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
	char R = 0, G = 0, B = 0 , A = 0xFF, rr = 0, rg = 0, rb = 0;
	int color;
	protected int swidth, sheight;
	
	public Background(int screenWidth, int screenHeight)
	{
		swidth = screenWidth;
		sheight = screenHeight;
		
		stars = new ArrayList<BackgroundStar>();
		addStars(50);
		//addPlanets(1);
		
		color = (char) MainMenuScreen.BGCOLOR;
		rr = R = (char) ((color>>16)&0xFF);
		rg = G = (char) ((color>>8)&0xFF);
		rb = B = (char) (color&0xFF);
		A = 0xFF;
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
		R = (char)(a1*R + a2*rr);
		G = (char)(a1*G + a2*rg);
		B = (char)(a1*B + a2*rb);
		A = (char)(a1*R + a2*0xFF);
		//color =  (int) (A<<24 + R<<16 + G<<8 + B) ;
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
//		rr = (char)((c>>16)&0xFF);
//		rg =  (char)((c>>8)&0xFF);
//		rb = (char)(c&0xFF);
//		A = 0;
		color = c;
	}
	
}
