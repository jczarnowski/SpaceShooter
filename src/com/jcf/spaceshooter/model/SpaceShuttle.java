package com.jcf.spaceshooter.model;


import android.widget.AbsoluteLayout;

import com.jcf.spaceshooter.Assets;

public class SpaceShuttle extends SpaceObject{

	TouchPad tp;
	Gun activeGun, machineGun;
	
	public SpaceShuttle (int screenWidth, int screenHeight ,TouchPad tp, Bullets bullets) 
	{
		super(300,300,0f,0f, screenWidth, screenHeight , Assets.rocket);
		this.tp = tp;
		hp = 100;
		actualizeDimensions();
		machineGun = new MachineGun(bullets, swidth, sheight);
		activeGun = machineGun;
	}


	public boolean update(int time) {
		
		if(tp.get_y1()>0)
			activeGun.fire((int)(4000 / tp.get_y1()),(int)x,(int)y,rot);
		
		double cap = 150;
		
		vx = tp.get_x2();
		vy = tp.get_y2();
		
		rot = (float)(Math.atan2(vx,-vy + 200)*180/3.14);

		super.update(time);
		
		float r = 0.6f;
		if (x < pixmap.getWidth()/2) {
			vx *= -r;
			x = pixmap.getWidth()/2;
		}
		if (x > swidth - pixmap.getWidth()/2 ) {
			vx *= -r;
			x = swidth - pixmap.getWidth()/2;
		}		
		

		if (y < pixmap.getHeight()/2) {
			vy *= -r;
			y = pixmap.getHeight()/2;
		}
		if (y > sheight - pixmap.getHeight()/2) {
			vy *= -r;
			y = sheight - pixmap.getHeight()/2;
		}
		return true;
	}
}
