package com.jcf.spaceshooter.model;

import android.util.Log;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Pixmap;

public class Enemy extends InteractiveSpaceObject {

	protected EnemyBullet bullet;
	
	public Enemy(int x, int y, float vx, float vy, int screenWidth,
			int screenHeight, Pixmap pixmap) {
		super(x, y, vx, vy, screenWidth, screenHeight, pixmap);

		initialHp = hp;
	}

	public boolean colisionDetection(InteractiveSpaceObject object) {
		if(object instanceof Enemy) return false;
		return (super.colisionDetection(object));
	}

	public void colisionDetected(InteractiveSpaceObject object)
	{

		if(object instanceof Enemy ||
				object instanceof EnemyBullet) return;

		if(object instanceof Bullet)
		{
			hp -= object.power;
			points = object.power;
			if(hp <= 0)
			{
				bunusGeneration();
				//explosion
				pe.add(new ParticleEmitter(0, 200,(int)x,(int)y, vx, vy,imageWidth,0.80f,0f,(float)Math.PI*2f, 10f, swidth, sheight, Assets.sparkBig));
			}
			else
				pe.add(new ParticleEmitter(0, 200,(int)object.getX(),(int)object.getY(),0.80f,(float)Math.atan2(object.getY() - y, object.getX() -x),1, 0.2f, swidth, sheight, Assets.spark));
		}
		else
			if(object instanceof SpaceShuttle)
			{
				hp = 0;
				pe.add(new ParticleEmitter(0, 200,(int)x,(int)y, vx, vy,imageWidth,0.80f,0f,(float)Math.PI*2f, 30f, swidth, sheight, Assets.sparkBigRed));
			}

	}

	private void bunusGeneration() {
		
		if(Math.random() < 0.2) 
		{
			double tmp = Math.random();
			int gtype = 0;
			if(tmp<0.33)
				gtype = Gun.BAZOOKA;
			else
				if(tmp<0.66)
					gtype = Gun.MACHINE_GUN;
				else
					gtype = Gun.CRAZY;
			
			int btype;
			Pixmap p = Assets.fnf;
			tmp = Math.random();

			if(tmp<0.3)
			{
				btype = BonusDrop.UPGRADE;
				p = Assets.bonusUpgrade;
			}
			else
				if(tmp < 0.6)
				{
					btype = BonusDrop.SWITCH;
					switch(gtype)
					{
					case Gun.BAZOOKA:
						p = Assets.bonusBazooka;
						break;
					case Gun.MACHINE_GUN:
						p = Assets.bonusMachinegun;
						break;
					case Gun.CRAZY:
						p = Assets.bonusCrazy;
						break;
					}
				}
				else
				{
					btype = BonusDrop.LIFEUP;
					p = Assets.lifeUp;
				}
			bonus = new BonusDrop((int)x, (int)y, 0, vy, swidth, sheight, p ,btype ,gtype);
		}
	}

	public boolean update(int time)
	{
		return super.update(time) && (y <= sheight + getHeight());
	}
	
	EnemyBullet getBullet()
	{
		EnemyBullet tmp = bullet;
		bullet = null;
		return tmp;
	}
}
