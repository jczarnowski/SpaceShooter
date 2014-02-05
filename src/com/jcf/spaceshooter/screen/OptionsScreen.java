package com.jcf.spaceshooter.screen;

import java.util.ArrayList;

import android.graphics.Rect;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Input;
import com.jcf.spaceshooter.engine.KeyEvent;
import com.jcf.spaceshooter.engine.ShuttleController;
import com.jcf.spaceshooter.engine.TouchEvent;

public class OptionsScreen extends Screen {
	Rect soundBounds;
	Rect inputBounds;
	Rect clearBounds;
	Rect backBounds;
	int textX, textY;
	
	public OptionsScreen(AndroidGame game) {
		super(game);
		
		Graphics g = game.getGraphics();
		
		textX = (g.getWidth()-Assets.options_text.getWidth())/2;
		textY = (g.getHeight()-Assets.options_text.getHeight())/2;
		
		soundBounds = new Rect(textX, textY+69, textX+330, textY+118);
		inputBounds = new Rect(textX, textY+127, textX+420, textY+183);
		clearBounds = new Rect(textX, textY+198, textX+316, textY+248);
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
				int x = input.getTouchX(i);
				int y = input.getTouchY(i);
				
				if(inBounds(x, y, soundBounds)) {
					if(!game.getConfig().soundOn) Assets.click.play(0.1f);;
					game.getConfig().soundOn = !game.getConfig().soundOn;
				}
				if(inBounds(x, y, inputBounds)) {
					game.getConfig().controlMethod = game.getConfig().controlMethod == ShuttleController.CONTROL_TOUCH ? 
							ShuttleController.CONTROL_ACCEL : ShuttleController.CONTROL_TOUCH;
					if(game.getConfig().soundOn) Assets.click.play(0.1f);;
				}
				if(inBounds(x, y, clearBounds)) {
					if(game.getConfig().soundOn) Assets.click.play(0.1f);;
					game.getConfig().clearHighScores();
				}
				if(inBounds(x, y, backBounds)) {
					if(game.getConfig().soundOn) Assets.click.play(0.1f);;
					game.getConfig().saveSettings();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
		
		// process key events (back button)
		ArrayList<KeyEvent> keyEvents = input.getKeyEvents();
		for(int i = keyEvents.size()-1; i >= 0; --i) {
			KeyEvent event = keyEvents.get(i);
			
			if(event.keyCode == android.view.KeyEvent.KEYCODE_BACK) {
				game.getConfig().saveSettings();
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
		
		BackgroundStars.update(deltaTime);
	}

	@Override
	public void present(int deltaTime) {
		Graphics g = game.getGraphics();
		
		g.clear(MainMenuScreen.BGCOLOR);
		
		BackgroundStars.present(deltaTime, g);
		g.drawPixmap(Assets.options_text, textX, textY);
		
		int mod = 1;
		if(game.getConfig().soundOn)
			mod = 0;
		
		g.drawPixmap(Assets.options_onoff, textX+120, textY+69, 
				mod*Assets.options_onoff.getWidth()/2, 0, Assets.options_onoff.getWidth()/2, Assets.options_onoff.getHeight());
		
		mod = 1;
		if(game.getConfig().controlMethod == ShuttleController.CONTROL_TOUCH)
			mod = 0;
		
		g.drawPixmap(Assets.options_input, textX+249, textY+139,
				mod*6*18, 0, (5+mod*8)*18, Assets.options_input.getHeight());
		
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
