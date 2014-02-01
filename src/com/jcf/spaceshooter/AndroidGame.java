package com.jcf.spaceshooter;

import com.jcf.spaceshooter.screen.LoadingScreen;
import com.jcf.spaceshooter.screen.MainMenuScreen;
import com.jcf.spaceshooter.screen.Screen;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class AndroidGame extends Activity {
	Graphics graphics;		// graphics object to load and draw stuff
	RenderView renderView;	// a SurfaceView for displaying our framebuffer and main loop
	WakeLock wakeLock;		// wakeLock to keep the screen alive
	Screen screen;			// the current screen
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// request no title and fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// get a WakeLock instance
		PowerManager powerManager = (PowerManager)
				getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
		
		// initialize stuff
		int fbWidth = getWindowManager().getDefaultDisplay().getWidth();
		int fbHeight = getWindowManager().getDefaultDisplay().getHeight();
		Bitmap framebuffer = Bitmap.createBitmap(fbWidth, fbHeight, Config.RGB_565);
		
		graphics = new Graphics(getAssets() ,framebuffer);
		renderView = new RenderView(this, framebuffer);
		
		// set our renderView as content view
		setContentView(renderView);
		
		// set the current screen
		screen = new LoadingScreen(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_game, menu);
		return true;
	}

	@Override
	protected void onPause() {
		wakeLock.release();
		renderView.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		wakeLock.acquire();
		renderView.resume();
		super.onResume();
	}

	/*
	 * Returns the current active screen
	 */
	public Screen getCurrentScreen() {
		return screen;
	}
	
	/* 
	 * Sets a new active screen
	 */
	public void setScreen(Screen screen) {
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");
		
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		
		this.screen = screen;
	}
	
	public Graphics getGraphics() {
		return graphics;
	}
}
