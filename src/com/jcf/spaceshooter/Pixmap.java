package com.jcf.spaceshooter;

import android.graphics.Bitmap;

import com.jcf.spaceshooter.Graphics.PixmapFormat;

/*
 * simple wrapper to the bitmap type.
 * adds format info.
 */
public class Pixmap {
	Bitmap bitmap;
	PixmapFormat format;

	public Pixmap(Bitmap bitmap, PixmapFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}
	
	public int getWidth() {
		return bitmap.getWidth();
	}
	
	public int getHeight() {
		return bitmap.getHeight();
	}
	
	public PixmapFormat getFormat() {
		return format;
	}
	
	public void dispose() {
		bitmap.recycle();
	}
}
