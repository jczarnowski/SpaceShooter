package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Pixmap;

public class ParticleEmitter {
	
	long time, starttime;
	int lifetime, particlelifetime;
	int swidth, sheight;
	float dir, range, intensity, partVel, velx, vely, x, y;
	ArrayList<Particle> particles;
	Pixmap pixmap;
	
	public ParticleEmitter(int emiterLifetimeMilis, int particleLifetimeMilis, int x, int y,float particleVelocity, float direction, float angleRange, float intensity,
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
		
		this.time += time;
		emit(time);
		
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
		if(this.time < lifetime)
			for(int i = 0; i < intensity*time; i++)
			{
				float vx,vy;
				float rot = dir + (float)Math.random()*range;
				float vel = (float)Math.random();
				vx = vel*partVel * (float)Math.cos(rot) + velx;
				vy = vel*partVel * (float)Math.sin(rot) + vely;
				particles.add(new Particle((int)x, (int)y, vx, vy,particlelifetime, swidth, sheight, pixmap));
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

//	public void clean() {
//		for(int i = 0; i< particles.size(); i++)
//		{
//				//particles.get(i).clean();
//				particles.remove(i);
//				i--;
//		}
//	}
	
}
