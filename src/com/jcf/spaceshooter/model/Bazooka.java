package com.jcf.spaceshooter.model;

import java.util.ArrayList;

public class Bazooka extends Gun {


	private int amount;
	public Bazooka(ArrayList<InteractiveSpaceObject> bullets, int swidth, int sheight) {
		super(swidth,sheight,bullets);
		interval = 400;
		maxLevel = 8;
		amount = 1;
	}

	@Override
	public boolean fire(int x, int y, float rot ) {

		float dx = 10;
		if(!super.fire(x, y, rot))
			return false;

		float v = 0.50f;
		float shift = (amount -1)*dx/2f;
		for(int i = 0; i< amount; i++)
		{
			
			float asd = (float) (rot / 1.30 * Math.PI + 0.01*(Math.random() - 0.5));
			bullets.add(new BazookaBullet((int)(x-shift + dx*i), y, (float)Math.sin(asd)*v, -(float)Math.cos(asd)*v, width, height));
		}
		return true;
	}

	public void upgrade() {
		super.upgrade();
		switch(level)
		{
		case 0:
			interval = 300;
			amount = 1;
			break;
		case 1:
			interval = 200;
			amount = 1;
			break;
		case 2:
			interval = 200;
			amount = 2;
			break;
		case 3:
			interval = 200;
			amount = 3;
			break;
		case 4:
			interval = 150;
			amount = 3;
			break;	
		case 5:
			interval = 150;
			amount = 4;
			break;
		case 6:
			interval = 150;
			amount = 5;
			break;
		case 7:
			interval = 100;
			amount = 6;
			break;
		case 8:
			interval = 100;
			amount = 7;
			break;
		}
	}
}
