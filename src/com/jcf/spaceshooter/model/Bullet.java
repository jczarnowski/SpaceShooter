package com.jcf.spaceshooter.model;

import android.widget.AbsoluteLayout;

import com.jcf.spaceshooter.engine.Assets;

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

		return super.update(time);
	}

	public boolean colisionDetection(SpaceObjectsHandler objects) {
		
		for(int i = 0; i<objects.count(); i++)
		{
			if(objects.simpleCollisionDetection(i,x,y,width,height, power))
			{
				return true;
			}
		}
		return false;
	}
	
}
