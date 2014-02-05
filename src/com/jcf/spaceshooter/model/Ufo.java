package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class Ufo extends Enemy {

	float diff;
	
	public Ufo(int x, int y, float vx, float vy, int screenWidth, int screenHeight) {
		super(x,y,vx,vy,screenWidth,screenHeight, Assets.ufo);
		hp = 100;
		power = 5;
		vrot = 0;
		diff = (float)Math.random()*0.1f + 1f;
		initialHp = hp;
	}
	
	public boolean update(int time)
	{	
		vx = (float)(0.15*Math.sin(diff*lifetime/200f));
		rot = (float)(0.2*Math.sin(diff*lifetime/200f));
		
		return(super.update(time) );
	}
	
	public void colisionDetected(InteractiveSpaceObject object) {
		super.colisionDetected(object);
		
		if(hp <= 0)
			Assets.ufo_expl.play(0.8f);
		else
			Assets.hit.play(0.1f);
	}
}
