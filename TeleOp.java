package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp {
	
	private static CANTalon.TalonControlMode currentMode = null;
	private static double deadZone = .3;
	private static double maxSpeed = .50;
	
	public static void runPower(){
		if(currentMode != Motor.MODE_POWER){
			currentMode = Motor.MODE_POWER;
			
			Motor.getInstance().setLeftSideMotorsMode(currentMode);
			Motor.getInstance().setRightSideMotorsMode(currentMode);
		}
		
		double leftY = Controller.getInstance().getYL() * Math.abs(Controller.getInstance().getYL());
		double rightY = Controller.getInstance().getYR()* Math.abs(Controller.getInstance().getYR());
		
		if(Math.abs(Controller.getInstance().getYL())>deadZone){
			Motor.getInstance().moveLeftSideMotors(leftY * maxSpeed);
		}else{
			Motor.getInstance().moveLeftSideMotors(0);
		}
		
		if(Math.abs(Controller.getInstance().getYR())>deadZone){
			Motor.getInstance().moveRightSideMotors(rightY * maxSpeed);
		}else{
			Motor.getInstance().moveRightSideMotors(0);
		}
		
		SmartDashboard.putString("DB/String 6", "LY: " + leftY);
		SmartDashboard.putString("DB/String 7", "RY: " + rightY);
	}
	
	private static int leftEnc;
	private static int rightEnc;
	
	public static void runPosition(){
		if(currentMode != Motor.MODE_POSITION){
			currentMode = Motor.MODE_POSITION;
			
			Motor.getInstance().setLeftSideMotorsMode(currentMode);
			Motor.getInstance().setRightSideMotorsMode(currentMode);
			
			leftEnc = 0;
			rightEnc = 0;
			Motor.getInstance().resetDriveEncoders();
		}
		
		double leftY = Controller.getInstance().getYL();
		double rightY = Controller.getInstance().getYR();
		
		if(Math.abs(leftY)>deadZone){
			leftEnc += leftY*75;
		}
		
		if(Math.abs(rightY)>deadZone){
			rightEnc += rightY*50;
		}
		
		Motor.getInstance().moveLeftSideMotors(leftEnc);
		Motor.getInstance().moveRightSideMotors(rightEnc);
		
	}
}
