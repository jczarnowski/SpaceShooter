package com.jcf.spaceshooter;

import java.util.ArrayList;

import com.jcf.spaceshooter.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class KeyHandler implements OnKeyListener {
	public static final int MAX_KEYEVENTS = 25;
	ArrayList<KeyEvent> keyEvents = new ArrayList<KeyEvent>(MAX_KEYEVENTS);
	boolean backPressed = false;
	
	public KeyHandler(View view) {
		view.setOnKeyListener(this);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
	}
	
	@Override
	public boolean onKey(View v, int keycode, android.view.KeyEvent event) {
		if(keycode != android.view.KeyEvent.KEYCODE_BACK)
			return true;
		
		KeyEvent evt = new KeyEvent();
		evt.keyCode = keycode;
		switch(event.getAction()) {
		case android.view.KeyEvent.ACTION_DOWN:	
			backPressed = true;
			evt.type = KeyEvent.KEY_DOWN;
			break;
		case android.view.KeyEvent.ACTION_UP:
			backPressed = false;
			evt.type = KeyEvent.KEY_UP;
			break;
		}
		
		keyEvents.add(evt);
		return true;
	}
	
	public boolean isBackPressed() {
		return backPressed;
	}

	public ArrayList<KeyEvent> getKeyEvents() {
		ArrayList<KeyEvent> clone;
		
		synchronized(this) {
			clone = (ArrayList<KeyEvent>) keyEvents.clone();
			keyEvents.clear();
		}
		
		return clone;
	}
}
