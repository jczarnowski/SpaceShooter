package com.jcf.spaceshooter.screen;

import android.graphics.Rect;

import com.jcf.spaceshooter.AndroidGame;

/*
 * Abstract screen class that every
 * screen in game has to extend
 */
public abstract class Screen {
	protected final AndroidGame game;
	
	public Screen(AndroidGame game) {
		this.game = game;
	}
	
	public abstract void update(float deltaTime);
	public abstract void present(float deltaTime);
	public abstract void pause();
	public abstract void resume();
	public abstract void dispose();
	
	public boolean inBounds(int x, int y, Rect rect) {
		if(x >= rect.left && x <= rect.right && y >= rect.top && y <= rect.bottom)
			return true;
		
		return false;
	}
}
