package com.jcf.spaceshooter.model;

import java.util.Random;

import com.jcf.spaceshooter.engine.Assets;

public class CrazyGunBullet extends Bullet {

	Random random;
	public CrazyGunBullet(int x, int y, float velx, float vely,int screenWidth, int screenHeight, long seed) {
		super(x, y, velx, vely, screenWidth, screenHeight, Assets.sparkBigBlue);
		power = 4;
		random = new Random(seed);
		//random = new Random(random.nextLong());
		
	}
	
	@Override
	public boolean update(int time) {
		//if(random.nextFloat()>0.4)
		{	
			double v = Math.sqrt(vx*vx + vy*vy);
			double a = Math.atan2(vy, vx) + random.nextFloat()*0.4f - 0.2f;;
			vx = (float)(Math.cos(a)*v);
			vy = (float)(Math.sin(a)*v);
			
		}
		if(x< 0 || y< 0 || x > swidth || y > sheight) return false;
		return super.update(time);
	}

}
