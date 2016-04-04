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

	//To make sure we run our attack only once
	private static boolean done = false;

	//constructor
	public AutoAttack () {

	}

	//runs Attack portion of autonomous
	/**
	 * Switch Position Definitions
	 * 3-Position RectangularSwitch:
	 * --Initial Starting Position--
	 * 1: To the right of defense (relative to looking at field from driver's position)
	 * 2: Right in front of Defense
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
		if (!done) {
			//starting to the right of defense, parallel, (forward, turn right, forward)
			if(mySwitches.getTripleSwitch() == 1) {
				SmartDashboard.putString("DB/String 5", "Starting Position: Right of Defense");

				//Delay Time
				try {
					if(mySwitches.getFourDial() == 0) {
						SmartDashboard.putString("DB/String 6", "5 Second Delay");
						Thread.sleep(5000);
					}
					else if (mySwitches.getFourDial() == 1) {
						SmartDashboard.putString("DB/String 6", "10 Second Delay");
						Thread.sleep(10000);
					}
					else if (mySwitches.getFourDial() == 2) {
						SmartDashboard.putString("DB/String 6", "15 Second Delay");
						Thread.sleep(15000);
					}
					else if (mySwitches.getFourDial() == 3) {
						SmartDashboard.putString("DB/String 6", "20 Second Delay");
						Thread.sleep(20000);
					}
				}
				catch (Exception e) {
					//Do Nothing(for Thread.sleep())
				}

				//Complete same operation for each defense except for low bar(forward, turn right, forward)
				if(mySwitches.getEightDial() == 0) { //low bar
					SmartDashboard.putString("DB/String 7", "Defense 0");
					myMotor.moveForwardEncoders(5100);
					ninetyTurnWithGyro(AutoAttack.RIGHT);
					movePastDefense();
				}
				else if (mySwitches.getEightDial() == 1) {
					SmartDashboard.putString("DB/String 7", "Defense 1");
					myMotor.moveForwardEncoders(5100);
					ninetyTurnWithGyro(AutoAttack.RIGHT);
					movePastDefense();
				}
				else if (mySwitches.getEightDial() == 2) {
					SmartDashboard.putString("DB/String 7", "Defense 2");
					myMotor.moveForwardEncoders(5100);
					ninetyTurnWithGyro(AutoAttack.RIGHT);
					movePastDefense();
				}
				else if (mySwitches.getEightDial() == 3) {
					SmartDashboard.putString("DB/String 7", "Defense 3");
					myMotor.moveForwardEncoders(5100);
					ninetyTurnWithGyro(AutoAttack.RIGHT);
					movePastDefense();
				}
				else if (mySwitches.getEightDial() == 4) {
					SmartDashboard.putString("DB/String 7", "Defense 4");
					myMotor.moveForwardEncoders(5100);
					ninetyTurnWithGyro(AutoAttack.RIGHT);
					movePastDefense();
				}

				//We're done- do not repeat
				done = true;
			}

			//starting in front of defense
			//just drive straight, same for every defense except low bar
			else if (mySwitches.getTripleSwitch() == 2) {
				SmartDashboard.putString("DB/String 5", "Starting in Front");

				//Delay Time
				try {
					if(mySwitches.getFourDial() == 0) {
						SmartDashboard.putString("DB/String 6", "5 Second Delay");
						Thread.sleep(5000);
					}
					else if (mySwitches.getFourDial() == 1) {
						SmartDashboard.putString("DB/String 6", "10 Second Delay");
						Thread.sleep(10000);
					}
					else if (mySwitches.getFourDial() == 2) {
						SmartDashboard.putString("DB/String 6", "15 Second Delay");
						Thread.sleep(15000);
					}
					else if (mySwitches.getFourDial() == 3) {
						SmartDashboard.putString("DB/String 6", "20 Second Delay");
						Thread.sleep(20000);
					}
				}
				catch (Exception e) {
					//Do Nothing(for Thread.sleep())
				}

				//go straight
				if(mySwitches.getEightDial() == 0) { //low bar
					SmartDashboard.putString("DB/String 7", "Defense 0");
					movePastDefense();
				}
				else if (mySwitches.getEightDial() == 1) {
					SmartDashboard.putString("DB/String 7", "Defense 1");
					movePastDefense();
				}
				else if (mySwitches.getEightDial() == 2) {
					SmartDashboard.putString("DB/String 7", "Defense 2");
					movePastDefense();
				}
				else if (mySwitches.getEightDial() == 3) {
					SmartDashboard.putString("DB/String 7", "Defense 3");
					movePastDefense();
				}
				else if (mySwitches.getEightDial() == 4) {
					SmartDashboard.putString("DB/String 7", "Defense 4");
					movePastDefense();
				}

				//We're done- do not repeat
				done = true;
			}
		}

	}

	//90 Degree turn with gyroscope
	//for input, use constants LEFT and RIGHT defined at the top of this class
	public static void ninetyTurnWithGyro(int direction) {
		//first reset the gyro
		myTelemetry.resetGyro();

		//set motors to power mode
		myMotor.setLeftSideMotorsMode(Motor.MODE_POWER);
		myMotor.setRightSideMotorsMode(Motor.MODE_POWER);

		//turning left
		if (direction == LEFT) {
			while(myTelemetry.getGyro() > -85.0) {
				//turn
				myMotor.moveLeftSideMotors(-.15);
				myMotor.moveRightSideMotors(.15);
			}
			myMotor.moveLeftSideMotors(0);
			myMotor.moveRightSideMotors(0);
		}

		//turning right
		else if (direction == RIGHT) {
			while(myTelemetry.getGyro() < 85.0) {
				//turn
				myMotor.moveLeftSideMotors(.15);
				myMotor.moveRightSideMotors(-.15);
			}

			myMotor.moveLeftSideMotors(0);
			myMotor.moveRightSideMotors(0);
		}
	}

	//move forward past defense
	private static void movePastDefense() {
		myMotor.moveForwardEncoders(20000);
	}
}

