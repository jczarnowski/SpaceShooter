package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Pixmap;

public class Background {
	float y;
	ArrayList<BackgroundStar> stars;

	float starVelocity = 0.2f;
	float planetVelocity = 0.05f; 
	
	protected int swidth, sheight;
	
	public Background(int screenWidth, int screenHeight)
	{
		swidth = screenWidth;
		sheight = screenHeight;
		
		stars = new ArrayList<BackgroundStar>();
		addStars(50);
		//addPlanets(1);
		
	}
	
	public void addStars(int num)
	{
		addElements(num, starVelocity, Assets.star, 0);
	}
	
	public void addPlanets(int num)
	{
		for(int i = 0; i< num; i++)
			addElements(num, planetVelocity, Assets.planet, (float)Math.random()*360);
	}
	
	public void addElements(int num, float vel, Pixmap pixmap, float rotation)
	{
		for(int i = 0; i< num; i++)
			stars.add(new BackgroundStar((int)(Math.random()*swidth), (int)(Math.random()*sheight), 0, vel, swidth, sheight, pixmap));
	}
	
	public void update(int time)
	{
		for(BackgroundStar a: stars)
			if(!a.update(time))
			{
				a.setY(a.getY() - sheight);
				a.setX((float)(Math.random()*swidth));
			}
	}	
	
	public void draw(Graphics g)
	{
		for(BackgroundStar a: stars)
			a.draw(g);
	}
	
}
