package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class TeleOp {
	
	private static ControlMode currentMode = null;
	private static double deadZone = .03;
	private static double maxSpeed = .50;
	
	public static void runPower(){
		if(currentMode != Motor.MODE_POWER){
			currentMode = Motor.MODE_POWER;
			
			Motor.getInstance().setLeftSideMotorsMode(currentMode);
			Motor.getInstance().setRightSideMotorsMode(currentMode);
		}
		
		double leftY = Controller.getInstance().getYL();
		double rightY = Controller.getInstance().getYR();
		
		if(Math.abs(leftY)>deadZone){
			Motor.getInstance().moveLeftSideMotors(leftY * maxSpeed);
		}else{
			Motor.getInstance().moveLeftSideMotors(0);
		}
		
		if(Math.abs(rightY)>deadZone){
			Motor.getInstance().moveRightSideMotors(rightY * maxSpeed);
		}else{
			Motor.getInstance().moveRightSideMotors(0);
		}
	}
	
	public static void runPosition(){
		if(currentMode != Motor.MODE_POSITION){
			currentMode = Motor.MODE_POSITION;
			
			Motor.getInstance().setLeftSideMotorsMode(currentMode);
			Motor.getInstance().setRightSideMotorsMode(currentMode);
		}
	}
}
