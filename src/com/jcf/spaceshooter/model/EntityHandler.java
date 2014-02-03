package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Graphics;

import android.widget.AbsoluteLayout;

public class EntityHandler {

	private Asteroids asteroids;
	private Bullets bullets;
	SpaceShuttle ss;

	public EntityHandler(int width, int height, TouchPad tp)
	{
		bullets = new Bullets(width, height);
		ss = new SpaceShuttle( width, height, tp, bullets);
		asteroids = new Asteroids(10, width, height, ss);
		bullets.setAsteroids(asteroids);
		asteroids.createAsteroids(1);
	}

	public void update(int time)
	{
		ss.update(time);
		asteroids.update(time);
		bullets.update(time);
		handleCollisions();
	}

	private void handleCollisions()
	{

	}

//	public boolean simpleCollisionDetection(int i, double x, double y, double width, double height) {
//
//		for(Asteroid a:asteroidsList)
//		{
//			double w =  a.getWidth()/2;
//			double h =  a.getHeight()/2;
//			if(	a.getX()+w > x &&
//					a.getX()-w < x + width &&
//					a.getY()+h > y &&
//					a.getY()-h < y )
//			{
//				a.clean();
//				asteroidsList.remove(a);
//				return true;
//			}
//		}
//		return false;
//	}

	public void draw(Graphics g)
	{
		bullets.draw(g);
		ss.draw(g);
		asteroids.draw(g);
		
	}
}
