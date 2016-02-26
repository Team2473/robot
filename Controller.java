package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	private Joystick joy1;
	private Joystick joy2;

	private static Controller controller = null;
    
	//Constructor for the controller
	private Controller() {
		joy1 = new Joystick(0); // adjust?
		joy2 = new Joystick(1); // adjust?
	}
     //Getting an instance of the controller
	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	// drive joystick 1 controls
	
	//Getting the left x-value of the joystick
	public double getXL() {
		return joy1.getRawAxis(0);
	}
	//Getting the right x-value of the joystick
	public double getXR() {
		return -joy1.getRawAxis(4);
	}
	//Getting the left y-value of the joystick
	public double getYL() {
		return -joy1.getRawAxis(1);
	}
	//Getting the left negative y-value of the joystick
	public double getYLNeg() {
		return joy1.getRawAxis(1);
	}
    //Getting the right y-value of the joystick
	public double getYR() {
		return -joy1.getRawAxis(5);
	}
	//Getting the negative right y-value of the joystick
	public double getYRNeg() {
		return joy1.getRawAxis(5);
	}
	
	//Getting the value of the left trigger
	public double getLeftTrigger(){
		return joy1.getRawAxis(2);
	}
	//Getting the state of JoyButton1.
	public boolean getJoy1Button(int b) {
		return joy1.getRawButton(b);
	}
	//Getting the state of JoyButton2.
	public boolean getJoy2Button(int b) {
		return joy2.getRawButton(b);
	}
}
