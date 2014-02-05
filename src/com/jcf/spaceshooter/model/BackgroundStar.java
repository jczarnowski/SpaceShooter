package com.jcf.spaceshooter.model;


import com.jcf.spaceshooter.engine.Pixmap;

public class BackgroundStar extends SpaceObject {

	public BackgroundStar(int x, int y, float vx, float vy,	int screenWidth, int screenHeight, Pixmap pixmap) {
		super(x, y, vx, vy, screenWidth, screenHeight, pixmap);
		// TODO Auto-generated constructor stub
	}
	
	public boolean update(int time, float vel)
	{
		vy = vel;
		super.update(time);
		return y<=sheight + imageHeight/2;
	}



}
