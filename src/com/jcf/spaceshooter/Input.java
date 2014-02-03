package com.jcf.spaceshooter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;

public class Input {
	MultiTouchHandler touchHandler;
	AccelHandler accelHandler;
	KeyHandler keyHandler;
	
	public Input(Context context, View view) {
		accelHandler = new AccelHandler(context);
		touchHandler = new MultiTouchHandler(view);
		keyHandler = new KeyHandler(view);
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
	
	public boolean isBackPressed() {
		return keyHandler.isBackPressed();
	}
	
	public ArrayList<TouchEvent> getTouchEvents() {
		return touchHandler.getTouchEvents();
	}
	
	public ArrayList<KeyEvent> getKeyEvents() {
		return keyHandler.getKeyEvents();
	}
	
	public float getAccX() {
		return accelHandler.getX();
	}
	
	public float getAccY() {
		return accelHandler.getY();
	}
	
	public float getAccZ() {
		return accelHandler.getZ();
	}
}
