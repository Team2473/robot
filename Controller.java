package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	
	Joystick joy1;
	Joystick joy2;
	
	public Controller(){
		joy1 = new Joystick(0); //adjust?
		joy2 = new Joystick(1); //adjust?
	}
	
	//drive joystick controls
	public double getXL(){
		return joy1.getRawAxis(0);
	}
	public double getXR(){
		return joy1.getRawAxis(2);
	}
	public double getYL(){
		return joy1.getRawAxis(1);
	}
	public double getYR(){
		return joy1.getRawAxis(3);
	}
	
	
	//buttons
	
	
	
}
