package com.jcf.spaceshooter.model;

public class Bazooka extends Gun {

	public Bazooka(Bullets bullets, int swidth, int sheight) {
		super(swidth,sheight,bullets);
		interval = 200;
	}
	
	@Override
	public boolean fire(int x, int y, float rot ) {
		
		if(!super.fire(x, y, rot))
			return false;
		
		float asd = (float) (rot / 1.30 * Math.PI + 0.01*(Math.random() - 0.5));
		float v = 0.50f;
		bullets.add(new Rocket(x, y, (float)Math.sin(asd)*v, -(float)Math.cos(asd)*v, width, height));
		return true;
	}
}
