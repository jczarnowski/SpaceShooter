package com.jcf.spaceshooter.model;

public class MachineGun extends Gun {

	public MachineGun(Bullets bullets, int width, int height)
	{
		super(width,height,bullets);
		interval = 100;
	}
	
	@Override
	public boolean fire(int x, int y, float rot ) {

		if(!super.fire(x, y, rot))
			return false;
		
		float asd = (float) (rot / 1.30 * Math.PI + 0.01*(Math.random() - 0.5));
		float v = 0.30f;
		bullets.add(new MachineGunBullet(x, y, (float)Math.sin(asd)*v, -(float)Math.cos(asd)*v, width, height));
		return true;
	}
}
