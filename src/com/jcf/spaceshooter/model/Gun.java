package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class Gun {

	public static final int MACHINE_GUN = 0;
	public static final int BAZOOKA = 1;
	public static final int CRAZY = 2;
	
	protected long lastShotTime;
	int width, height;
	protected int interval;
	protected int level;
	protected int maxLevel;
	Bullets bullets;
	
	public Gun(int w, int h,Bullets bullets)
	{
		width = w;
		height = h;
		lastShotTime = System.currentTimeMillis();
		this.bullets = bullets;
		level = 0;
	}


	public boolean fire(int x, int y, float rot ) {
		
		if(lastShotTime < System.currentTimeMillis() - interval)
		{
			if(lastShotTime < System.currentTimeMillis() - 2*interval)
				lastShotTime = System.currentTimeMillis();
			else
				lastShotTime+=interval;
			
			Assets.pew.play(0.01f);
			return true;
		}
		else
		{
			return false;
		}
	}


	public void upgrade() {
		if(level<maxLevel) level++;
	}


}
