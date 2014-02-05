package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Graphics;

public class PlayerObjectsHandler extends SpaceObjectsHandler {

	
	public PlayerObjectsHandler(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight, null);
		
		ss = new SpaceShuttle(screenWidth, screenHeight,spaceObjects);
	}
	
	public void update(int time) {

		updateEmitters(time);
		ss.update(time);
		
		for (int i = spaceObjects.size() -1; i >= 0; i--) {

			SpaceObject obj = spaceObjects.get(i);
			if (!spaceObjects.get(i).update(time)) 
			{
				spaceObjects.remove(i);
			}
			points += ((InteractiveSpaceObject)obj).getPoints();
		}
	}
	
	public SpaceShuttle getShuttle()
	{
		return ss;
	}
	
	public void draw(Graphics g)
	{
		super.draw(g);
		ss.draw(g);
	}
	
	public void register(ScreenGrid sg)
	{
		sg.registerSpaceObject(ss);
		super.register(sg);
	}
}
