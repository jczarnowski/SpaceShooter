package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;

public class Ufo extends Enemy {

	
	public Ufo(int x, int y, float vx, float vy, int screenWidth, int screenHeight) {
		super(x,y,vx,vy,screenWidth,screenHeight, Assets.ufo);
		hp = 100;
		power = 5;
		vrot = 0;
	}
	
	@Override
	public boolean update(int time)
	{	
		vx = (float)(0.15*Math.sin(lifetime/200));

		return(super.update(time) );
	}
}
