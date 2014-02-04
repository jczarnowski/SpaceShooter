package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.Graphics;


public class EntityHandler { 

	private Asteroids asteroids;
	private Bullets bullets;
	private Enemies enemies;
	SpaceShuttle ss;

	public EntityHandler(int width, int height, TouchPad tp)
	{
		bullets = new Bullets(width, height);
		ss = new SpaceShuttle( width, height, tp, bullets);
		asteroids = new Asteroids(width, height, ss);
		enemies = new Enemies(width, height, ss);
		bullets.setAsteroids(asteroids);
		bullets.setEnemiess(enemies);
	}

	public void update(int time)
	{
		ss.update(time);
		asteroids.update(time);
		enemies.update(time);
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
		enemies.draw(g);
		asteroids.draw(g);
		
	}
}
