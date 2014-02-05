package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Pixmap;

public class EnemyBullet extends Bullet {

	public EnemyBullet(int x, int y, float velx, float vely, int screenWidth,
			int screenHeight) {
		super(x, y, velx, vely, screenWidth, screenHeight, Assets.fnf);
	}
}
