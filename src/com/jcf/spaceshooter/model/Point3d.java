package com.jcf.spaceshooter.model;

public class Point3d {
	public float x,y,z;
	
	public Point3d(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}	
	
	public Point3d()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}	
	
	public Point3d(float width, float height, int depth)
	{
		x = (float) (Math.random()*width - width/2);
		y = (float) (Math.random()*height - height/2);
		z = (float) (Math.random()*depth*2 - depth);
	}
	
	public void randOnZ(float width, float height)
	{
		x = (float) (Math.random()*width - width/2);
		y = (float) (Math.random()*height - height/2);
		z = (float) (Math.random()*10 - 5);
	}

}
