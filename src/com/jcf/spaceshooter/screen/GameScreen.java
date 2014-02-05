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
import com.jcf.spaceshooter.engine.Music;
import com.jcf.spaceshooter.engine.ShuttleController;
import com.jcf.spaceshooter.engine.TouchController;
import com.jcf.spaceshooter.engine.TouchEvent;
import com.jcf.spaceshooter.model.Background;
import com.jcf.spaceshooter.model.EntityHandler;

public class GameScreen extends Screen {
	private static final int PLAYING = 0;
	private static final int GAMEOVER = 1;
	private int state = PLAYING;
	ShuttleController shuttleController;
	Graphics g;
	Background bg;
	EntityHandler eh;
	Music activemusic;
	
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
		
		// random music
		activemusic = Assets.music[(int) (Math.random()*3)];
		activemusic.setLooping(true);
		activemusic.setVolume(0.3f);
	}

	@Override
	public void update(int deltaTime) {

		Log.d("asd", Float.toString(1000.f/deltaTime));

		Input input = game.getInput();

		if(game.getConfig().soundOn)
			activemusic.play();
		else
			activemusic.stop();
		
		if(state == PLAYING) {
			//controll
			shuttleController.ControlShuttle(eh.getShuttle());
	
			//model update
			//background
			bg.update(deltaTime);
			//entity handler
			eh.update(deltaTime);
		} else {
			ArrayList<TouchEvent> touchEvents = input.getTouchEvents();
			for(TouchEvent e : touchEvents) {
				if(e.type == TouchEvent.TOUCH_DOWN) {
					game.setScreen(new MainMenuScreen(game));
					game.getConfig().addScore(eh.getPoints());
				}
			}
		}
		// process key events (back button)
		ArrayList<KeyEvent> keyEvents = input.getKeyEvents();
		for(int i = keyEvents.size()-1; i >= 0; --i) {
			KeyEvent event = keyEvents.get(i);

			if(event.keyCode == android.view.KeyEvent.KEYCODE_BACK) {
				game.setScreen(new MainMenuScreen(game));
				game.getConfig().addScore(eh.getPoints());
			}
		}

		// check if player is alive
		if(!eh.getShuttle().isAlive()) {
			state = GAMEOVER;
		}
	}

	@Override
	public void present(int deltaTime) {

		g.clear(MainMenuScreen.BGCOLOR);
		Graphics g = game.getGraphics();

		bg.draw(g);
		eh.draw(g);
		
		if(state == GAMEOVER) {
			int x = (g.getWidth() - Assets.gameover.getWidth())/2;
			int y = (g.getHeight() - Assets.gameover.getHeight())/2;	
			
			g.drawPixmap(Assets.gameover, x, y);
			g.drawNumber(x + 145, y + 106, eh.getPoints());
		}

	}

	@Override
	public void pause() {
		activemusic.stop();
	}

	@Override
	public void resume() {
		g = game.getGraphics();
		bg = new Background(g.getWidth(), g.getHeight());
		eh = new EntityHandler(g.getWidth(), g.getHeight());
		activemusic.play();
	}

	@Override
	public void dispose() {
		if(game.isFinishing())
			activemusic.stop();
	}

}
