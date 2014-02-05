package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class EnemyBullet extends Bullet {

	public EnemyBullet(int x, int y, float rot, float velx, float vely, int screenWidth,
			int screenHeight) {
		super(x, y, velx, vely, screenWidth, screenHeight, Assets.enemyBullet);
		type = Type.ENEMY_BULLET;
		this.rot = rot;
	}
	
	public void colisionDetected(InteractiveSpaceObject object)
	{
		if(object instanceof SpaceShuttle)
		{
			pe.add(new ParticleEmitter(0, 200,(int)x,(int)y, vx, vy,imageWidth,0.80f,0f,(float)Math.PI*2f, 10f, swidth, sheight, Assets.sparkBigRed));
			hp = 0;
		}
	}
}
