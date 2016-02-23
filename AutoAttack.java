package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAttack {
	//directions for ninetyTurnWithGyro()
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	public static boolean turn = true;

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
		if (turn){
			SmartDashboard.putString("DB/String 5", "Running gyroTurn");
			ninetyTurnWithGyro(LEFT);
		}
		turn = false;
//		//Starting on Left Side of Field
//		if(Switches.getInstance().getTripleSwitch() == 1) {
//			SmartDashboard.putString("DB/String 5", "Starting Side: LEFT");
//			
//			//Delay Time
//			try {
//				if(Switches.getInstance().getFourDial() == 0) {
//					SmartDashboard.putString("DB/String 6", "5 Second Delay");
//					Thread.sleep(5000);
//				}
//				else if (Switches.getInstance().getFourDial() == 1) {
//					SmartDashboard.putString("DB/String 6", "10 Second Delay");
//					Thread.sleep(10000);
//				}
//				else if (Switches.getInstance().getFourDial() == 2) {
//					SmartDashboard.putString("DB/String 6", "15 Second Delay");
//					Thread.sleep(15000);
//				}
//				else if (Switches.getInstance().getFourDial() == 3) {
//					SmartDashboard.putString("DB/String 6", "20 Second Delay");
//					Thread.sleep(20000);
//				}
//			}
//			catch (Exception e) {
//				//Do Nothing(for Thread.sleep())
//			}
//			
//			//Distance To travel dependent on defense position
//			if(Switches.getInstance().getEightDial() == 0) {
//				SmartDashboard.putString("DB/String 7", "Defense 0");
//				//Move
//			}
//			else if (Switches.getInstance().getEightDial() == 1) {
//				SmartDashboard.putString("DB/String 7", "Defense 1");
//				//Move
//			}
//			else if (Switches.getInstance().getEightDial() == 2) {
//				SmartDashboard.putString("DB/String 7", "Defense 2");
//				//Move
//			}
//			else if (Switches.getInstance().getEightDial() == 3) {
//				SmartDashboard.putString("DB/String 7", "Defense 3");
//				//Move
//			}
//			else if (Switches.getInstance().getEightDial() == 4) {
//				SmartDashboard.putString("DB/String 7", "Defense 4");
//				//Move
//			}
//			
//			//Turn Left
//			ninetyTurnWithGyro(LEFT);
//			
//			//Move Past Defense
//			//Move
//		}
//		else if (Switches.getInstance().getTripleSwitch() == 2) {
//			SmartDashboard.putString("DB/String 5", "Starting Side: RIGHT");
//			
//			//Delay Time
//			try {
//				if(Switches.getInstance().getFourDial() == 0) {
//					SmartDashboard.putString("DB/String 6", "5 Second Delay");
//					Thread.sleep(5000);
//				}
//				else if (Switches.getInstance().getFourDial() == 1) {
//					SmartDashboard.putString("DB/String 6", "10 Second Delay");
//					Thread.sleep(10000);
//				}
//				else if (Switches.getInstance().getFourDial() == 2) {
//					SmartDashboard.putString("DB/String 6", "15 Second Delay");
//					Thread.sleep(15000);
//				}
//				else if (Switches.getInstance().getFourDial() == 3) {
//					SmartDashboard.putString("DB/String 6", "20 Second Delay");
//					Thread.sleep(20000);
//				}
//			}
//			catch (Exception e) {
//				//Do Nothing(for Thread.sleep())
//			}
//			
//			//Distance To travel dependent on defense position
//			if(Switches.getInstance().getEightDial() == 0) {
//				SmartDashboard.putString("DB/String 7", "Defense 0");
//				//Move
//			}
//			else if (Switches.getInstance().getEightDial() == 1) {
//				SmartDashboard.putString("DB/String 7", "Defense 1");
//				//Move
//			}
//			else if (Switches.getInstance().getEightDial() == 2) {
//				SmartDashboard.putString("DB/String 7", "Defense 2");
//				//Move
//			}
//			else if (Switches.getInstance().getEightDial() == 3) {
//				SmartDashboard.putString("DB/String 7", "Defense 3");
//				//Move
//			}
//			else if (Switches.getInstance().getEightDial() == 4) {
//				SmartDashboard.putString("DB/String 7", "Defense 4");
//				//Move
//			}
//			
//			//Turn Left
//			ninetyTurnWithGyro(LEFT);
//			
//			//Move Past Defense
//			//Move
//		}
	}

	//90 Degree turn with gyroscope
	//for input, use constants LEFT and RIGHT defined at the top of this class
	private static void ninetyTurnWithGyro(int direction) {
		Telemetry.getInstance().resetGyro();
		if (direction == LEFT) {
			Motor.getInstance().setLeftSideMotorsMode(Motor.MODE_POWER);
			Motor.getInstance().setRightSideMotorsMode(Motor.MODE_POWER);
			while(Telemetry.getInstance().getGyro() > -90.0) {
				SmartDashboard.putString("DB/String 6", "Turning");
				Motor.getInstance().moveLeftSideMotors(-.4);
				Motor.getInstance().moveRightSideMotors(.4);
			}
			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(0);
		}
		else if (direction == RIGHT) {
			//finish
		}
	}
}

