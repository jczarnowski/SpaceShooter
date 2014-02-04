package com.jcf.spaceshooter.screen;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Graphics.PixmapFormat;

/*
 * Loading screen loads game assets and configuration
 * Also, it displays a nice logo.
 */
public class LoadingScreen extends Screen {

	public LoadingScreen(AndroidGame game) {
		super(game);
	}

	@Override
	public void update(int deltaTime) {
		Graphics g = game.getGraphics();
		
		// load pictures
		Assets.logo = g.newPixmap("logo.jpg", PixmapFormat.ARGB4444);
		Assets.menuText = g.newPixmap("menu.png", PixmapFormat.ARGB4444);
		Assets.sound = g.newPixmap("sound.png", PixmapFormat.ARGB4444);
		Assets.nosound = g.newPixmap("nosound.png", PixmapFormat.ARGB4444);
		Assets.exit = g.newPixmap("exit.png", PixmapFormat.ARGB4444);	Assets.logo = g.newPixmap("logo.jpg", PixmapFormat.ARGB4444);
		Assets.options_text = g.newPixmap("options.png", PixmapFormat.ARGB4444);
		Assets.options_onoff = g.newPixmap("onoff.png", PixmapFormat.ARGB4444);
		Assets.options_input = g.newPixmap("touchaccel.png", PixmapFormat.ARGB4444);
		Assets.back = g.newPixmap("back.png", PixmapFormat.ARGB4444);
		Assets.asteroid1 = g.newPixmap("asteroid.png", PixmapFormat.ARGB4444);
		Assets.asteroid2 = g.newPixmap("asteroid2.png", PixmapFormat.ARGB4444);
		//Assets.ball = g.newPixmap("ball.png", PixmapFormat.ARGB4444);
		Assets.bullet = g.newPixmap("bullet.png", PixmapFormat.ARGB4444);
		Assets.pad = g.newPixmap("pad.png", PixmapFormat.ARGB4444);
		Assets.planet = g.newPixmap("planet.png", PixmapFormat.ARGB4444);
		Assets.rocket = g.newPixmap("rocket.png", PixmapFormat.ARGB4444);
		Assets.star = g.newPixmap("star.png", PixmapFormat.ARGB4444);
		Assets.spark = g.newPixmap("spark.png", PixmapFormat.ARGB4444);
		Assets.sparkBig = g.newPixmap("sparkBig.png", PixmapFormat.ARGB4444);
		Assets.ufo = g.newPixmap("ufo.png", PixmapFormat.ARGB4444);
		
		// load sounds
		
		// load config from sd
		
		// set the next screen
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(int deltaTime) {
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
