package com.jcf.spaceshooter.model;

public class Gun {

	protected long lastShotTime;
	int width, height;
	
	public Gun(int w, int h)
	{
		width = w;
		height = h;
		lastShotTime = System.currentTimeMillis();
	}
	
	public void fire(int interval,int x, int y, float rot ) {
		
	}
}
