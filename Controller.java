package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	
	Joystick joy1;
	Joystick joy2;
	
	private static Controller controller = null;
	
	private Controller(){
		joy1 = new Joystick(0); //adjust?
		joy2 = new Joystick(1); //adjust?
	}
	public static Controller getInstance(){
		if(controller == null){
			controller = new Controller();
		}
		return controller;
	}
	

	
	//drive joystick 1 controls
	public double getXL(){
		return joy1.getRawAxis(0);
	}
	public double getXR(){
		return joy1.getRawAxis(2);
	}
	public double getYL(){
		return -joy1.getRawAxis(1);
	}
	public double getYR(){
		return -joy1.getRawAxis(3);
	}
	
	
	//buttons joystick 2
	public boolean getButton(int b){
		return joy2.getRawButton(b);
	}	
	
	
	
}
