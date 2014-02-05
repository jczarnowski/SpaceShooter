package com.jcf.spaceshooter.screen;

import java.util.ArrayList;

import android.graphics.Color;
import android.util.Log;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.engine.AccelController;
import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Input;
import com.jcf.spaceshooter.engine.KeyEvent;
import com.jcf.spaceshooter.engine.ShuttleController;
import com.jcf.spaceshooter.engine.TouchController;
import com.jcf.spaceshooter.model.Background;
import com.jcf.spaceshooter.model.EntityHandler;

public class GameScreen extends Screen {

	ShuttleController shuttleController;
	Graphics g;
	Background bg;
	EntityHandler eh;

	public GameScreen(AndroidGame game) {
		super(game);
		g = game.getGraphics();
		bg = new Background(g.getWidth(), g.getHeight());
		eh = new EntityHandler(g.getWidth(), g.getHeight());
		switch(game.getConfig().controlMethod)
		{
		case ShuttleController.CONTROL_TOUCH:
			shuttleController = new TouchController(game.getInput(), g.getWidth());
			break;
		case ShuttleController.CONTROL_ACCEL: 
			shuttleController = new AccelController(game.getInput());
			break;
		}

		Assets.menumusic.setLooping(true);
		Assets.menumusic.setVolume(0.1f);
	}

	@Override
	public void update(int deltaTime) {

		Log.d("asd", Float.toString(1000.f/deltaTime));

		Input input = game.getInput();

		if(game.getConfig().soundOn)
			Assets.menumusic.play();
		else
			Assets.menumusic.stop();
		
		//controll
		shuttleController.ControlShuttle(eh.getShuttle());

		//model update
		//background
		bg.update(deltaTime);
		//entity handler
		eh.update(deltaTime);

		// process key events (back button)
		ArrayList<KeyEvent> keyEvents = input.getKeyEvents();
		for(int i = keyEvents.size()-1; i >= 0; --i) {
			KeyEvent event = keyEvents.get(i);

			if(event.keyCode == android.view.KeyEvent.KEYCODE_BACK)
				game.setScreen(new MainMenuScreen(game));
		}


	}

	@Override
	public void present(int deltaTime) {

		g.clear(Color.BLACK);
		Graphics g = game.getGraphics();

		bg.draw(g);
		eh.draw(g);

	}

	@Override
	public void pause() {
		Assets.menumusic.stop();
	}

	@Override
	public void resume() {
		g = game.getGraphics();
		bg = new Background(g.getWidth(), g.getHeight());
		eh = new EntityHandler(g.getWidth(), g.getHeight());
		Assets.menumusic.play();
	}

	@Override
	public void dispose() {
		if(game.isFinishing())
			Assets.menumusic.stop();
	}

}
