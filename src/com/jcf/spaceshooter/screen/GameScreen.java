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
	int nextLevelTime;
	int level;

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
		
		level = 0;
		nextLevelTime = setLevel(level);
	}

	private int setLevel(int level) {
		int ntlvl = 10000;
		
		eh.lvlUp(level);
		switch(level)
		{
		case 0:
			bg.setSpeed(0.2f);
			bg.setColor(MainMenuScreen.BGCOLOR);
		break;
		case 1:
			bg.setSpeed(0.6f);
			bg.setColor(0xff002e63);
			break;
		case 2:
			bg.setSpeed(0.65f);
			bg.setColor(0xff528036);
			break;
		case 3:
			bg.setSpeed(0.7f);
			bg.setColor(0xfffe2712);
			break;
		case 4:
			bg.setSpeed(1f);
			bg.setColor(MainMenuScreen.BGCOLOR);
			break;
		}
		
		return ntlvl;
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
			shuttleController.ControlShuttle(eh.getShuttle()) ;

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
		
		//level managment
		if(state == PLAYING)
			nextLevelTime-=deltaTime;
		
		if(nextLevelTime<0)
		{
			nextLevelTime = setLevel(++level);
		}
	}

	@Override
	public void present(int deltaTime) {
		
//		bg clears screen
//		g.clear(MainMenuScreen.BGCOLOR);

		bg.draw(g);
		eh.draw(g);

		if(state == GAMEOVER) {
			int x = (g.getWidth() - Assets.gameover.getWidth())/2;
			int y = (g.getHeight() - Assets.gameover.getHeight())/2;	

			g.drawPixmap(Assets.gameover, x, y);
			g.drawText(x + 145, y + 95, Integer.toString(eh.getPoints()), 0xffa4a4a4);
		}
		
		g.drawText(g.getWidth()/2 - 200, 25, "level: " + level+ ",  next:" + Float.toString((float)((int)(nextLevelTime/10)/100f)), Color.WHITE);

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
