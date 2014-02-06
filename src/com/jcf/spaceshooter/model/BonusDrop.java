package com.jcf.spaceshooter.model;

import com.jcf.spaceshooter.engine.Assets;
import com.jcf.spaceshooter.engine.Pixmap;

public class BonusDrop extends InteractiveSpaceObject{
	public int bonusType;
	public static final int SWITCH = 0;
	public static final int UPGRADE = 1;
	public static final int LIFEUP = 2;
	public static final int SHIELD = 3;
	public int gunType;
	
	
	public BonusDrop(int x, int y, float vx, float vy, int screenWidth,	int screenHeight) {
		super(x, y, vx, vy, screenWidth, screenHeight, Assets.lifeUp);
		type = type.BONUS;
		
		double tmp = Math.random();		
		if(tmp<0.33)
			gunType = Gun.BAZOOKA;
		else
			if(tmp<0.66)
				gunType = Gun.MACHINE_GUN;
			else
				gunType = Gun.CRAZY;
		
		tmp = Math.random();
		if(tmp<0.45)
		{
			bonusType = BonusDrop.UPGRADE;
			pixmap = Assets.bonusUpgrade;
		}
		else
			if(tmp < 0.9)
			{
				bonusType = BonusDrop.SWITCH;
				switch(gunType)
				{
				case Gun.BAZOOKA:
					pixmap = Assets.bonusBazooka;
					break;
				case Gun.MACHINE_GUN:
					pixmap = Assets.bonusMachinegun;
					break;
				case Gun.CRAZY:
					pixmap = Assets.bonusCrazy;
					break;
				}
			}
			else
			{
				if(Math.random() < 0.5) {
					bonusType = BonusDrop.LIFEUP;
					pixmap = Assets.lifeUp;
				} else {
					bonusType = BonusDrop.SHIELD;
					pixmap = Assets.shieldUp;
				}
			}
		
		hp = 1;
		realWidth = (int)(0.5 * imageWidth);
		realHeight = (int)(0.5*imageHeight);
		 
		if(vy<0.1)
			vy=0.1f;
	}
	
	public boolean colisionDetection(InteractiveSpaceObject object) {
		if(!(object instanceof SpaceShuttle)) return false;
		return (super.colisionDetection(object));
	}
	
	public void colisionDetected(InteractiveSpaceObject object)
	{
	
		if(!(object instanceof SpaceShuttle)) return;
		
		((SpaceShuttle)object).addBonus(this);

		pe.add(new ParticleEmitter(0, 200,(int)x,(int)y, vx, vy,imageWidth,0.80f,0f,(float)Math.PI*2f, 10f, swidth, sheight, Assets.sparkBigBlue));
		pe.add(new ParticleEmitter(0, 200,(int)x,(int)y, vx, vy,imageWidth,0.40f,0f,(float)Math.PI*2f, 10f, swidth, sheight, Assets.sparkBigBlue));
		hp = 0;
	}
	
	public boolean update(int time)
	{
		
		return super.update(time) && (y <= sheight + getHeight());

	}
	
	public int getPoints()
	{
		return 1000;
	}

}
