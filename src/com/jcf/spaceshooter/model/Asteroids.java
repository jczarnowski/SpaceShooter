package com.jcf.spaceshooter.model;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;
import android.widget.ImageView;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;

public class Asteroids extends SpaceObjectsHandler{

	public Asteroids(int screenWidth, int screenHeight, SpaceShuttle s)
	{
		super(screenWidth, screenHeight, s);
	}
	
	public void update(int time) {

		super.update(time);
		if(spaceObjects.size()<10)
			createSpaceObjects(3);
	}
	
	public void createSpaceObjects(int n)
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
	}
	
}
