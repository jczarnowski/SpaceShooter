package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import android.util.Log;
import android.view.MotionEvent;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Input;
import com.jcf.spaceshooter.engine.Pixmap;
import com.jcf.spaceshooter.engine.TouchEvent;

public class TouchPad{

	private float x1, y1, x2, y2, sumx, sumy, midx1, midx2, midy, width, height;
	Pixmap pixmap;
	int swidth;
	
	public float get_x1()
	{
		return x1;
	}

	public float get_y1()
	{
		return y1;
	}	
	
	public float get_sumx()
	{
		return sumx;
	}

	public float get_sumy()
	{
		return sumy;
	}	

	public float get_x2()
	{
		return x2;
	}

	public float get_y2()
	{
		return y2;
	}

	public TouchPad(int swidth, int sheight, float w, float h)
	{
		
		this.midx1 = w*0.6f;
		this.midx2 = swidth - w*0.6f;
		this.midy = sheight - h*0.6f;
		this.x1 = 0;
		this.x2 = 0;
		this.y1 = this.y2 = 0;		
		this.swidth = swidth;
		
		width = w;
		height = h;
		sumx = 0;
		sumy = 0;
		pixmap = Assets.pad;

	}


	public void draw(Graphics g)
	{
		g.drawPixmap(pixmap, (int)(x1 + midx1 - pixmap.getWidth()/2), (int)(y1 + midy - pixmap.getHeight()/2));
		g.drawPixmap(pixmap, (int)(x2 + midx2 - pixmap.getWidth()/2), (int)(y2 + midy - pixmap.getHeight()/2));
	}


	public void update(Input input) {
		
		ArrayList<TouchEvent> e = input.getTouchEvents();
		float x,y;
		float touchX ;
		
		for(int i = 0; i <e.size();i++)
		{	


			switch (e.get(i).type) {
				case MotionEvent.ACTION_MOVE:
				{
					sumx = (float)(sumx*0.95 + 0.05*e.get(i).x);
					sumy = (float)(sumy*0.95 + 0.05*e.get(i).y);

					if(e.get(i).x < swidth/2)
						touchX = e.get(i).x - midx1;
					else
						touchX = e.get(i).x - midx2;
			
					float touchY = e.get(i).y - midy;
			
					if(touchX*touchX + touchY*touchY > width*width/4)
					{
						float a = (float) Math.atan2(touchY, touchX);
						x = (float) (width*Math.cos(a)/2);
						y = (float) (width*Math.sin(a)/2);
					}
					else
					{
						y = touchY;
						x = touchX;
					}
			
					if(e.get(i).x < swidth/2)
					{	
						x1 = x;
						y1 = y;
					}
					else
					{
		
						x2 = x;
						y2 = y;
					}
	
					break;
				}
					
				case MotionEvent.ACTION_UP:
				{
					Log.d("tp","up " + e.get(i).x );
					x1 = 0;
					y1 = 0;
					x2 = 0;
					y2 = 0;
					break;
				}
				
				case MotionEvent.ACTION_POINTER_UP:
					Log.d("tp","pointer_up " + e.get(i).x );
					break;				
				case MotionEvent.ACTION_CANCEL:
					Log.d("tp","cancel " + e.get(i).x );
					break;				
				case MotionEvent.ACTION_POINTER_1_DOWN:
					Log.d("tp","pointer 1 down " + e.get(i).x );
					break;				
				case MotionEvent.ACTION_OUTSIDE:
					Log.d("tp","outside " + e.get(i).x );
					break;
			}
		}
	}
}
