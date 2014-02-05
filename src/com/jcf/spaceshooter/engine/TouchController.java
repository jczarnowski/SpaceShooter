package com.jcf.spaceshooter.engine;


import java.util.ArrayList;

import android.util.Log;
import android.view.MotionEvent;

import com.jcf.spaceshooter.model.SpaceShuttle;

public class TouchController extends ShuttleController {
	
	int width;
	
	public TouchController(Input input, int width) {
		super(input);
		this.width = width;
	}

	@Override
	public void ControlShuttle(SpaceShuttle shuttle) {

		ArrayList<TouchEvent> e = input.getTouchEvents();
		
		boolean left = false, right = false;
		
		for(int i = 0; i <e.size();i++)
		{	
			switch (e.get(i).type) {
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_DOWN:
				{
					if(e.get(i).x > width/2)
						right = true;
					else
						left = true;
					break;
				}
			}
		}
		
		if(left == right)
			shuttle.setVx(0);
		else
			if(left)
				shuttle.setVx(-0.6f);
			else
				shuttle.setVx(0.6f);
		
	}
	




}
