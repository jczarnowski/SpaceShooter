package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import com.jcf.spaceshooter.Graphics;

public class Bullets{

	ArrayList<Bullet> bullets;
	int sh, sw;
	Asteroids asteroids;
	Enemies enemies;
	
	public Bullets(int screenWidth, int screenHeight)
	{
		sh = screenHeight;
		sw = screenWidth;
		bullets = new ArrayList<Bullet>();
	}
	
	public void setAsteroids(Asteroids astrds)
	{
		asteroids = astrds;
	}	
	public void setEnemiess(Enemies enems)
	{
		enemies = enems;
	}
	
	public void update(int time)
	{
		for(int i = 0; i< bullets.size(); i++)
		{
			if(!bullets.get(i).update(time))
			{
				//bullets.get(i).clean();
				bullets.remove(i--);
				continue;
			}
			
			if(bullets.get(i).colisionDetection(asteroids) || bullets.get(i).colisionDetection(enemies))
			{
				//bullets.get(i).clean();
				bullets.remove(i--);
			}
		}
	}
	
	public void add(int x, int y, float vx, float vy)
	{
		bullets.add(new Bullet(x, y, vx, vy, sw, sh, asteroids));
	}

	public void draw(Graphics g) {
		for(Bullet a:bullets)
			a.draw(g);
	}
	public int size()
	{
		return bullets.size();
	}
	
	
}
