package com.jcf.spaceshooter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MultiTouchHandler implements OnTouchListener {
	public static final int MAX_TOUCHEVENTS = 20;
	public static final int MAX_TOUCHPOINTS = 10;
	boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
	int[] touchX = new int[MAX_TOUCHPOINTS];
	int[] touchY = new int[MAX_TOUCHPOINTS];
	int[] id = new int[MAX_TOUCHPOINTS];
	
	public MultiTouchHandler(View view) {
		view.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		int pointerCount = event.getPointerCount();
		 
		for(int i = 0; i < MAX_TOUCHPOINTS; ++i) {
			// if there are no more pointers on screen, zero out the rest of touchpoints
			if(i >= pointerCount) {
				isTouched[i] = false;
				id[i] = -1;
				continue;
			}
			
			int pointerId = event.getPointerId(i);
			
			switch(action) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				isTouched[i] = true;
				id[i] = pointerId;
				touchX[i] = (int) event.getX(i);
				touchY[i] = (int) event.getY(i);
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_CANCEL:
				isTouched[i] = false;
				id[i] = -1;
				break;
			case MotionEvent.ACTION_MOVE:
				isTouched[i] = true;
				id[i] = pointerId;
				touchX[i] = (int) event.getX(i);
				touchY[i] = (int) event.getY(i);
			}
		}
		
		return true;
	}
	
	public boolean isTouchDown(int pointerid) {
		int ind = getTPointIndex(pointerid);
		return ind < 0 || ind > MAX_TOUCHPOINTS ? false : isTouched[ind];
	}
	
	public int getTouchX(int pointerid) {
		int ind = getTPointIndex(pointerid);
		return ind < 0 || ind > MAX_TOUCHPOINTS ? 0 : touchX[ind];
	}
	
	public int getTouchY(int pointerid) {
		int ind = getTPointIndex(pointerid);
		return ind < 0 || ind > MAX_TOUCHPOINTS ? 0 : touchY[ind];
	}
	
	private int getTPointIndex(int pointerId) {
		for(int i = 0; i < MAX_TOUCHPOINTS; ++i) {
			if(pointerId == id[i])
				return i;
		}
		
		return -1;
	}
}
