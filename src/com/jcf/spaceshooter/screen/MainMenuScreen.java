package com.jcf.spaceshooter.screen;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.Assets;
import com.jcf.spaceshooter.Graphics;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(AndroidGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.clear(0xFFFFFF);
		
		int x = (g.getWidth() - Assets.menuText.getWidth())/2;
		int y = (g.getHeight() - Assets.menuText.getHeight())/2;
		
		g.drawPixmap(Assets.menuText, x, y);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
