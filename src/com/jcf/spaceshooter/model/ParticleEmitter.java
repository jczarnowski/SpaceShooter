package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import android.util.Log;

import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Pixmap;

public class ParticleEmitter {
	
	long time, starttime;
	int lifetime, particlelifetime;
	int swidth, sheight;
	float dir, range, intensity, partVel, velx, vely, x, y, width;
	ArrayList<Particle> particles;
	Pixmap pixmap;
	
	public ParticleEmitter(int emiterLifetimeMilis, int particleLifetimeMilis, int x, int y,float particleVelocity, float direction, float angleRange, float intensity,
			int screenWidth, int screenHeight, Pixmap pixmap)
	{
		init( emiterLifetimeMilis,  particleLifetimeMilis,  x,  y, particleVelocity,  direction,  angleRange,  intensity,
			 screenWidth,  screenHeight,  pixmap);
	}
	
	public ParticleEmitter(int i, int j, int x2, int y2,float vx,float vy, float width, float f, float g, float h, float k, int swidth2, int sheight2, Pixmap sparkBig) {
		init( i,  j,  x2,  y2, f, g,  h,  k,  swidth2,  sheight2, sparkBig);
		this.width = width/2;
		velx = vx;
		vely = vy;
		
	}
	
	private void init(int emiterLifetimeMilis, int particleLifetimeMilis, int x, int y,float particleVelocity, float direction, float angleRange, float intensity,
			int screenWidth, int screenHeight, Pixmap pixmap)
	{
		this.time = starttime = 0;

		this.range = angleRange;
		this.dir = direction;
		swidth = screenWidth;
		sheight = screenHeight;
		this.x = x;
		this.y = y;
		partVel = particleVelocity;
		particles = new ArrayList<Particle>();
		lifetime = emiterLifetimeMilis;
		particlelifetime = particleLifetimeMilis;
		this.intensity = intensity;
		this.pixmap = pixmap;
		velx = vely = 0;
	}


	public boolean update(int time)
	{
		x += (float)(time*velx/100.0);
		y += (float)(time*vely/100.0);
		
		emit(time);
		this.time += time;
		
		for(int i = particles.size()-1; i>=0; i--)
			if(!particles.get(i).update(time))
			{
				particles.remove(i);
			}
		return particles.size()>0 || lifetime > this.time;
	}
	
	public void updateLocation(int x, int y, float dir)
	{
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void emit(int time)
	{
		//Log.d("emituje", "lifetime " + lifetime + " time " + this.time + " intensity " + intensity + " time " + time );
		if(this.time <= lifetime)
			for(int i = 0; i < intensity*time; i++)
			{
				Log.d("emituje", "dodaje " + i);
				float vx,vy;
				float rot = dir + (float)Math.random()*range;
				float vel = (float)Math.random();
				vx = vel*partVel * (float)Math.cos(rot) + velx*3;
				vy = vel*partVel * (float)Math.sin(rot) + vely*3;
				double random = width*Math.random();
				particles.add(new Particle((int)(x + vel*width*Math.cos(rot)), (int)(y+ vel*width*Math.sin(rot)), vx, vy,(int)(particlelifetime*(Math.random()*0.5+0.5)), swidth, sheight, pixmap));
			}
	}
	
	public void draw(Graphics g)
	{
		for(Particle a:particles)
			a.draw(g);
	}
	
	public void setVels(float x, float y)
	{
		velx = x;
		vely = y;
	}
}
