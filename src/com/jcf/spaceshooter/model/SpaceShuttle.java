package com.jcf.spaceshooter.model;


import com.jcf.spaceshooter.engine.Graphics;
import com.jcf.spaceshooter.engine.Pixmap;
import com.jcf.spaceshooter.engine.Assets;

public class SpaceShuttle extends InteractiveSpaceObject{

	private Gun activeGun, machineGun, bazooka, crazyGun;
	private float acc = 0.01f;

	private float desiredVx = 0;

	private Pixmap pixmapHp;
	
	public void setVx(float d) {
		desiredVx = d;
	}

	public SpaceShuttle (int screenWidth, int screenHeight , Bullets bullets) 
	{
		super(screenWidth/2,(int)(screenHeight*0.9),0f,0f, screenWidth, screenHeight , Assets.rocket);
		hp = 7;
		machineGun = new MachineGun(bullets, swidth, sheight);
		bazooka = new Bazooka(bullets, swidth, sheight);
		crazyGun = new CrazyGun(bullets, swidth, sheight);
		activeGun = machineGun;
		pixmapHp = Assets.life;
	}

	public void addBonus(BonusDrop bonus)
	{
		switch(bonus.bonusType)
		{
		case BonusDrop.SWITCH:		
			switch(bonus.gunType)
			{
			case Gun.MACHINE_GUN:
				activeGun = machineGun;
				break;
			case Gun.BAZOOKA:
				activeGun = bazooka;
				break;
			case Gun.CRAZY:
				activeGun = crazyGun;
				break;
			}
			break;
		case BonusDrop.UPGRADE:
			bazooka.upgrade();
			machineGun.upgrade();
			crazyGun.upgrade();
			break;
		case BonusDrop.LIFEUP:
			hp++;
			break;
		}


	}

	public boolean update(int time) {

		activeGun.fire((int)x,(int)y,rot);

		rot = (float)(Math.atan2(vx,-vy + 200)*180/3.14);

		if(vx < desiredVx)
		{
			vx = Math.min(vx+acc*time,desiredVx);
		}
		else
			if(vx > desiredVx)
			{
				vx = Math.max(vx-acc*time,desiredVx);
			}




		super.update(time);

		if (x < pixmap.getWidth()/2) {
			x = pixmap.getWidth()/2;
		}
		if (x > swidth - pixmap.getWidth()/2 ) {
			x = swidth - pixmap.getWidth()/2;
		}		

		if (y < pixmap.getHeight()/2) {
			y = pixmap.getHeight()/2;
		}
		if (y > sheight - pixmap.getHeight()/2) {
			y = sheight - pixmap.getHeight()/2;
		}
		return true;
	}

	@Override
	public void colisionDetected(InteractiveSpaceObject object) {
		super.colisionDetected(object);
		if(object instanceof Enemy)
			hp --;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for(int i = 0; i< hp; i++)
			g.drawPixmap(pixmapHp, 20*i+5, 5);
	}

}
