package com.jcf.spaceshooter.screen;

import android.util.Log;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.Assets;
import com.jcf.spaceshooter.Graphics;
import com.jcf.spaceshooter.Input;
import com.jcf.spaceshooter.MultiTouchHandler;

public class MainMenuScreen extends Screen {
	public MainMenuScreen(AndroidGame game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		int num_touched = 0;
		Input input = game.getInput();
		
		for(int i = 0; i < MultiTouchHandler.MAX_TOUCHPOINTS; ++i)
			if(input.isTouchDown(i))
				num_touched++;
		
		Log.d("asd", "Palców na ekranie: " + num_touched);
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.clear(0xFFFFFF);
		
		int x = (g.getWidth() - Assets.menuText.getWidth())/2;
		int y = (g.getHeight() - Assets.menuText.getHeight())/2;
		
		g.drawPixmap(Assets.menuText, x, y);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
