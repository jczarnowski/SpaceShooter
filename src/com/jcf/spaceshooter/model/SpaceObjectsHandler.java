package com.jcf.spaceshooter.model;

import java.util.ArrayList;
import java.util.Random;

import com.jcf.spaceshooter.Assets;
import com.jcf.spaceshooter.Graphics;

public class SpaceObjectsHandler {

	protected ArrayList<ParticleEmitter> emitters;
	protected ArrayList<SpaceObject> spaceObjects;
	
	SpaceShuttle ss;
	int sw, sh;
	
	public SpaceObjectsHandler(int screenWidth, int screenHeight, SpaceShuttle s)
	{
		ss = s;
		sh = screenHeight;
		sw = screenWidth;
		
		emitters = new ArrayList<ParticleEmitter>();
		spaceObjects = new ArrayList<SpaceObject>();
	
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
				spaceObjects.remove(i);
			}
		}
		
	}
	
	public void createSpaceObjects(int n)
	{

	}

	public int count() {
		return spaceObjects.size();
	}
	
	public void draw(Graphics g)
	{
		for(SpaceObject a:spaceObjects)
		{
			a.draw(g);
		}
		
		for(ParticleEmitter a:emitters)
		{
			a.draw(g);
		}
	}
	
	public boolean simpleCollisionDetection(int i, double x, double y, double width, double height, int power) {

		for(SpaceObject a:spaceObjects)
		{
			double w =  a.getWidth()/2;
			double h =  a.getHeight()/2;
			if(	a.getX()+w > x &&
					a.getX()-w < x + width &&
					a.getY()+h > y &&
					a.getY()-h < y )
			{
				a.hp -= power;
				if(a.hp < 0)
				{
					//add extra explosion to emitter list
					ParticleEmitter tmpEmitter = new ParticleEmitter(20, 50,(int)(a.getX()),(int)(a.getY()),150f,0f,(float)Math.PI*2f, 4f, sw, sh, Assets.sparkBig);
					//tmpEmitter.setVels(a.getVx(), a.getVy());
					emitters.add(tmpEmitter);
					ArrayList<ParticleEmitter> tmp = a.getParticleEmitters();
					spaceObjects.remove(a);
					if(tmp != null)
						emitters.addAll(tmp);

					return true;
				}
				else
					emitters.add(new ParticleEmitter(10, 70,(int)x,(int)y,100f,0f,(float)Math.PI*2f, 0.3f, sw, sh, Assets.spark));
				
				return true;
			}
		}
		return false;
	}
	
	
}
