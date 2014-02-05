package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Pixmap;

public class SpaceObject {
	
	protected Pixmap pixmap;
	int time = 0;
	protected float  vx = 0, vy = 0, x , y, rot = 0, vrot = 0;
	protected float imageWidth, imageHeight, realWidth, realHeight;
	int swidth, sheight;
	
	public SpaceObject(int x, int y,float vx, float vy, int screenWidth, int screenHeight, Pixmap pixmap) {

		this.pixmap = pixmap;
		swidth = screenWidth;
		sheight = screenHeight;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		imageWidth = pixmap.getWidth();
		imageHeight = pixmap.getHeight();
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVx() {
		return vx;
	}


	public float getVy() {
		return vy;
	}


	public float getX() {
		return x;
	}


	public float getY() {
		return y;
	}

	public float getWidth() {
		return pixmap.getWidth();
	}	

	public float getHeight() {
		return pixmap.getHeight();
	}

	public boolean update(int time)
	{
		x += vx * time;
		y += vy * time;
		rot += vrot*time;
		return true;
	}
	
	public void draw(Graphics g)
	{
		//g.drawPixmap(pixmap, (int)(x - pixmap.getWidth()/2), (int)(y - pixmap.getHeight()/2));
		g.drawRotatedPixmap(pixmap, (int)(x - pixmap.getWidth()/2), (int)(y - pixmap.getHeight()/2), (float)(rot/Math.PI*180 ));
	}
}
