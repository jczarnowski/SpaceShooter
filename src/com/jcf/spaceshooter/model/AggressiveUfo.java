package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class AggressiveUfo extends Enemy {

	float diff;
	SpaceShuttle ss;

	public AggressiveUfo(int x, int y, float vx, float vy, int screenWidth, int screenHeight, SpaceShuttle ss) {
		super(x,y,vx,vy,screenWidth,screenHeight, Assets.ufo);
		type = Type.AGGRESSIVE_UFO;
		hp = 100;
		power = 5;
		vrot = 0;
		diff = (float)Math.random()*0.1f + 1f;
		initialHp = hp;
		this.ss = ss;
	}

	public boolean update(int time)
	{	
		vx = (float)(0.15*Math.sin(diff*lifetime/200f));
		rot = (float)(0.2*Math.sin(diff*lifetime/200f));

		if(Math.random() < 0.01)
			attack();


		return(super.update(time) );

	}

	private void attack() {

		float v = 0.4f;
		double dir = Math.atan2(ss.getY() - y, ss.getX() - x);
		
		bullet = new EnemyBullet((int)x, (int)y, (float)(v*Math.cos(dir)), (float)(Math.sin(dir)), swidth, sheight);
	}

	public void colisionDetected(InteractiveSpaceObject object) {
		super.colisionDetected(object);

		if(hp <= 0)
			Assets.ufo_expl.play(0.8f);
		else
			Assets.hit.play(0.1f);
	}
}
