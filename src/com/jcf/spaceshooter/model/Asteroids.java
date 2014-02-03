package com.jcf.spaceshooter.model;

import java.util.ArrayList;
import java.util.Random;

import android.widget.ImageView;

import com.jcf.spaceshooter.Assets;
import com.jcf.spaceshooter.Graphics;

public class Asteroids {

	private ArrayList<Asteroid> asteroidsList;
	int interval;
	SpaceShuttle ss;
	int sw, sh;
	int res;
	
	public Asteroids( int interval, int screenWidth, int screenHeight, SpaceShuttle s)
	{

		ss = s;
		this.interval = interval;
		sh = screenHeight;
		sw = screenWidth;
		asteroidsList = new ArrayList<Asteroid>();
		
		for(Asteroid a:asteroidsList)
			a.actualizeDimensions();
	}
	
	public void update(int time) {

		if(asteroidsList.size()<10)
			createAsteroids(1);
		
		for (int i = 0; i < asteroidsList.size(); i++) {
			
		ImageView ball;
		
			double x = asteroidsList.get(i).getX();
			double y = asteroidsList.get(i).getY();
	
			double bvx = asteroidsList.get(i).getVx();
			double bvy = asteroidsList.get(i).getVy();
			double dirchange = 0;
	
			float r = 1f;
			x += bvx * interval / 100.0;
			y += bvy * interval / 100.0;
			
			if (!asteroidsList.get(i).update(i)) 
			{
				asteroidsList.get(i).clean();
				asteroidsList.remove(i);
				i--;
				continue;
			}
			
//			if(simpleCollisionDetection(i, ss.getX(), ss.getY(), ss.getWidth(), ss.getHeight()))
//			{
//				//asteroidsList.get(i).clean();
//				//asteroidsList.remove(i);
//				i--;
//				continue;
//			}	
			
			
//			if (y > ss.getY() - ballList.get(i).getHeight() && over.get(i)) {
//				over.set(i, false);
//				if (x < ss.getX() + ss.getWidth()
//						&& x > ss.getX() - ballList.get(i).getWidth()) {
//					over.set(i,true);
//					bvy = -r *(bvy - ss.getVy()) + ss.getVy();
//					double px = (double) (ss.getX() + ss.getWidth()
//							/ 2.0 - ballList.get(i).getX() + ballList.get(i).getWidth() / 2)
//							/ ss.getWidth() * 2.0;
//					dirchange = -px * 0.3;
//	
//					y = ss.getY() - ballList.get(i).getHeight();
//				}
//			}
//			if (y < ss.getY() - ballList.get(i).getHeight() && !over.get(i)) {
//				over.set(i,true);
//			}
		}
	}
	
	public void createAsteroids(int n)
	{
		Random  random = new Random();
		for(int j = 0; j< n;j++)
		{
			float a = random.nextFloat()*20 + 20;
			float b = random.nextFloat()*30 -15;
			int x = (int) (random.nextFloat()*sw);
			int y = (int) (random.nextFloat()*sh - sh);
			{
				asteroidsList.add(new Asteroid(x,y,b,a,sw,sh));
			}
		}
	}
	
//	boolean colisionDetection(float x, float y, float w, float h)
//	{ 
//		//for(int i = 0; i < ball)
//		return true;
//	
//	}

	public int count() {
		return asteroidsList.size();
	}
	public boolean simpleCollisionDetection(int i, double x, double y, double width, double height, int power) {

		for(Asteroid a:asteroidsList)
		{
			double w =  a.getWidth()/2;
			double h =  a.getHeight()/2;
			if(	a.getX()+w > x &&
					a.getX()-w < x + width &&
					a.getY()+h > y &&
					a.getY()-h < y )
			{
				a.hp -= power;
				if(a.hp < 0)
				{
					if(a.clean())
						asteroidsList.remove(a);
					return true;
				}
				a.addEmitter(new ParticleEmitter(10, 70,(int)x,(int)y,100f,0f,(float)Math.PI*2f, 0.3f, sw, sh, Assets.spark));
				return true;
			}
		}
		return false;
	}
	
	public void draw(Graphics g)
	{
		for(Asteroid a:asteroidsList)
		{
			a.draw(g);
		}
	}


}
