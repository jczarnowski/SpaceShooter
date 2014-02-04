package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import com.jcf.spaceshooter.Graphics;
import com.jcf.spaceshooter.Pixmap;

public class SpaceObject {
	protected Pixmap pixmap;
	int time = 0;
	protected float  vx = 0, vy = 0, x , y, rot = 0, vrot = 0;
	protected float width, height;
	int swidth, sheight;
	protected int hp;
	protected int power;
	protected ArrayList<ParticleEmitter> pe;
	
	public SpaceObject(int x, int y,float vx, float vy, int screenWidth, int screenHeight, Pixmap pixmap) {
		
		this.pixmap = pixmap;
		swidth = screenWidth;
		sheight = screenHeight;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		pe = new ArrayList<ParticleEmitter>();
		
	}
	
	public void addEmitter(ParticleEmitter particleEmitter) {
		pe.add(particleEmitter);
	}

	public void actualizeDimensions()
	{
		width = pixmap.getWidth();
		height = pixmap.getHeight();
	}
	
	public ArrayList<ParticleEmitter> getParticleEmitters()
	{
		ArrayList<ParticleEmitter> tmp = pe;
		pe = null;
		return tmp;
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
		x += vx * time / 100.0;
		y += vy * time / 100.0;
		rot += vrot*time / 100.0;

		for(int i = pe.size() -1; i >= 0;i--)
			if(!pe.get(i).update(time))
			{
				pe.remove(i);
			}

		return true;
	}
	
	public void draw(Graphics g)
	{
		for(ParticleEmitter a:pe)
			a.draw(g);
		
		g.drawPixmap(pixmap, (int)(x - pixmap.getWidth()/2), (int)(y - pixmap.getHeight()/2));
		
//		image.setX((float) x - image.getWidth()/2);
//		image.setY((float) y - image.getHeight()/2);
//		image.setPivotX(image.getWidth()/2);
//		image.setPivotY(image.getHeight()/2);
//		image.setRotation((float)rot);
	}

}
