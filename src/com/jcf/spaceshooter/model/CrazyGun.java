package com.jcf.spaceshooter.model;

import java.util.ArrayList;

public class CrazyGun extends Gun {

	private int amount;
	public CrazyGun(ArrayList<InteractiveSpaceObject> bullets, int swidth, int sheight) {
		super(swidth,sheight,bullets);
		interval = 100;
		maxLevel = 6;
		amount = 1;
		upgrade();
		upgrade();
		upgrade();
		upgrade();
	}

	@Override
	public boolean fire(int x, int y, float rot ) {

		if(!super.fire(x, y, rot))
			return false;
		float da = 0.15f;
		float angle = da*(amount - 1);
		float v = 0.20f;
		for(int i = 0; i< amount; i++)
		{
			float asd = (float) (rot / 1.30 * Math.PI) - angle/2f + da*i ;
			bullets.add(new CrazyGunBullet(x, y, (float)Math.sin(asd)*v, -(float)Math.cos(asd)*v, width, height,(long)(i + System.currentTimeMillis()/5000)));
			
		}
		return true;
	}

	public void upgrade() {
		super.upgrade();
		switch(level)
		{
		case 0:
			interval = 100;
			amount = 1;
			break;
		case 1:
			interval = 80;
			amount = 1;
			break;
		case 2:
			interval = 80;
			amount = 2;
			break;
		case 3:
			interval = 80;
			amount = 3;
			break;
		case 4:
			interval = 70;
			amount = 3;
			break;	
		case 5:
			interval = 70;
			amount = 4;
			break;
		case 6:
			interval = 70;
			amount = 5;
			break;
		case 7:
			interval = 70;
			amount = 6;
			break;
		case 8:
			interval = 70;
			amount = 7;
			break;
		}
	}
}
