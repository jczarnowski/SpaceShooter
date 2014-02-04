package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import android.Manifest.permission;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;

public abstract class SpaceObjectsHandler {

	protected ArrayList<ParticleEmitter> emitters;
	protected ArrayList<InteractiveSpaceObject> spaceObjects;
	
	SpaceShuttle ss;
	int sw, sh;
	
	public SpaceObjectsHandler(int screenWidth, int screenHeight, SpaceShuttle s)
	{
		ss = s;
		sh = screenHeight;
		sw = screenWidth;
		
		emitters = new ArrayList<ParticleEmitter>();
		spaceObjects = new ArrayList<InteractiveSpaceObject>();
	
	}

	public ArrayList<ParticleEmitter> getEmitters(){return emitters;}
	
	public void updateEmitters(int time){
		for(int i = 0; i<emitters.size();i++)
			if(!emitters.get(i).update(time/5))
			{
				//pe.get(i).clean();
				emitters.remove(i);
				i--;
			}
		}

	public void update(int time) {

		updateEmitters(time);
		
		for (int i = spaceObjects.size() -1; i >= 0; i--) {

			if (!spaceObjects.get(i).update(time)) 
			{
				emitters.addAll(spaceObjects.get(i).getParticleEmitters());
				spaceObjects.remove(i);
			}
		}
	}
	
	
	abstract public void createSpaceObjects(int n);

	public int count() {
		return spaceObjects.size();
	}
	
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
