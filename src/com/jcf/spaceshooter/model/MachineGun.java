package com.jcf.spaceshooter.model;

import java.util.ArrayList;

public class MachineGun extends Gun {

	private int amount;
	public MachineGun(ArrayList<InteractiveSpaceObject> bullets, int width, int height)
	{
		super(width,height,bullets);
		interval = 200;
		amount = 1;
		maxLevel = 7;
	}

	@Override
	public boolean fire(int x, int y, float rot ) {

		if(!super.fire(x, y, rot))
			return false;
		float da = 0.15f;
		float angle = da*(amount - 1);

		for(int i = 0; i< amount; i++)
		{
			float asd = (float) (rot / 1.30 * Math.PI + 0.01*(Math.random() - 0.5)) - angle/2f + da*i ;
			float v = 0.30f;
			bullets.add(new MachineGunBullet(x, y, asd, (float)Math.sin(asd)*v, -(float)Math.cos(asd)*v, width, height));
		}
		return true;
	}

	public void upgrade() {
		super.upgrade();
		switch(level)
		{
		case 0:
			interval = 400;
			amount = 1;
			break;
		case 1:
			interval = 300;
			amount = 2;
			break;
		case 2:
			interval = 300;
			amount = 3;
			break;
		case 3:
			interval = 300;
			amount = 4;
			break;
		case 5:
			interval = 300;
			amount = 5;
			break;
		case 6:
			interval = 300;
			amount = 6;
			break;
		case 7:
			interval = 200;
			amount = 7;
			break;
		}
	}
}
