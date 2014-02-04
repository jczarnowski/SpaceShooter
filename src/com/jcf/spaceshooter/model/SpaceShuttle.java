package com.jcf.spaceshooter.model;


import android.util.Log;

import com.jcf.spaceshooter.engine.Assets;

public class SpaceShuttle extends InteractiveSpaceObject{

	TouchPad tp;
	Gun activeGun, machineGun, bazooka;
	
	public SpaceShuttle (int screenWidth, int screenHeight ,TouchPad tp, Bullets bullets) 
	{
		super(screenWidth/2,(int)(screenHeight*0.8),0f,0f, screenWidth, screenHeight , Assets.rocket);
		this.tp = tp;
		hp = 100;
		machineGun = new MachineGun(bullets, swidth, sheight);
		bazooka = new Bazooka(bullets, swidth, sheight);
		activeGun = bazooka;
		x = 300;
	}


	public boolean update(int time) {
		
		activeGun.fire((int)x,(int)y,rot);
		
		rot = (float)(Math.atan2(vx,-vy + 200)*180/3.14);
		
		if(time != 0)
		{
			vx = (tp.get_sumx() - x)/time;
			vy = (tp.get_sumy() - y)/time;
		}
		
		super.update(time);
		
		if (x < pixmap.getWidth()/2) {
			x = pixmap.getWidth()/2;
		}
		if (x > swidth - pixmap.getWidth()/2 ) {
			x = swidth - pixmap.getWidth()/2;
		}		
		
		if (y < pixmap.getHeight()/2) {
			y = pixmap.getHeight()/2;
		}
		if (y > sheight - pixmap.getHeight()/2) {
			y = sheight - pixmap.getHeight()/2;
		}
		return true;
	}
}
