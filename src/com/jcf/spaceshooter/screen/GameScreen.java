package com.jcf.spaceshooter.screen;

import java.util.ArrayList;

import android.util.Log;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.Graphics;
import com.jcf.spaceshooter.Input;
import com.jcf.spaceshooter.KeyEvent;

public class GameScreen extends Screen {

	public GameScreen(AndroidGame game) {
		super(game);
	}
	
	@Override
	public void update(float deltaTime) {
		Input input = game.getInput();
		
		// check out the sensor reading
		Log.d("asd", "sensor reading: " + input.getAccX() + ", " + input.getAccY() + ", " + input.getAccZ());
		
		// process key events (back button)
		ArrayList<KeyEvent> keyEvents = input.getKeyEvents();
		for(int i = keyEvents.size()-1; i >= 0; --i) {
			KeyEvent event = keyEvents.get(i);
			
			if(event.keyCode == android.view.KeyEvent.KEYCODE_BACK)
				game.setScreen(new MainMenuScreen(game));
		}
		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
	
		g.clear(0xFF00FF00);
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
