package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import android.util.Log;

import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Pixmap;

public class InteractiveSpaceObject extends SpaceObject{

	BonusDrop bonus;
	protected int lifetime = 0;
	protected int hp;
	protected int power;
	protected ArrayList<ParticleEmitter> pe;
	protected int initialHp;
	protected int points;

	public InteractiveSpaceObject(int x, int y,float vx, float vy, int screenWidth, int screenHeight, Pixmap pixmap) {
		super(x, y, vx, vy, screenWidth, screenHeight, pixmap);

		pe = new ArrayList<ParticleEmitter>();
		points = 0;
		initialHp = hp;
	}

	public void addEmitter(ParticleEmitter particleEmitter) {
		pe.add(particleEmitter);
	}


	public ArrayList<ParticleEmitter> getParticleEmitters()
	{
		ArrayList<ParticleEmitter> tmp = pe;
		pe = null;
		return tmp;
	}

	public int getPoints()
	{
		int tmp = points;
		points = 0;
		return tmp;
	}

	public boolean update(int time)
	{				
		lifetime += time;
		if(hp<=0) return false;

		super.update(time);

		for(int i = pe.size() -1; i >= 0;i--)
		{
			if(!pe.get(i).update(time))
			{
				pe.remove(i);
			}
		}

		return true;
	}

	public void draw(Graphics g)
	{
		super.draw(g);

		for(ParticleEmitter a:pe)
			a.draw(g);

	}

	public boolean colisionDetection(InteractiveSpaceObject object) {

		double w =  (object.getWidth()+realWidth)/2;
		double h =  (object.getHeight()+realHeight)/2;

		if (	object.getX() < x + w &&
				object.getX() > x - w &&
				object.getY() < y + h &&
				object.getY() > y - h)
		{
			object.colisionDetected(this);
			colisionDetected(object);
			return true;
		}
		else
		{
			return false;
		}
	}	

	public void colisionDetected(InteractiveSpaceObject object) {
		//Log.d("colision","asd");
	}

	public BonusDrop getBonus()
	{
		return bonus;
	}

}
