package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Pixmap;

public class Asteroid extends Enemy {


	public Asteroid(int x, int y, float vx, float vy, int screenWidth, int screenHeight)
	{
		super(x,y,vx,vy,screenWidth,screenHeight, Math.random()>0.5? Assets.asteroid1: Assets.asteroid2);
		type = Type.ASTEROID;
		hp = 60;
		power = 5;
		vrot = (float)Math.random()*0.01f - 0.005f;
		initialHp = hp;
	}
	
	public boolean update(int time)
	{	
		return super.update(time);
	}
	
	public void colisionDetected(InteractiveSpaceObject object) {
		super.colisionDetected(object);
		
		if(hp <= 0)
			Assets.asteroid_expl.play(0.1f);
		else
			Assets.hit.play(0.05f);
	}
}
