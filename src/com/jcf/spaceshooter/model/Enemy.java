package com.jcf.spaceshooter.model;

import android.util.Log;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Pixmap;

public class Enemy extends InteractiveSpaceObject {

	public Enemy(int x, int y, float vx, float vy, int screenWidth,
			int screenHeight, Pixmap pixmap) {
		super(x, y, vx, vy, screenWidth, screenHeight, pixmap);
		// TODO Auto-generated constructor stub
	}

	public boolean colisionDetection(InteractiveSpaceObject object) {
		if(object instanceof Enemy) return false;
		return (super.colisionDetection(object));
	}
	
	public void colisionDetected(InteractiveSpaceObject object)
	{
		if(object instanceof Enemy) return;
		
		if(object instanceof Bullet)
		{
			hp -= object.power;
			if(hp <= 0)
			{
				//explosion
				pe.add(new ParticleEmitter(0, 200,(int)x,(int)y, vx, vy,width,0.80f,0f,(float)Math.PI*2f, 10f, swidth, sheight, Assets.sparkBig));
			}
			else
				pe.add(new ParticleEmitter(0, 200,(int)object.getX(),(int)object.getY(),0.40f,(float)Math.atan2(object.getY() - y, object.getX() -x),1, 0.2f, swidth, sheight, Assets.spark));
		}
	}
	
	public boolean update(int time)
	{
		
		return super.update(time) && (y <= sheight + getHeight());

	}
	
}
