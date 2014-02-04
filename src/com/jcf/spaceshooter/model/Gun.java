package com.jcf.spaceshooter.model;

public class Gun {

	protected long lastShotTime;
	int width, height;
	protected int interval;

	Bullets bullets;
	
	public Gun(int w, int h,Bullets bullets)
	{
		width = w;
		height = h;
		lastShotTime = System.currentTimeMillis();
		this.bullets = bullets;
	}


	public boolean fire(int x, int y, float rot ) {
		
		if(lastShotTime < System.currentTimeMillis() - interval)
		{
			if(lastShotTime < System.currentTimeMillis() - 2*interval)
				lastShotTime = System.currentTimeMillis();
			else
				lastShotTime+=interval;
			
			return true;
		}
		else
		{
			return false;
		}
	}
}
