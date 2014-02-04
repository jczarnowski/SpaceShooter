package com.jcf.spaceshooter.model;

import java.util.Random;

public class Enemies extends SpaceObjectsHandler {

	public Enemies(int screenWidth, int screenHeight, SpaceShuttle s)
	{
		super(screenWidth, screenHeight, s);
	}
	
	public void update(int time) {

		super.update(time);
		if(spaceObjects.size()<3)
			createSpaceObjects(1);
		
	}
	
	public void createSpaceObjects(int n)
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
	}
}
