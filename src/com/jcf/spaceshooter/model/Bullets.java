package com.jcf.spaceshooter.model;

import java.util.Random;

public class Bullets extends SpaceObjectsHandler{

	public Bullets(int screenWidth, int screenHeight, SpaceShuttle ss)
	{
		super(screenWidth, screenHeight, ss);
	}
	
	
	public void update(int time)
	{
		super.update(time);
	}
//	{
//		for(int i = 0; i< spaceObjects.size(); i++)
//		{
//			if(!spaceObjects.get(i).update(time))
//			{
//				spaceObjects.remove(i--);
//				continue;
//			}
//			
////			if(bullets.get(i).colisionDetection(asteroids) || bullets.get(i).colisionDetection(enemies))
////			{
////				//bullets.get(i).clean();
////				bullets.remove(i--);
////			}
//		}
//	}
	
	public void add(int x, int y, float vx, float vy)
	{
		spaceObjects.add(new Bullet(x, y, vx, vy, sw, sh));
	}

	@Override
	public void createSpaceObjects(int n) {
		//nop
	}

	
	
}
