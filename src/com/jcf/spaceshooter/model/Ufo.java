package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class Ufo extends Enemy {

	private int lifetime = 0;
	
	public Ufo(int x, int y, float vx, float vy, int screenWidth, int screenHeight) {
		super(x,y,vx,vy,screenWidth,screenHeight, Assets.ufo);
		hp = 100;
		power = 5;
		vrot = 0;
	}
	
	@Override
	public boolean update(int time)
	{	
		lifetime += time;
		vx = (float)(15*Math.sin(lifetime/100));

		return(super.update(time) );
	}
}
