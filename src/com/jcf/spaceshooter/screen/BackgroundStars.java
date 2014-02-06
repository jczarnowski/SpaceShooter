package com.jcf.spaceshooter.screen;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;

import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.model.Point3d;

public class BackgroundStars {
	private static final int STARS_NUM = 400;
	private static ArrayList<Point3d> starList = new ArrayList<Point3d>();
	private static ArrayList<Point3d> transformedStarList = new ArrayList<Point3d>();

	private static boolean initialized = false;
	private static int width, height;
	private static int starwidth = 5;
	private static int starheight = 500;
	private static int eyeheight = 300;

	private static double alfa = 0;
	private static double beta = 0;

	public static void init(Graphics g) {
		if(initialized)
			return;

		width = g.getWidth();
		height = g.getHeight();

		// generate stars
		Random gen = new Random();
		starList.clear();
		transformedStarList.clear();
		for(int i = 0; i < STARS_NUM; ++i) {
			starList.add(new Point3d(width,width,starheight*2));
			transformedStarList.add(new Point3d(0,0,0));
		}

		initialized = true;
	}
	public static void update(int deltaTime) {
		if(!initialized)
			return;

		Random gen = new Random();
		float starspeed = 0.5f;
		for(Point3d p : starList) {
			p.z += starspeed*deltaTime;
			if(p.z > 2*starheight ) {
				p.randOnZ(width, width);
			}
		}
	}

	public static void present(int deltaTime, Graphics g) {
		if(!initialized)
			return;

		for(int i = 0; i<transformedStarList.size();i++) {
			
			if(transformedStarList.get(i).z < eyeheight)
			{
				double w = (starwidth/(- transformedStarList.get(i).z + eyeheight) * eyeheight);//(int)( starwidth*transformedStarList.get(i).z);
				double y = (transformedStarList.get(i).y/(-transformedStarList.get(i).z + eyeheight) * eyeheight);
				double x = (transformedStarList.get(i).x/(-transformedStarList.get(i).z + eyeheight) * eyeheight);
				g.drawRect((int)(x + w/2 + width/2),(int)( y  -w/2 + height/2), (int)w, (int)w, 0xFFFFFFFF);
				if(x>width/2|| x< -width/2)
					starList.get(i).randOnZ( width, width);
			}
		}
		//g.drawText(10,30," a = " + alfa,0xffffffff);
	}

	public static void deinitialize() {
		initialized = false;
	}

	public static void bank(float f) {
		for(Point3d p : starList) {
			p.x += f*((float)p.y)/width;
		}
	}

	public static void transform(float accX, float accY, float accZ) {

		alfa = alfa *0.95f + 0.05f *( Math.atan2(-accZ, accX)+ Math.PI/4);

		for(int i = 0; i< starList.size();i++) {
			transformedStarList.get(i).y  = (float) (starList.get(i).y * Math.cos(alfa) + (starheight - starList.get(i).z)*Math.sin(alfa));
			transformedStarList.get(i).z =  (float) ((starList.get(i).z -starheight)*Math.cos(alfa) + starList.get(i).y * Math.sin(alfa));
			transformedStarList.get(i).x = starList.get(i).x;
			//if(transformedStarList.get(i).z)
		}

	}
}
