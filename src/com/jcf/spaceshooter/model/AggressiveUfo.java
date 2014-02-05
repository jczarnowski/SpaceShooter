package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import com.jcf.spaceshooter.engine.Assets;

public class AggressiveUfo extends Enemy {

	float diff;
	float moving_direction;
	float abs_vel;
	float avVy;
	SpaceShuttle ss;
	ArrayList<InteractiveSpaceObject> spaceObjects;

	public AggressiveUfo(int x, int y, float vy, float absVel, int screenWidth, int screenHeight, SpaceShuttle ss, ArrayList<InteractiveSpaceObject> spaceObjects) {
		super(x, y, 0, vy, screenWidth, screenHeight, Assets.ufo2);
		type = Type.AGGRESSIVE_UFO;
		hp = 30;
		power = 5;
		vrot = 0;
		diff = (float)Math.random()*0.1f + 1f;
		if(Math.random()<0.5)
			diff *=-1;
		
		initialHp = hp;
		this.ss = ss;
		this.spaceObjects = spaceObjects;
		
		abs_vel = absVel;
		moving_direction = 0;
		avVy = vy;
	}

	public boolean update(int time)
	{	
		moving_direction = diff*lifetime/300f;
		
		vx = 2*(float)(Math.cos(moving_direction)*abs_vel);
		vy = (float)(Math.sin(moving_direction)*abs_vel) + avVy;
		
		rot = (float)(0.2*Math.sin(diff*lifetime/200f));

		if(Math.random() < 0.001 && y < (sheight/2))
			attack();


		return(super.update(time) );

	}

	private void attack() {

		float v = 0.2f;
		double dir = Math.atan2(ss.getY() - y, ss.getX() - x);
		
		spaceObjects.add(new EnemyBullet((int)x, (int)y, (float)dir, (float)(v*Math.cos(dir)), (float)(v*Math.sin(dir)), swidth, sheight));
	}

	public void colisionDetected(InteractiveSpaceObject object) {
		super.colisionDetected(object);

		if(hp <= 0)
			Assets.ufo_expl.play(0.8f);
		else
			Assets.hit.play(0.1f);
	}
}
