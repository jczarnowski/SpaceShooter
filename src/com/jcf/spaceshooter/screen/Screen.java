package com.jcf.spaceshooter.screen;

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
}
