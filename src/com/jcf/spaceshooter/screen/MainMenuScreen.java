package com.jcf.spaceshooter.screen;

import java.util.ArrayList;

import android.graphics.Rect;
import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.Assets;
import com.jcf.spaceshooter.Graphics;
import com.jcf.spaceshooter.Input;
import com.jcf.spaceshooter.KeyEvent;
import com.jcf.spaceshooter.MultiTouchHandler;
import com.jcf.spaceshooter.TouchEvent;

public class MainMenuScreen extends Screen {
	private static final int BGCOLOR = 0xFF2868b8;
	
	boolean sound = true;
	int textX, textY;
	int buttonWidth, buttonHeight;
	Rect playBounds;
	Rect scoreBounds;
	Rect optionsBounds;
	Rect helpBounds;
	Rect soundBounds;
	Rect exitBounds;
	
	public MainMenuScreen(AndroidGame game) {
		super(game);
		
		Graphics g = game.getGraphics();
		textX = (g.getWidth() - Assets.menuText.getWidth())/2;
		textY = (g.getHeight() - Assets.menuText.getHeight())/2;
		buttonHeight = Assets.menuText.getHeight()/4;
		buttonWidth = Assets.menuText.getWidth();
		
		playBounds = new Rect(textX, textY, textX + buttonWidth, textY + buttonHeight);
		scoreBounds = new Rect(textX, textY, textX + buttonWidth, textY + 2*buttonHeight);
		optionsBounds = new Rect(textX, textY, textX + buttonWidth, textY + 3*buttonHeight);
		helpBounds = new Rect(textX, textY, textX + buttonWidth, textY + 4*buttonHeight);
		exitBounds = new Rect(10, g.getHeight()-10-Assets.exit.getHeight(), 10+Assets.exit.getWidth(), g.getHeight()-10);
		
		int soundX = g.getWidth() - 10 - Assets.sound.getWidth();
		int soundY = g.getHeight() - 10 - Assets.sound.getHeight();
		soundBounds = new Rect(soundX, soundY, soundX+Assets.sound.getWidth(), soundY+Assets.sound.getHeight());
	
		
	}

	@Override
	public void update(float deltaTime) {
		Input input = game.getInput();
		
		ArrayList<TouchEvent> eventList = input.getTouchEvents();
		
		for(int i = eventList.size()-1; i >= 0; --i) {
			TouchEvent event = eventList.get(i);
			
			if(event.type == TouchEvent.TOUCH_DOWN) {
				int x = input.getTouchX(i);
				int y = input.getTouchY(i);
				
				if(inBounds(x, y, playBounds))
					game.setScreen(new GameScreen(game));
				if(inBounds(x, y, soundBounds))
					sound = !sound;
				if(inBounds(x, y, exitBounds))
					game.finish();
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.clear(BGCOLOR);
		g.drawPixmap(Assets.menuText, textX, textY);
		g.drawPixmap(Assets.exit, exitBounds.left, exitBounds.top);
		
		if(sound)
			g.drawPixmap(Assets.sound, soundBounds.left, soundBounds.top);
		else
			g.drawPixmap(Assets.nosound, soundBounds.left, soundBounds.top);
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
