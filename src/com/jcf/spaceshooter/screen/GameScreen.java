package com.jcf.spaceshooter.screen;

import java.util.ArrayList;

import android.util.Log;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Input;
import com.jcf.spaceshooter.engine.KeyEvent;
import com.jcf.spaceshooter.engine.ShuttleController;
import com.jcf.spaceshooter.engine.TouchController;

public class GameScreen extends Screen {
	ShuttleController shuttleController;
	
	public GameScreen(AndroidGame game) {
		super(game);
		
		//if(Game.Config.ControlMethod = ShuttleControler.CONTROL_TOUCH)
		shuttleController = new TouchController(game.getInput());
	}
	
	@Override
	public void update(float deltaTime) {
		Input input = game.getInput();
		
		// control the shuttle
		//shuttleController.ControlShuttle(shuttle)
		
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
