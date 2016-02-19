package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp {
	
	private static CANTalon.TalonControlMode currentMode = null;
	private static double deadZone = .02;
	private static double maxSpeed = .40;
	
	public static void runPower(){
		if(waiting){
			return;
		}
		
		if(currentMode != Motor.MODE_POWER){
			currentMode = Motor.MODE_POWER;
			
			Motor.getInstance().setLeftSideMotorsMode(currentMode);
			Motor.getInstance().setRightSideMotorsMode(currentMode);
		}
		
		double leftY = Controller.getInstance().getYL()/Math.abs(Controller.getInstance().getYL()) * Math.sqrt(Math.abs(Controller.getInstance().getYL()));
		double rightY = Controller.getInstance().getYR()/Math.abs(Controller.getInstance().getYR()) * Math.sqrt(Math.abs(Controller.getInstance().getYR()));
		
		if(Math.abs(Controller.getInstance().getYL())>deadZone){
			Motor.getInstance().moveLeftSideMotors(leftY * maxSpeed * 1.1);
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
	
	private static boolean waiting = false;
	
	public static void runUtilities(){
		
		if(Controller.getInstance().getJoy2Button(3)){
			if(currentMode != Motor.MODE_POWER){
				currentMode = Motor.MODE_POWER;
				
				Motor.getInstance().setLeftSideMotorsMode(currentMode);
				Motor.getInstance().setRightSideMotorsMode(currentMode);
			}
			Motor.getInstance().moveLeftSideMotors(.25);
			Motor.getInstance().moveRightSideMotors(.25);
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			
			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(-0.1);
			
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {}
			
			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {}
			
			for(int i = 0; i < 100; i++){
				Motor.getInstance().moveGrapplerArmMotor(260);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {}
			}
			
			Motor.getInstance().moveLeftSideMotors(.25);
			Motor.getInstance().moveRightSideMotors(.25);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);
			
			waiting = true;
		}
		
		if(Controller.getInstance().getJoy2Button(4)){
			for(int i = 0; i < 75; i++){
				Motor.getInstance().moveWinchMotors(140);
				Motor.getInstance().moveGrapplerArmMotor(0);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {}
			}
			
			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(0);
			
			while(true){
				Motor.getInstance().moveWinchMotors(2800);
				Motor.getInstance().moveGrapplerArmMotor(0);
			}
		}
		
		if(Controller.getInstance().getJoy1Button(2)){
			waiting = false;
		}
		
	}
}
