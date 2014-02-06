package com.jcf.spaceshooter.engine;

/*
 * class that keeps the information about
 * a touch event.
 */
public class TouchEvent {
	public static final int TOUCH_DOWN = 0;
	public static final int TOUCH_UP = 1;
	public static final int TOUCH_MOVE = 2;

	public int type;
	public int x, y;
	public int pointerid;
}
