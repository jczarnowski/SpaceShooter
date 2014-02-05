package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import android.graphics.Color;
import android.util.Log;

import com.jcf.spaceshooter.engine.Graphics;


public class EntityHandler { 

	private Asteroids asteroids;
	private Bullets bullets;
	private Enemies enemies;
	SpaceShuttle ss;
	ScreenGrid sg;
	int points;
	int displayedPoints;
	int lastLoopTime;
	float fps, maxfps, minfps=1000;

	public EntityHandler(int screenWidth, int screenHeight)
	{
		bullets = new Bullets(screenWidth, screenHeight,ss);
		ss = new SpaceShuttle( screenWidth, screenHeight, bullets);
		asteroids = new Asteroids(screenWidth, screenHeight, ss);
		enemies = new Enemies(screenWidth, screenHeight, ss);
		sg = new ScreenGrid(screenWidth, screenHeight, 5, 5);
		displayedPoints = points = 0; 
		
	}

	public void update(int time)
	{
		ss.update(time);
		asteroids.update(time);
		enemies.update(time);
		bullets.update(time);
		
		Log.d("b",Integer.toString(bullets.size()));
		
		sg.clear();
		
		bullets.register(sg);
		asteroids.register(sg);
		enemies.register(sg);
		sg.registerSpaceObject(ss);
	
		handleCollisions();
		
		points += enemies.getPoints() + asteroids.getPoints();
		
		lastLoopTime = time;
	}
	
	private void handleCollisions()
	{
		ArrayList<InteractiveSpaceObject> obj;
		for(int i = 0;i<sg.getHorSharNum();i++)
		{
			for(int j = 0; j<sg.getVerSharNum();j++)
			{
				obj = sg.getObjectList(i, j);
				for(int k = 0; k< obj.size();k++)
				{
					//inside cell
					for(int l = k+1; l< obj.size();l++)
					{
						obj.get(k).colisionDetection(obj.get(l));
					}
					
					//between cells
					if(i+1<sg.getHorSharNum())
					{
						ArrayList<InteractiveSpaceObject> objNextCell = sg.getObjectList(i+1, j);
						for(int l = 0; l< objNextCell.size();l++)
						{
							obj.get(k).colisionDetection(objNextCell.get(l));
						}
					}
				}
			}
		}
	}

	public void draw(Graphics g)
	{
		bullets.draw(g);
		ss.draw(g);
		enemies.draw(g);
		asteroids.draw(g);
		
		int log = 0;
		if(points > displayedPoints) displayedPoints = (int)(displayedPoints*0.9 + points*0.1);
		if(displayedPoints != 0)
			log = (int)Math.log10(displayedPoints);
		
		g.drawNumberYellow(g.getWidth() - 21 * log - 31, 10, displayedPoints);	
		
		
		if(lastLoopTime!=0)
		{
			float tmp = 1000f/lastLoopTime;
			fps = fps*0.9f + 0.1f*tmp;
			maxfps = Math.max(tmp, maxfps);
			minfps = Math.min(tmp, minfps);
		}
		g.drawNumberYellow(g.getWidth() - 21 * 2 - 31, g.getHeight() - 40, (int)fps);
		g.drawNumberYellow(g.getWidth() - 21 * 2 - 31, g.getHeight() - 75, (int)maxfps);
		g.drawNumberYellow(g.getWidth() - 21 * 2 - 31, g.getHeight() - 110, (int)minfps);
			
	}

	public SpaceShuttle getShuttle() {
		return ss;
	}
	
	public int getPoints() {
		return points;
	}
}
