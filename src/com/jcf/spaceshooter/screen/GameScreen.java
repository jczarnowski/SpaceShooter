package com.jcf.spaceshooter.screen;

import java.util.ArrayList;

import android.graphics.Color;
import android.util.Log;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.Graphics;
import com.jcf.spaceshooter.Input;
import com.jcf.spaceshooter.KeyEvent;
import com.jcf.spaceshooter.model.Background;
import com.jcf.spaceshooter.model.EntityHandler;
import com.jcf.spaceshooter.model.TouchPad;

public class GameScreen extends Screen {

	Graphics g;
	Background bg;
	EntityHandler eh;
	TouchPad tp;
	
	public GameScreen(AndroidGame game) {
		super(game);
		g = game.getGraphics();
		tp = new TouchPad(g.getWidth(), g.getHeight(), 200, 200);
		bg = new Background(g.getWidth(), g.getHeight());
		eh = new EntityHandler(g.getWidth(), g.getHeight(), tp);
	}
	
	@Override
	public void update(int deltaTime) {
		Input input = game.getInput();
		
		//controll
		tp.update(input);
		
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
		tp.draw(g);
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
