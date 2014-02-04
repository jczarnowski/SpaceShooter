package com.jcf.spaceshooter.screen;

import java.util.ArrayList;
import java.util.Random;

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
		
		if(game.getConfig().soundOn)
			Assets.menumusic.play();
		else
			Assets.menumusic.stop();
		
		// process touch events
		ArrayList<TouchEvent> eventList = input.getTouchEvents();
		for(int i = eventList.size()-1; i >= 0; --i) {
			TouchEvent event = eventList.get(i);
			
			if(event.type == TouchEvent.TOUCH_DOWN) {
				Random gen = new Random();
				game.getConfig().addScore(Math.abs(gen.nextInt()));
				
				int x = input.getTouchX(i);
				int y = input.getTouchY(i);
				
				if(inBounds(x, y, backBounds)) {
					game.setScreen(new MainMenuScreen(game));
					if(game.getConfig().soundOn) Assets.click.play(1);
				}
			}
		}
		
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
		
		g.drawPixmap(Assets.highscores, (g.getWidth()-Assets.highscores.getWidth())/2, 90);
		
		// draw the scores
		int highscore_num_x = 230;
		int highscore_start_y = 150;
		int highscore_linegap = 30;
		int highscore_numgap = 30;
		
		int[] highscores = game.getConfig().highscores;
		for(int i = 0; i < highscores.length; ++i) {
			int x = highscore_num_x;
			int y = highscore_start_y + i*(21+highscore_linegap);
			g.drawSingleNumber(x, y, Integer.toString(i+1).charAt(0));
			x += 21;
			g.drawSingleNumber(x, y, '.');
			
			x += 21 + highscore_numgap;
			
			g.drawNumber(x, y, highscores[i]);
		}
		
		g.drawPixmap(Assets.back, backBounds.left, backBounds.top);
	}

	@Override
	public void pause() {
		Assets.menumusic.stop();
	}

	@Override
	public void resume() {
		Assets.menumusic.play();
	}

	@Override
	public void dispose() {
		
	}

}
