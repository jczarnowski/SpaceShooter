package com.jcf.spaceshooter.model;

import android.widget.AbsoluteLayout;

import com.jcf.spaceshooter.Assets;

public class Bullet extends SpaceObject{

	Asteroids asteroids;

	public Bullet(int x, int y, float velx, float vely, int screenWidth, int screenHeight, Asteroids astrds)
	{
		super(x,y,velx, vely,screenWidth,screenHeight,Assets.bullet);
		asteroids = astrds;
		hp = 1;
		power = 5;
	}
	
	public boolean update(int time) {
		
		if (x < - width 		||
				x > swidth + width 	||
				y < - height		||
				y > sheight - height) 
				{
					return false;
				}

		if(colisionDetection())
		{
			return false;
		}
		
		return super.update(time);
	}

	private boolean colisionDetection() {
		
		for(int i = 0; i<asteroids.count(); i++)
		{
			if(asteroids.simpleCollisionDetection(i,x,y,width,height, power))
			{
				return true;
			}
		}
		return false;
	}
	
}
