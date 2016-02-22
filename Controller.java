package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	private Joystick joy1;
	private Joystick joy2;

	private static Controller controller = null;

	private Controller() {
		joy1 = new Joystick(0); // adjust?
		joy2 = new Joystick(1); // adjust?
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	// drive joystick 1 controls
	public double getXL() {
		return joy1.getRawAxis(0);
	}

	public double getXR() {
		return -joy1.getRawAxis(4);
	}

	public double getYL() {
		return -joy1.getRawAxis(1);
	}
	
	public double getYLNeg() {
		return joy1.getRawAxis(1);
	}

	public double getYR() {
		return -joy1.getRawAxis(5);
	}
	
	public double getYRNeg() {
		return joy1.getRawAxis(5);
	}
	
	public double getLeftTrigger(){
		return joy1.getRawAxis(2);
	}
	
	public boolean getJoy1Button(int b) {
		return joy1.getRawButton(b);
	}
	// buttons joystick 2
	public boolean getJoy2Button(int b) {
		return joy2.getRawButton(b);
	}
}
