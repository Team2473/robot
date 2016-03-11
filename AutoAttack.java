package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAttack {
	//directions for ninetyTurnWithGyro()
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	
	//Instances of other classes for use in this class
	static Motor myMotor = Motor.getInstance();
	static Switches mySwitches = Switches.getInstance();
	static Telemetry myTelemetry = Telemetry.getInstance();

	//constructor
	public AutoAttack () {

	}

	//runs Attack portion of autonomous
	/**
	 * Switch Position Definitions
	 * 3-Position RectangularSwitch:
	 * --Side of Field--
	 * 1: Left Side of Field (relative to looking at field from driver's position)
	 * 2: Right Side of Field (relative to looking at field from driver's position)
	 * 
	 * 4-Position Dial
	 * --Time Delay--, Allows us to let other teammates pass us safely and then we proceed with our autonomous attack
	 * 0: 5s
	 * 1:10s
	 * 2:15s
	 * 3:20s
	 * 
	 * 7-Position Dial
	 * --Defense we are Crossing--
	 * Note: Whether we are starting on left or right side of field, numbering starts from left (relative to looking at field from driver's position)
	 * 01234
	 */
	public static void run() {
//		//Starting on Left Side of Field
		if(mySwitches.getTripleSwitch() == 1) {
			SmartDashboard.putString("DB/String 5", "Starting Side: LEFT");
			
			//Delay Time
			try {
				if(mySwitches.getFourDial() == 0) {
					SmartDashboard.putString("DB/String 6", "5 Second Delay");
//					Thread.sleep(5000);
				}
				else if (mySwitches.getFourDial() == 1) {
					SmartDashboard.putString("DB/String 6", "10 Second Delay");
//					Thread.sleep(10000);
				}
				else if (mySwitches.getFourDial() == 2) {
					SmartDashboard.putString("DB/String 6", "15 Second Delay");
//					Thread.sleep(15000);
				}
				else if (mySwitches.getFourDial() == 3) {
					SmartDashboard.putString("DB/String 6", "20 Second Delay");
//					Thread.sleep(20000);
				}
			}
			catch (Exception e) {
				//Do Nothing(for Thread.sleep())
			}
//			
			//Distance To travel dependent on defense position
			if(mySwitches.getEightDial() == 0) {
				SmartDashboard.putString("DB/String 7", "Defense 0");
//				myMotor.moveForwardEncoders();
			}
			else if (mySwitches.getEightDial() == 1) {
				SmartDashboard.putString("DB/String 7", "Defense 1");
//				myMotor.moveForwardEncoders();
			}
			else if (mySwitches.getEightDial() == 2) {
				SmartDashboard.putString("DB/String 7", "Defense 2");
//				myMotor.moveForwardEncoders();
			}
			else if (mySwitches.getEightDial() == 3) {
				SmartDashboard.putString("DB/String 7", "Defense 3");
//				myMotor.moveForwardEncoders();
			}
			else if (mySwitches.getEightDial() == 4) {
				SmartDashboard.putString("DB/String 7", "Defense 4");
//				myMotor.moveForwardEncoders();
			}
//			
//			//Turn Left
//			ninetyTurnWithGyro(LEFT);
//			
//			//Move Past Defense
//			movePastDefense()
		}
		else if (mySwitches.getTripleSwitch() == 2) {
			SmartDashboard.putString("DB/String 5", "Starting Side: RIGHT");
			
			//Delay Time
			try {
				if(mySwitches.getFourDial() == 0) {
					SmartDashboard.putString("DB/String 6", "5 Second Delay");
//					Thread.sleep(5000);
				}
				else if (mySwitches.getFourDial() == 1) {
					SmartDashboard.putString("DB/String 6", "10 Second Delay");
//					Thread.sleep(10000);
				}
				else if (mySwitches.getFourDial() == 2) {
					SmartDashboard.putString("DB/String 6", "15 Second Delay");
//					Thread.sleep(15000);
				}
				else if (mySwitches.getFourDial() == 3) {
					SmartDashboard.putString("DB/String 6", "20 Second Delay");
//					Thread.sleep(20000);
				}
			}
			catch (Exception e) {
				//Do Nothing(for Thread.sleep())
			}
			
			//Distance To travel dependent on defense position
			if(mySwitches.getEightDial() == 0) {
				SmartDashboard.putString("DB/String 7", "Defense 0");
//				myMotor.moveForwardEncoders();
			}
			else if (mySwitches.getEightDial() == 1) {
				SmartDashboard.putString("DB/String 7", "Defense 1");
//				myMotor.moveForwardEncoders();
			}
			else if (mySwitches.getEightDial() == 2) {
				SmartDashboard.putString("DB/String 7", "Defense 2");
//				myMotor.moveForwardEncoders();
			}
			else if (mySwitches.getEightDial() == 3) {
				SmartDashboard.putString("DB/String 7", "Defense 3");
//				myMotor.moveForwardEncoders();
			}
			else if (mySwitches.getEightDial() == 4) {
				SmartDashboard.putString("DB/String 7", "Defense 4");
//				myMotor.moveForwardEncoders();
			}
//			
//			//Turn Left
//			ninetyTurnWithGyro(LEFT);
//			
//			//Move Past Defense
//			movePastDefense()
		}
		
	}
	

	//90 Degree turn with gyroscope
	//for input, use constants LEFT and RIGHT defined at the top of this class
	private static void ninetyTurnWithGyro(int direction) {
		//first reset the gyro
		myTelemetry.resetGyro();
		
		//set motors to power mode
		myMotor.setLeftSideMotorsMode(Motor.MODE_POWER);
		myMotor.setRightSideMotorsMode(Motor.MODE_POWER);
		
		//turning left
		if (direction == LEFT) {
			while(myTelemetry.getGyro() > -90.0) {
				//turn
				myMotor.moveLeftSideMotors(-.4);
				myMotor.moveRightSideMotors(.4);
			}
			myMotor.moveLeftSideMotors(0);
			myMotor.moveRightSideMotors(0);
		}
		
		//turning right
		else if (direction == RIGHT) {
			while(myTelemetry.getGyro() < 90.0) {
				//turn
				myMotor.moveLeftSideMotors(.4);
				myMotor.moveRightSideMotors(-.4);
			}
		}
	}
	
	private static void movePastDefense() {
		
	}
}

