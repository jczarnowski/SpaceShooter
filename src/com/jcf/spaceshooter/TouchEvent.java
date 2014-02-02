package com.jcf.spaceshooter;

/*
 * class that keeps the information about
 * a touch event.
 */
public class TouchEvent {
	public static int TOUCH_DOWN = 0;
	public static int TOUCH_UP = 1;
	public static int TOUCH_MOVE = 2;

	public int type;
	public int x, y;
	public int pointerid;
}
