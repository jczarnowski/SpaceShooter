package com.jcf.spaceshooter.screen;

import com.jcf.spaceshooter.AndroidGame;
import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Audio;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Graphics.PixmapFormat;
import com.jcf.spaceshooter.engine.Music;
import com.jcf.spaceshooter.engine.Pixmap;

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
		Assets.exit = g.newPixmap("exit.png", PixmapFormat.ARGB4444);	
		Assets.logo = g.newPixmap("logo.jpg", PixmapFormat.ARGB4444);
		Assets.options_text = g.newPixmap("options.png", PixmapFormat.ARGB4444);
		Assets.options_onoff = g.newPixmap("onoff.png", PixmapFormat.ARGB4444);
		Assets.options_input = g.newPixmap("touchaccel.png", PixmapFormat.ARGB4444);
		Assets.back = g.newPixmap("back.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		Assets.numbersYellow = g.newPixmap("numbersYellow.png", PixmapFormat.ARGB4444);
		Assets.gameover = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
		Assets.highscores = g.newPixmap("highscores.png", PixmapFormat.ARGB4444);
		Assets.asteroid = new Pixmap[6];
		for(int i = 1; i <=6; ++i) Assets.asteroid[i-1] = g.newPixmap("asteroid" + i + ".png", PixmapFormat.ARGB4444);
		Assets.asteroid_gray = new Pixmap[6];
		for(int i = 1; i <=6; ++i) Assets.asteroid_gray[i-1] = g.newPixmap("asteroidgray" + i + ".png", PixmapFormat.ARGB4444);
		Assets.bullet = g.newPixmap("bullet.png", PixmapFormat.ARGB4444);
		Assets.pad = g.newPixmap("pad.png", PixmapFormat.ARGB4444);
		Assets.planet = g.newPixmap("planet.png", PixmapFormat.ARGB4444);
		Assets.rocket = g.newPixmap("rocket.png", PixmapFormat.ARGB4444);
		Assets.star = new Pixmap[2];
		for(int i = 1; i <= 2; ++i) Assets.star[i-1] = g.newPixmap("star" + i + ".png", PixmapFormat.ARGB4444);
		
		Assets.spark = g.newPixmap("spark.png", PixmapFormat.ARGB4444);
		Assets.sparkBig = g.newPixmap("sparkBig.png", PixmapFormat.ARGB4444);
		Assets.sparkBigBlue = g.newPixmap("sparkBigBlue.png", PixmapFormat.ARGB4444);
		Assets.ufo = g.newPixmap("ufo.png", PixmapFormat.ARGB4444);
		Assets.ufo2 = g.newPixmap("ufo2.png", PixmapFormat.ARGB4444);
		Assets.bullet_rocket = g.newPixmap("bullet_rocket.png", PixmapFormat.ARGB4444);
		Assets.bonusMachinegun = g.newPixmap("bonusMachinegun.png", PixmapFormat.ARGB4444);
		Assets.bonusBazooka = g.newPixmap("bonusBazooka.png", PixmapFormat.ARGB4444);
		Assets.bonusUpgrade = g.newPixmap("bonusUpgrade.png", PixmapFormat.ARGB4444);
		Assets.bonusCrazy = g.newPixmap("bonusCrazy.png", PixmapFormat.ARGB4444);
		Assets.sparkBigRed = g.newPixmap("sparkBigRed.png", PixmapFormat.ARGB4444);
		Assets.fnf = g.newPixmap("fnf.png", PixmapFormat.ARGB4444);
		Assets.life = g.newPixmap("life.png", PixmapFormat.ARGB4444); 
		Assets.lifeUp = g.newPixmap("lifeUp.png", PixmapFormat.ARGB4444); 
		Assets.enemyBullet = g.newPixmap("enemyBullet.png", PixmapFormat.ARGB4444); 
		Assets.shield = new Pixmap[3];
		for(int i = 1; i <=3; ++i) Assets.shield[i-1] = g.newPixmap("shield" + i + ".png", PixmapFormat.ARGB4444);
		Assets.shieldUp = g.newPixmap("shieldup.png", PixmapFormat.ARGB4444);
		
		// load sounds
		Audio a = game.getAudio();
		Assets.click = a.newSound("click.wav");
		Assets.asteroid_expl = a.newSound("explo4.wav");
		Assets.pickup = a.newSound("pickup.wav");
		Assets.upgrade = a.newSound("upgrade.wav");
		Assets.hit = a.newSound("hit.wav");
		Assets.ufo_expl = a.newSound("explo2.wav");
		Assets.pew = a.newSound("pew.wav");
		Assets.death = a.newSound("death.wav");
		Assets.shieldup = a.newSound("shield_up.ogg");
		Assets.shielddown = a.newSound("shield_down.ogg");
		
		// load music
		Assets.music = new Music[4];
		for(int i = 1; i <= 4; ++i) Assets.music[i-1] = a.newMusic("music" + i + ".mp3");
		
		// load config from sd
		game.getConfig().loadSettings();
		
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
