package com.jcf.spaceshooter.model;

import java.util.ArrayList;
import java.util.Random;

import com.jcf.spaceshooter.engine.Graphics;

public class EnemiesHandler extends SpaceObjectsHandler {

	private int aggrUfos;
	private int ufos;
	private int asteroids;
	private int reqAggrUfos;
	private int reqUfos;
	private int reqAsteroids;
	
	public EnemiesHandler(int screenWidth, int screenHeight, SpaceShuttle s, int rufos, int rasteroids, int raufos)
	{
		super(screenWidth, screenHeight,s);
		reqAggrUfos = raufos;
		reqAsteroids = rasteroids;
		reqUfos = rufos;
		aggrUfos = ufos = asteroids = 0;
	}

	public ArrayList<ParticleEmitter> getEmitters(){return emitters;}
	
	public void update(int time) {

		updateEmitters(time);

		BonusDrop tmpBonus;
		for (int i = spaceObjects.size() -1; i >= 0; i--) {

			InteractiveSpaceObject obj = spaceObjects.get(i);
			if (!spaceObjects.get(i).update(time)) 
			{
				tmpBonus = obj.getBonus();
				emitters.addAll(obj.getParticleEmitters());
				switch(obj.type)
				{
				case AGGRESSIVE_UFO:
					aggrUfos--;
					break;
				case UFO:
					ufos--;
					break;
				case ASTEROID:
					asteroids--;
					break;
				}
				if(tmpBonus != null)
					spaceObjects.add(tmpBonus);

				spaceObjects.remove(i);
			}
			points += ((InteractiveSpaceObject)obj).getPoints();
		}
		
		createUfos(reqUfos - ufos);
		createAsteriods(reqAsteroids - asteroids);
		createAggresiveUfos(reqAggrUfos - aggrUfos);
	}

	public void createUfos(int n)
	{
		Random  random = new Random();
		for(int j = 0; j< n;j++)
		{
			float a = random.nextFloat()*0.10f + 0.05f;
			float b = 0;
			int x = (int) (random.nextFloat()*sw);
			int y = (int) (random.nextFloat()*sh - sh);
			{
				spaceObjects.add(new Ufo(x,y,b,a,sw,sh));
			}
		
		}
		ufos+=n;
	}
	
	public void createAsteriods(int n)
	{
		Random  random = new Random();
		for(int j = 0; j< n;j++)
		{
			float a = random.nextFloat()*0.20f + 0.20f;
			float b = random.nextFloat()*0.10f -0.05f;
			int x = (int) (random.nextFloat()*sw);
			int y = (int) (random.nextFloat()*sh - sh);
			{
				spaceObjects.add(new Asteroid(x,y,b,a,sw,sh));
			}
		}
			asteroids+=n;
	}	
	
	public void createAggresiveUfos(int n)
	{
		Random  random = new Random();
		for(int j = 0; j< n;j++)
		{
			float a = random.nextFloat()*0.20f + 0.20f;
			float b = random.nextFloat()*0.10f -0.05f;
			int x = (int) (random.nextFloat()*sw);
			int y = (int) (random.nextFloat()*sh - sh);
			{
				spaceObjects.add(new AggressiveUfo(x,y,b,a,sw,sh,ss));
			}
		}
			aggrUfos+=n;
	}
}
