package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class BazookaBullet extends Bullet {

	public BazookaBullet(int x, int y, float velx, float vely, int screenWidth,
			int screenHeight) {
		super(x, y, velx, vely, screenWidth, screenHeight,Assets.bullet_rocket);
		power = 21;
	}

}
