package com.jcf.spaceshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* Class RenderView is the content view of our main activity.
 * It starts a separate thread for handling the game logic and 
 * controls its lifecycle. The current screen is acquired by
 * AndroidGame pointer and then updated and presented.
 */
public class RenderView extends SurfaceView implements Runnable {
	AndroidGame game;
	SurfaceHolder holder;
	Thread renderThread = null;
	Bitmap framebuffer;
	volatile boolean running = false;
	
	
	public RenderView(AndroidGame game, Bitmap framebuffer) {
		super(game);
		this.game = game;
		this.holder = getHolder();
		this.framebuffer = framebuffer;
	}
	
	public void run() {
		long startTime = System.nanoTime();
		Rect dstRect = new Rect();
		
		while(running) {
			if(!holder.getSurface().isValid())
				continue;
			
			int dt = (int)(System.currentTimeMillis() - startTime);
			
			startTime = System.currentTimeMillis();
			
			game.getCurrentScreen().update(dt);
			game.getCurrentScreen().present(dt);
			
			// draw our framebuffer to the canvas
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(framebuffer, null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);
		}
		
	}
	
	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	public void pause() {
		running = false;
		while(true) {
			try {
				renderThread.join();
				break;
			} catch (InterruptedException e) {
			
			}
		}
	}
}
