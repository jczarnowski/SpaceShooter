package com.jcf.spaceshooter.model;


import com.jcf.spaceshooter.engine.Assets;

public class SpaceShuttle extends InteractiveSpaceObject{

	TouchPad tp;
	Gun activeGun, machineGun;
	
	public SpaceShuttle (int screenWidth, int screenHeight ,TouchPad tp, Bullets bullets) 
	{
		super(screenWidth/2,(int)(screenHeight*0.8),0f,0f, screenWidth, screenHeight , Assets.rocket);
		this.tp = tp;
		hp = 100;
		machineGun = new MachineGun(bullets, swidth, sheight);
		activeGun = machineGun;
	}


	public boolean update(int time) {
		
		//if(tp.get_y1()>0)
		activeGun.fire(40,(int)x,(int)y,rot);
		
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
