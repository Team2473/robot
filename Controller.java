package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	
	Joystick joy1;
	Joystick joy2;
	
	public Controller(){
		joy1 = new Joystick(0); //adjust?
		joy2 = new Joystick(1); //adjust?
	}
	
	public double getX(){
		return joy1.getX();
	}
	public double getY(){
		return joy1.getY();
	}
	
	
}
