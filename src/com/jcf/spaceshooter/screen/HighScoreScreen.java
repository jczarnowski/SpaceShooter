package com.jcf.spaceshooter.screen;

import java.util.ArrayList;

import android.graphics.Rect;
import android.util.Log;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Input;
import com.jcf.spaceshooter.engine.KeyEvent;
import com.jcf.spaceshooter.engine.TouchEvent;

public class HighScoreScreen extends Screen {
	Rect backBounds;
	
	public HighScoreScreen(AndroidGame game) {
		super(game);
		
		Graphics g = game.getGraphics();
		backBounds = new Rect(10, g.getHeight()-10-Assets.back.getHeight(), 10+Assets.back.getWidth(), g.getHeight()-10);
	}

	@Override
	public void update(int deltaTime) {
		Input input = game.getInput();
		
		// process touch events
		ArrayList<TouchEvent> eventList = input.getTouchEvents();
		for(int i = eventList.size()-1; i >= 0; --i) {
			TouchEvent event = eventList.get(i);
			
			if(event.type == TouchEvent.TOUCH_DOWN) {
				int x = input.getTouchX(i);
				int y = input.getTouchY(i);
				
				if(inBounds(x, y, backBounds)) {
					game.setScreen(new MainMenuScreen(game));
					if(game.getConfig().soundOn) Assets.click.play(1);
				}
			}
		}
		
		Log.d("acc", "accel sensor: " + input.getAccX() + ", " + input.getAccY() + ", " + input.getAccZ());
		
		// process key events (back button)
		ArrayList<KeyEvent> keyEvents = input.getKeyEvents();
		for(int i = keyEvents.size()-1; i >= 0; --i) {
			KeyEvent event = keyEvents.get(i);
			
			if(event.keyCode == android.view.KeyEvent.KEYCODE_BACK) {
				game.setScreen(new MainMenuScreen(game));
			}
		}
		
		BackgroundStars.update(deltaTime);
	}

	@Override
	public void present(int deltaTime) {
		Graphics g = game.getGraphics();
		
		g.clear(MainMenuScreen.BGCOLOR);
		BackgroundStars.present(deltaTime, g);
		g.drawPixmap(Assets.back, backBounds.left, backBounds.top);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		
	}

}
