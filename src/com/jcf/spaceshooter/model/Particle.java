package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.Pixmap;

public class Particle extends SpaceObject {
	
	int lifetime, chuj;
	
	public Particle(int x, int y, float vx, float vy, int lifetime, int screenWidth, int screenHeight, Pixmap pixmap) 
	{
		super(x, y, vx, vy, screenWidth, screenHeight,  pixmap);
		chuj = 0;
		this.lifetime = lifetime;
	}
	

	public boolean update(int time)
	{
		chuj+=time;
		return super.update(time) && chuj<lifetime;
	}
}
