package com.jcf.spaceshooter.screen;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.Assets;
import com.jcf.spaceshooter.Graphics;
import com.jcf.spaceshooter.Graphics.PixmapFormat;

/*
 * Loading screen loads game assets and configuration
 * Also, it displays a nice logo.
 */
public class LoadingScreen extends Screen {

	public LoadingScreen(AndroidGame game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		
		// load pictures
		Assets.logo = g.newPixmap("logo.jpg", PixmapFormat.ARGB4444);
		Assets.menuText = g.newPixmap("menu.png", PixmapFormat.ARGB4444);
		
		// load sounds
		
		// load config from sd
		
		// set the next screen
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
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
