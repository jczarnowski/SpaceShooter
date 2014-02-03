package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.Assets;


public class Asteroid extends SpaceObject {


	public Asteroid(int x, int y, float vx, float vy, int screenWidth, int screenHeight)
	{
		super(x,y,vx,vy,screenWidth,screenHeight, Math.random()>0.5? Assets.asteroid1: Assets.asteroid2);
		hp = 60;
		power = 5;
		vrot = (float)Math.random()*100 - 50;
	}
	
	public boolean update(int time)
	{	
		super.update(time);

		if ((x < - getWidth() &&	vx > 0)			||
			(x > swidth + getWidth() && vx < 0)	||
			(y < - sheight && vy > 0)		||
			(y > sheight + getHeight())) 
			{
				return false;
			}
		else
		{
			return true;
		}
	}


	
}
