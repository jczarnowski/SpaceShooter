package com.jcf.spaceshooter.engine;

import java.util.ArrayList;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MultiTouchHandler implements OnTouchListener {
	public static final int MAX_TOUCHEVENTS = 50;
	public static final int MAX_TOUCHPOINTS = 10;
	boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
	int[] touchX = new int[MAX_TOUCHPOINTS];
	int[] touchY = new int[MAX_TOUCHPOINTS];
	int[] id = new int[MAX_TOUCHPOINTS];
	
	ArrayList<TouchEvent> touchEvents = new ArrayList<TouchEvent>(MAX_TOUCHEVENTS);
	
	public MultiTouchHandler(View view) {
		view.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized(this) {
			int action = event.getAction() & MotionEvent.ACTION_MASK;
			int pointerCount = event.getPointerCount();
			 
			for(int i = 0; i < MAX_TOUCHPOINTS; ++i) {
				// if there are no more pointers on screen, zero out the rest of touchpoints
				if(i >= pointerCount) {
					isTouched[i] = false;
					id[i] = -1;
					continue;
				}
				
				int pointerId = event.getPointerId(i);
				TouchEvent touchEvent = new TouchEvent();
				touchEvent.pointerid = event.getPointerId(i);
				
				switch(action) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
					isTouched[i] = true;
					id[i] = pointerId;
					touchEvent.x = touchX[i] = (int) event.getX(i);
					touchEvent.y = touchY[i] = (int) event.getY(i);
					touchEvent.type = TouchEvent.TOUCH_DOWN;
					break;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_CANCEL:
					isTouched[i] = false;
					id[i] = -1;
					touchEvent.x = 0;
					touchEvent.y = 0;
					touchEvent.type = TouchEvent.TOUCH_UP;
					break;
				case MotionEvent.ACTION_MOVE:
					isTouched[i] = true;
					id[i] = pointerId;
					touchEvent.x = touchX[i] = (int) event.getX(i);
					touchEvent.y = touchY[i] = (int) event.getY(i);
					touchEvent.type = TouchEvent.TOUCH_MOVE;
					break;
				}
				
				// add to event lists
				touchEvents.add(touchEvent);
				
				// if event list is too big, remove the oldest event
				if(touchEvents.size() > MAX_TOUCHEVENTS)
					touchEvents.remove(0);
			}
			
			return true;
		}
	}
	
	public boolean isTouchDown(int pointerid) {
		synchronized(this) {
			int ind = getTPointIndex(pointerid);
			return ind < 0 || ind > MAX_TOUCHPOINTS ? false : isTouched[ind];
		}
	}
	
	public int getTouchX(int pointerid) {
		synchronized(this) {	
			int ind = getTPointIndex(pointerid);
			return ind < 0 || ind > MAX_TOUCHPOINTS ? 0 : touchX[ind];
		}
	}
	
	public int getTouchY(int pointerid) {
		synchronized(this) {
			int ind = getTPointIndex(pointerid);
			return ind < 0 || ind > MAX_TOUCHPOINTS ? 0 : touchY[ind];
		}
	}
	
	public ArrayList<TouchEvent> getTouchEvents() {
		ArrayList<TouchEvent> clone;
		synchronized(this) {
			clone = (ArrayList<TouchEvent>) touchEvents.clone();
			touchEvents.clear();
		}
		
		return clone;
	}
	
	private int getTPointIndex(int pointerId) {
		for(int i = 0; i < MAX_TOUCHPOINTS; ++i) {
			if(pointerId == id[i])
				return i;
		}
		
		return -1;
	}
}
