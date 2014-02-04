package com.jcf.spaceshooter.model;


import android.util.Log;

import com.jcf.spaceshooter.engine.Assets;

public class SpaceShuttle extends InteractiveSpaceObject{

	Gun activeGun, machineGun, bazooka;
	private float acc = 0.01f;
	
	private float desiredVx = 0;
	
	public void setVx(float d) {
		desiredVx = d;
	}
	
	public SpaceShuttle (int screenWidth, int screenHeight , Bullets bullets) 
	{
		super(screenWidth/2,(int)(screenHeight*0.9),0f,0f, screenWidth, screenHeight , Assets.rocket);
		hp = 100;
		machineGun = new MachineGun(bullets, swidth, sheight);
		bazooka = new Bazooka(bullets, swidth, sheight);
		activeGun = bazooka;
	}


	public boolean update(int time) {
		
		activeGun.fire((int)x,(int)y,rot);
		
		rot = (float)(Math.atan2(vx,-vy + 200)*180/3.14);
		
		if(vx < desiredVx)
		{
			vx = Math.min(vx+acc*time,desiredVx);
		}
		else
			if(vx > desiredVx)
			{
				vx = Math.max(vx-acc*time,desiredVx);
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
