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
	public boolean button5(){
		return joy2.getRawButton(5);
	}
	public boolean button3(){
		return joy2.getRawButton(3);
	}
	public boolean button4(){
		return joy2.getRawButton(4);
	}
	public boolean button6(){
		return joy2.getRawButton(6);
	}
	public boolean button2(){
		return joy2.getRawButton(2);
	}
	public boolean button11(){
		return joy2.getRawButton(11);
	}
	public boolean button12(){
		return joy2.getRawButton(12);
	}
	
	
	
	
}
