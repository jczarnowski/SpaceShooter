package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import android.graphics.Color;
import android.util.Log;

import com.jcf.spaceshooter.engine.Graphics;


public class EntityHandler { 

//	private Asteroids asteroids;
//	private Bullets bullets;
	private EnemiesHandler enemies;
	private PlayerObjectsHandler player;
	
	ScreenGrid playersGrid, enemiesGrid;
	int points;
	int displayedPoints;
	int lastLoopTime;
	float fps, maxfps, minfps=1000;
	long start, stop;
	float coldettime;
	int lvl;

	public EntityHandler(int screenWidth, int screenHeight)
	{
		player = new PlayerObjectsHandler(screenWidth, screenHeight);
		enemies = new EnemiesHandler(screenWidth, screenHeight, player.getShuttle(),4,0,0);
		playersGrid = new ScreenGrid(screenWidth, screenHeight, 5, 5);
		enemiesGrid = new ScreenGrid(screenWidth, screenHeight, 5, 5);
		displayedPoints = points = 0; 
		lvl = 0;
	}

	public void update(int time)
	{
		enemies.update(time);
		player.update(time);

		enemiesGrid.clear();
		playersGrid.clear();
		enemies.register(enemiesGrid);
		player.register(playersGrid);
	
		handleCollisions();
		
		points += enemies.getPoints() ;//+ asteroids.getPoints();
		
		lastLoopTime = time;
	}
	
	public void lvlUp(int level)
	{
		switch(level)
		{
		case 1:	
			enemies.setEntitiesNumbers(0,4,0);
			break;
			
		case 2:
			enemies.setEntitiesNumbers(0,0,3);
			break;

		case 3:
			enemies.setEntitiesNumbers(3,3,1);
			break;

		case 4:
			enemies.setEntitiesNumbers(3,3,3);
			break;
			
		default:

			enemies.setEntitiesNumbers(level/2+2,level/2+2,level/2+2);
			break;
			
		}
	}
	
	
	private void handleCollisions()
	{
		start = System.currentTimeMillis();
		if(playersGrid.getVerSharNum() != enemiesGrid.getVerSharNum() ||playersGrid.getHorSharNum() != enemiesGrid.getHorSharNum() )
			throw new RuntimeException("players ScreenGrid and enemies ScreenGrid differ in size!");
		
		ArrayList<InteractiveSpaceObject> playersObj, enemyObj, enemyObjNextCell;
		for(int i = 0;i<playersGrid.getHorSharNum();i++)
		{
			for(int j = 0; j<playersGrid.getVerSharNum();j++)
			{
				playersObj = playersGrid.getObjectList(i, j);
				enemyObj = enemiesGrid.getObjectList(i, j);			
				
				if(i+1<playersGrid.getHorSharNum()){
					enemyObjNextCell = enemiesGrid.getObjectList(i+1, j);}
					
				for(int k = 0; k< playersObj.size();k++)
				{
					//inside cell
					for(int l = 0; l< enemyObj.size();l++)
					{
						playersObj.get(k).colisionDetection(enemyObj.get(l));
					}
					
					//between cells
					if(i+1<playersGrid.getHorSharNum())
					{
						ArrayList<InteractiveSpaceObject> objNextCell = enemiesGrid.getObjectList(i+1, j);
						for(int l = 0; l< objNextCell.size();l++)
						{
							playersObj.get(k).colisionDetection(objNextCell.get(l));
						}
					}
				}
			}
		}
		stop = System.currentTimeMillis();
		coldettime = coldettime*0.95f + 0.05f*(float)(stop-start); 
	}

	public void draw(Graphics g)
	{
		enemies.draw(g);
		player.draw(g);
		
		int log = 0;
		if(points > displayedPoints) displayedPoints = (int)(displayedPoints*0.9 + points*0.1);
		if(displayedPoints != 0)
			log = (int)Math.log10(displayedPoints);
		
		g.drawText(g.getWidth() - 21 * log - 31, 30, Integer.toString(displayedPoints), 0xffa4a4a4);	
		
		
		if(lastLoopTime!=0)
		{
			float tmp = 1000f/lastLoopTime;
			fps = fps*0.9f + 0.1f*tmp;
			maxfps = Math.max(tmp, maxfps);
			minfps = Math.min(tmp, minfps);
		}
		g.drawText(g.getWidth() - 21 * 2 - 40, g.getHeight() - 40, Float.toString(fps), 0xffa4a4a4);
		g.drawText(g.getWidth() - 21 * 2 - 40, g.getHeight() - 75, Float.toString(maxfps), 0xffa4a4a4);
		g.drawText(g.getWidth() - 21 * 2 - 40, g.getHeight() - 110, Float.toString(minfps), 0xffa4a4a4);
		g.drawText( 10 , g.getHeight() - 40, Float.toString(coldettime*100), 0xffa4a4a4);
			
	}

	public SpaceShuttle getShuttle() {
		return player.getShuttle();
	}
	
	public int getPoints() {
		return points;
	}
}
