package com.jcf.spaceshooter.model;

import android.widget.AbsoluteLayout;

import com.jcf.spaceshooter.engine.Pixmap;

public class BackgroundStar extends InteractiveSpaceObject {

	public BackgroundStar(int x, int y, float vx, float vy,	int screenWidth, int screenHeight, Pixmap pixmap) {
		super(x, y, vx, vy, screenWidth, screenHeight, pixmap);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean update(int time)
	{
		super.update(time);
		return y<=sheight + height/2;
	}



}
