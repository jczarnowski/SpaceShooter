package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import android.graphics.Color;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Pixmap;
import com.jcf.spaceshooter.screen.MainMenuScreen;

public class Background {
	float y;
	ArrayList<BackgroundStar> stars;

	float starVelocity = 0.1f;
	float reqStarVelocity = 0.1f;
	float planetVelocity = 0f; 
	float colorR = 0, colorG = 0, colorB = 0, rr = 0, rg = 0, rb = 0;
	int color;
	protected int swidth, sheight;
	
	public Background(int screenWidth, int screenHeight)
	{
		swidth = screenWidth;
		sheight = screenHeight;
		
		stars = new ArrayList<BackgroundStar>();
		addStars(50);
		//addPlanets(1);
		color = MainMenuScreen.BGCOLOR;
		
	}
	
	public void addStars(int num)
	{
		addElements(num, starVelocity, Math.random() > 0.5 ? Assets.star[0] : Assets.star[1], 0);
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
		float a1 = 0.95f, a2 = 0.05f;
		//color = (int)(a1*color + a2*reqColor);
		starVelocity = a1*(float)starVelocity + a2*(float)reqStarVelocity;
		for(BackgroundStar a: stars)
		{
			if(!a.update(time, starVelocity))
			{
				a.setY(a.getY() - sheight);
				a.setX((float)(Math.random()*swidth));
			}
		}
	}	
	
	public void draw(Graphics g)
	{		
		g.clear(color);
		
		for(BackgroundStar a: stars)
			a.draw(g);
	}

	public void setSpeed(float f) {
		reqStarVelocity = f;
		
	}

	public void setColor(int c) {
		color = c;
	}
	
}
