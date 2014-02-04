package com.jcf.spaceshooter.engine;

import java.util.ArrayList;

import android.view.MotionEvent;

import com.jcf.spaceshooter.model.SpaceShuttle;

public class AccelController extends ShuttleController {

	float ster;
	public AccelController(Input input) {
		super(input);
		ster = 0;
	}

	@Override
	public void ControlShuttle(SpaceShuttle shuttle) {

		float tmp = input.getAccY()*0.2f;
		ster = (ster*0.9f + 0.1f*tmp );
		shuttle.setVx(ster);
		
	}

}
