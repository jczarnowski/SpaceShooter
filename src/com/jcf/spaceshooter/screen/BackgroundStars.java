package com.jcf.spaceshooter.screen;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;

import com.jcf.spaceshooter.engine.Graphics;

public class BackgroundStars {
	private static final int STARS_NUM = 30;
	private static ArrayList<Point> starList = new ArrayList<Point>();
	private static boolean initialized = false;
	private static int width, height;
	private static int starwidth = 5;
	private static int starheight = 100;
	
	public static void init(Graphics g) {
		if(initialized)
			return;
		
		// generate stars
		Random gen = new Random();
		starList.clear();
		for(int i = 0; i < STARS_NUM; ++i) {
			Point p = new Point(gen.nextInt(g.getWidth()), gen.nextInt(g.getHeight()));
			starList.add(p);
		}
		
		width = g.getWidth();
		height = g.getHeight();
		initialized = true;
	}
	public static void update(int deltaTime) {
		if(!initialized)
			return;
		
		Random gen = new Random();
		float starspeed = 1;
		for(Point p : starList) {
			p.y += starspeed*deltaTime;
			if(p.y > height) {
				p.y = - p.y + height;
				p.x = gen.nextInt(width);
			}
		}
	}
	
	public static void present(int deltaTime, Graphics g) {
		if(!initialized)
			return;
		
		for(Point p : starList) {
			g.drawRect(p.x, p.y, starwidth, starheight, 0xFFFFFFFF);
		}
	}
	
	public static void deinitialize() {
		initialized = false;
	}
	
	public static void bank(float f) {
		for(Point p : starList) {
			p.x += f*((float)p.y)/width;
		}
	}
}
