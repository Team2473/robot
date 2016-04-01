package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp {

	private static CANTalon.TalonControlMode currentMode = null;
	private static double deadZone = .07;
	private static double maxSpeed = .23;
	private static double currentThreshold = 1;
	private static PowerDistributionPanel myPanel = new PowerDistributionPanel();
	private static double currentFR;
	private static double currentBR;
	private static double currentFL;
	private static double currentBL;

	private static Controller cont = Controller.getInstance();
	private static Motor mot = Motor.getInstance();
	
	private static int i = 0;
	
	private static boolean runDrive = true;

	// static Accelerometer accel = new
	// BuiltInAccelerometer(Accelerometer.Range.k4G);

	public static void runPower() {
		i++;
		// double totalCurrent = 0;
		// for (int i = 0; i < 15; i++){
		// totalCurrent += myPanel.getCurrent(i);
		// }
		//
		// double motorCurrent = myPanel.getCurrent(0) + myPanel.getCurrent(1) +
		// myPanel.getCurrent(2) +myPanel.getCurrent(3);
		SmartDashboard.putString("DB/String 3", "FR Volt: " + currentFR);
		SmartDashboard.putString("DB/String 4", "BR Volt: " + currentBR);
		SmartDashboard.putString("DB/String 5", "FL Volt: " + currentFL);
		SmartDashboard.putString("DB/String 6", "BL Volt: " + currentBL);

		currentFR = myPanel.getCurrent(1);
		currentBR = myPanel.getCurrent(0);
		currentFL = myPanel.getCurrent(2);
		currentBL = myPanel.getCurrent(3);
		//
		// double xVal = accel.getX();
		// double yVal = accel.getY();
		// double zVal = accel.getZ();
		//
		// SmartDashboard.putString("DB/String 0", "X " + xVal);
		// SmartDashboard.putString("DB/String 1", "Y " + yVal);
		// SmartDashboard.putString("DB/String 2", "Z " + zVal);

		if (Controller.getInstance().getJoy2Button(1)) {
			maxSpeed = .5;
		} else {
			if(Shooter.crossingBar) {
				maxSpeed = .25;
			}else {
				maxSpeed = .23;
			}
		}
		if (runDrive) {
//			if (waiting) {
//				return;
//			}

			if (currentMode != Motor.MODE_POWER) {
				currentMode = Motor.MODE_POWER;

				mot.setLeftSideMotorsMode(currentMode);
				mot.setRightSideMotorsMode(currentMode);
			}

			double leftY = cont.getYL() / Math.abs(cont.getYL()) * Math.sqrt(Math.abs(cont.getYL()));
			double rightY = cont.getYR() / Math.abs(cont.getYR()) * Math.sqrt(Math.abs(cont.getYR()));

			if (Math.abs(cont.getYL()) > deadZone) {
				mot.moveLeftSideMotors(leftY * maxSpeed);
			} else {
				mot.moveLeftSideMotors(0);
			}

			if (Math.abs(cont.getYR()) > deadZone) {
				mot.moveRightSideMotors(rightY * maxSpeed);
			} else {
				mot.moveRightSideMotors(0);
			}

			 SmartDashboard.putString("DB/String 2", "I: " + i);
			// SmartDashboard.putString("DB/String 7", "RY: " + rightY);
			
//			if ( (i % 10000 == 0) && !(currentFR < (2 * currentBR) && currentFL < (2 * currentBL) && currentBR < (2 * currentFR)
//					&& currentBL < (2 * currentFL))) {
//				runDrive = false;
//				i = 0;
//			}else {
//				runDrive = true;
//			} 
		} else {
			SmartDashboard.putString("DB/String 0", "DRIVE MOTOR FAILURE");
			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(0);
		}
	}

	public static void runPowerReverse() {
		i++;
		SmartDashboard.putString("DB/String 3", "FR Volt: " + currentFR);
		SmartDashboard.putString("DB/String 4", "BR Volt: " + currentBR);
		SmartDashboard.putString("DB/String 5", "FL Volt: " + currentFL);
		SmartDashboard.putString("DB/String 6", "BL Volt: " + currentBL);

		currentFR = myPanel.getCurrent(1);
		currentBR = myPanel.getCurrent(0);
		currentFL = myPanel.getCurrent(2);
		currentBL = myPanel.getCurrent(3);

		if (Controller.getInstance().getJoy2Button(1)) {
			maxSpeed = .5;
		} else {
			if(Shooter.crossingBar) {
				maxSpeed = .25;
			}else {
				maxSpeed = .23;
			}
		}

		if (runDrive) {
//			if (waiting) {
//				return;
//			}
			if (currentMode != Motor.MODE_POWER) {
				currentMode = Motor.MODE_POWER;

				mot.setLeftSideMotorsMode(currentMode);
				mot.setRightSideMotorsMode(currentMode);
			}

			double leftY = cont.getYRNeg() / Math.abs(cont.getYRNeg()) * Math.sqrt(Math.abs(cont.getYRNeg()));
			double rightY = cont.getYLNeg() / Math.abs(cont.getYLNeg()) * Math.sqrt(Math.abs(cont.getYLNeg()));

			if (Math.abs(cont.getYRNeg()) > deadZone) {
				mot.moveLeftSideMotors(leftY * maxSpeed);

			} else {
				mot.moveLeftSideMotors(0);
			}

			if (Math.abs(cont.getYLNeg()) > deadZone) {
				mot.moveRightSideMotors(rightY * maxSpeed);
			} else {
				mot.moveRightSideMotors(0);
			}
			
//			if ( (i % 10000 == 0) && !(currentFR < (2 * currentBR) && currentFL < (2 * currentBL) && currentBR < (2 * currentFR)
//					&& currentBL < (2 * currentFL))) {
//				runDrive = false;
//				i = 0;
//			}else {
//				runDrive = true;
//			} 

			 SmartDashboard.putString("DB/String 2", "I: " + i);
			// SmartDashboard.putString("DB/String 7", "RY: " + rightY);
		} else {
			SmartDashboard.putString("DB/String 0", "DRIVE MOTOR FAILURE");
			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(0);
		}
	}

	private static int leftEnc;
	private static int rightEnc;

	public static void runPosition() {
		if (currentMode != Motor.MODE_POSITION) {
			currentMode = Motor.MODE_POSITION;

			Motor.getInstance().setLeftSideMotorsMode(currentMode);
			Motor.getInstance().setRightSideMotorsMode(currentMode);

			leftEnc = 0;
			rightEnc = 0;
			Motor.getInstance().resetDriveEncoders();
		}

		double leftY = Controller.getInstance().getYL();
		double rightY = Controller.getInstance().getYR();

		if (Math.abs(leftY) > deadZone) {
			leftEnc += leftY * 75;
		}

		if (Math.abs(rightY) > deadZone) {
			rightEnc += rightY * 50;
		}

		Motor.getInstance().moveLeftSideMotors(leftEnc);
		Motor.getInstance().moveRightSideMotors(rightEnc);

	}

	public static boolean waiting = false;
	public static boolean reverse = false;

	public static void runUtilities() {

		if (Controller.getInstance().getJoy2Button(1)) {
			maxSpeed = .5;
		} else {
			maxSpeed = .25;
		}

		if (Controller.getInstance().getJoy1Button(7)) {
			waiting = false;
		}

		if (Controller.getInstance().getFootPedal()) {
			reverse = true;
		} else {
			reverse = false;
		}
	}
}
