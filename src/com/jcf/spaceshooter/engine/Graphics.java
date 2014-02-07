package com.jcf.spaceshooter.engine;

import java.io.IOException;
import java.io.InputStream;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Graphics {
	AssetManager assets;
	Bitmap framebuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();
	 private Typeface mFace; 
	 
	public static enum PixmapFormat {
		ARGB8888, ARGB4444, RGB565
	}
	
	public Graphics(AssetManager assets, Bitmap framebuffer) {
		this.assets = assets;
		this.framebuffer = framebuffer;
		this.canvas = new Canvas(framebuffer);
		this.paint = new Paint();
		mFace = Typeface.createFromAsset(assets,"kenvector_future.ttf");  
		paint.setTextSize(30);
		paint.setTypeface(mFace);
		paint.setAntiAlias(true);
	}
	
	public Pixmap newPixmap(String fileName, PixmapFormat format) {
		Config config = null;
		
		if (format == PixmapFormat.RGB565)
			config = Config.RGB_565;
		else if (format == PixmapFormat.ARGB4444)
			config = Config.ARGB_4444;
		else
			config = Config.ARGB_8888;
		
		Options options = new Options();
		options.inPreferredConfig = config;
		InputStream in = null;
		Bitmap bitmap = null;
		
		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in);
			if (bitmap == null)
				throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		
		if (bitmap.getConfig() == Config.RGB_565)
			format = PixmapFormat.RGB565;
		else if (bitmap.getConfig() == Config.ARGB_4444)
			format = PixmapFormat.ARGB4444;
		else
			format = PixmapFormat.ARGB8888;
		
		return new Pixmap(bitmap, format);
	}
	
	public void clear(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
				(color & 0xff));
	}	
	
	public void clearTransparent(int color) {
		canvas.drawARGB((color & 0xff000000) >> 24,(color & 0xff0000) >> 16, (color & 0xff00) >> 8,
				(color & 0xff));
	}
	
	public void drawPixel(int x, int y, int color) {
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}
	
	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}
	
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		canvas.drawRect(x, y, x + width - 1, y + width - 1, paint);
	}
	
	public void fillRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x + width - 1, y + width - 1, paint);
	}
	
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcWidth - 1;
		srcRect.bottom = srcY + srcHeight - 1;
		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcWidth - 1;
		dstRect.bottom = y + srcHeight - 1;
		canvas.drawBitmap(pixmap.bitmap, srcRect, dstRect, null);
	}
	
	public void drawPixmap(Pixmap pixmap, int x, int y) {
		canvas.drawBitmap(pixmap.bitmap, x, y, null);
	}	
	public void drawPixmap(Pixmap pixmap, int x, int y, float scale) {
		Matrix matrix = new Matrix();
		matrix.setScale(scale, scale);
		matrix.postTranslate(x, y);
		canvas.drawBitmap(pixmap.bitmap, matrix, null);
	}
	
	public void drawRotatedPixmap(Pixmap pixmap, int x, int y, float angle) {
		Matrix matrix = new Matrix();
		matrix.postTranslate(-pixmap.getWidth() / 2, -pixmap.getHeight() / 2); // Centers image
		matrix.postRotate(angle);
		matrix.postTranslate(x+pixmap.getWidth()/2, y+pixmap.getHeight()/2);
		canvas.drawBitmap(pixmap.bitmap, matrix, null);
	}
	
	/*
	 * Draw a number using default font.
	 * The number must be positive
	 */
	public void drawNumber(int x, int y, int number) {
		if(number < 0)
			throw new IllegalArgumentException("number has to be positive!");
		
		String nr = Integer.toString(number);
		
		for(int i = 0; i < nr.length(); ++i)
			drawSingleNumber(x+i*22, y, nr.charAt(i));
	}
	
	public void drawText(int x, int y, String text, int color) {
		paint.setColor(color);
		canvas.drawText(text, x, y, paint);
	}
	
	public int measureText(String text) {
		return (int) paint.measureText(text);
	}
	
	public void drawNumberYellow(int x, int y, int number) {
		Pixmap workaround = Assets.numbers;
		Assets.numbers = Assets.numbersYellow;
		
		drawNumber(x,y,number);
		Assets.numbers = workaround;
	}
	/*
	 * draws numbers from 0-9 and a dot
	 */
	public void drawSingleNumber(int x, int y, char num) {
		if(num != '.' && (num < 48 || num > 57))
			throw new IllegalArgumentException("char out of range");
		
		int mul = num - 48;
		if(num == '.')
			mul = 10;
		
		drawPixmap(Assets.numbers, x, y, mul*22, 0, 22, Assets.numbers.getHeight());
	}
	
	public int getWidth() {
		return framebuffer.getWidth();
	}
	
	public int getHeight() {
		return framebuffer.getHeight();
	}
}
