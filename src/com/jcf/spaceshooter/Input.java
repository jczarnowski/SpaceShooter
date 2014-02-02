package com.jcf.spaceshooter;

import android.content.Context;
import android.view.View;

public class Input {
	MultiTouchHandler touchHandler;
	AccelHandler accelHandler;
	
	public Input(Context context, View view) {
		//accelHandler = new AccelHandler(context);
		touchHandler = new MultiTouchHandler(view);
	}
	
	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}
	
	public int getTouchX(int pointer) {
		return touchHandler.getTouchX(pointer);
	}
	
	public int getTouchY(int pointer) {
		return touchHandler.getTouchY(pointer);
	}
	
	/*
	public float getAccX() {
		return accelHandler.getX();
	}
	
	public float getAccY() {
		return accelHandler.getY();
	}
	
	public float getAccZ() {
		return accelHandler.getZ();
	}
	*/
}
