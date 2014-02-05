package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import com.jcf.spaceshooter.engine.Graphics;

public class SpaceObjectsHandler {
	
	protected ArrayList<ParticleEmitter> emitters;
	protected ArrayList<InteractiveSpaceObject> spaceObjects;
	
	protected SpaceShuttle ss;
	protected int sw, sh;
	protected int points;
	
	public SpaceObjectsHandler(int screenWidth, int screenHeight, SpaceShuttle s)
	{
		ss = s;
		sh = screenHeight;
		sw = screenWidth;
		
		emitters = new ArrayList<ParticleEmitter>();
		spaceObjects = new ArrayList<InteractiveSpaceObject>();
		points = 0;
	}
	
	public void updateEmitters(int time){
		for(int i = 0; i<emitters.size();i++)
			if(!emitters.get(i).update(time/5))
			{
				emitters.remove(i);
				i--;
			}
		}
	
	public int getPoints()
	{
		int tmp = points;
		points = 0;
		return tmp;
	}
	
	public void update(int time) {}
	
	public void draw(Graphics g)
	{
		for(InteractiveSpaceObject a:spaceObjects)
		{
			a.draw(g);
		}
		
		for(ParticleEmitter a:emitters)
		{
			a.draw(g);
		}
	}
	
	public void register(ScreenGrid sg)
	{
		for(InteractiveSpaceObject a:spaceObjects)
		{
			sg.registerSpaceObject(a);
		}
	}
	
	public int size()
	{
		return spaceObjects.size();
	}
}
