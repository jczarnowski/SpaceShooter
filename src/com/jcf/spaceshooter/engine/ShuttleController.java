package com.jcf.spaceshooter.engine;


import com.jcf.spaceshooter.model.SpaceShuttle;

public abstract class ShuttleController {
	public static final int CONTROL_TOUCH = 0;
	public static final int CONTROL_ACCEL = 1;
	
	Input input;
	
	public ShuttleController(Input input) {
		this.input = input;
	}
	
	public abstract void ControlShuttle(SpaceShuttle shuttle);

}
