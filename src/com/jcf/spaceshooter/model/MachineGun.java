package com.jcf.spaceshooter.model;

public class MachineGun extends Gun {

	Bullets bullets;
	
	public MachineGun(Bullets bullets, int width, int height)
	{
		super(width,height);
		this.bullets = bullets;
	}
	
	@Override
	public void fire(int interval,int x, int y, float rot ) {
		
		interval *= 0.5;
		if(lastShotTime < System.currentTimeMillis() - interval)
		{
			if(lastShotTime < System.currentTimeMillis() - 2*interval)
				lastShotTime = System.currentTimeMillis();
			else
				lastShotTime+=interval;
		}
		else
		{
			return;
		}
		
		float asd = (float) (rot / 130 * Math.PI + 0.01*(Math.random() - 0.5));
		float v = 30;
		bullets.add(x, y, (float)Math.sin(asd)*v, -(float)Math.cos(asd)*v);
		
	}
}
