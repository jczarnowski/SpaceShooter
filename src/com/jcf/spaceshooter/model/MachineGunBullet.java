package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class MachineGunBullet extends Bullet {

	public MachineGunBullet(int x, int y,float rot, float velx, float vely, int screenWidth,
			int screenHeight) {
		super(x, y, velx, vely, screenWidth, screenHeight, Assets.bullet);
		power = 20;
		this.rot = rot;
	}

}
