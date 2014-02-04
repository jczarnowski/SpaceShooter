package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class Bullet extends InteractiveSpaceObject{

	public Bullet(int x, int y, float velx, float vely, int screenWidth, int screenHeight)
	{
		super(x,y,velx, vely,screenWidth,screenHeight,Assets.bullet);
		hp = 1;
		power = 10;
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

	public boolean colisionDetection(InteractiveSpaceObject object) {
		
		if(object instanceof SpaceShuttle) return false;
		
		return super.colisionDetection(object);
	}
	
	public void colisionDetected(InteractiveSpaceObject object) {
		hp = -1;
	}
	
}
