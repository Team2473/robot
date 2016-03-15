package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
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

	public static void runPower() {
//		double totalCurrent = 0;
//		for (int i = 0; i < 15; i++){
//			totalCurrent += myPanel.getCurrent(i);
//		}
//		
//		double motorCurrent = myPanel.getCurrent(0) +  myPanel.getCurrent(1) + myPanel.getCurrent(2) +myPanel.getCurrent(3);
    	SmartDashboard.putString("DB/String 0",
				"FR Volt: " + currentFR);
    	SmartDashboard.putString("DB/String 1",
				"BR Volt: " + currentBR);
    	SmartDashboard.putString("DB/String 8",
				"FL Volt: " + currentFL);
    	SmartDashboard.putString("DB/String 9",
				"BL Volt: " + currentBL);
    	
    	currentFR = myPanel.getCurrent(1);
    	currentBR = myPanel.getCurrent(0);
    	currentFL = myPanel.getCurrent(2);
    	currentBL = myPanel.getCurrent(3);
    


    	
		if (Controller.getInstance().getJoy1Button(6)) {
			maxSpeed = .5;
		} else {
			maxSpeed = .23;
		}
		
		if ( currentFR < (2 * currentBR)
				&& currentFL < (2 * currentBL) && currentBR < (2 * currentFR) && currentBL < (2 * currentFL)) {
			if (waiting) {
				return;
			}

			if (currentMode != Motor.MODE_POWER) {
				currentMode = Motor.MODE_POWER;

				Motor.getInstance().setLeftSideMotorsMode(currentMode);
				Motor.getInstance().setRightSideMotorsMode(currentMode);
			}

			double leftY = Controller.getInstance().getYL() / Math.abs(Controller.getInstance().getYL())
					* Math.sqrt(Math.abs(Controller.getInstance().getYL()));
			double rightY = Controller.getInstance().getYR() / Math.abs(Controller.getInstance().getYR())
					* Math.sqrt(Math.abs(Controller.getInstance().getYR()));

			if (Math.abs(Controller.getInstance().getYL()) > deadZone) {
				Motor.getInstance().moveLeftSideMotors(leftY * maxSpeed * 1.05);
			} else {
				Motor.getInstance().moveLeftSideMotors(0);
			}

			if (Math.abs(Controller.getInstance().getYR()) > deadZone) {
				Motor.getInstance().moveRightSideMotors(rightY * maxSpeed);
			} else {
				Motor.getInstance().moveRightSideMotors(0);
			}

//			SmartDashboard.putString("DB/String 6", "LY: " + leftY);
//			SmartDashboard.putString("DB/String 7", "RY: " + rightY);
		} else {
			SmartDashboard.putString("DB/String 0", "DRIVE MOTOR FAILURE");
			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(0);
		}
	}

	public static void runPowerReverse() {
		if (Controller.getInstance().getJoy1Button(6)) {
			maxSpeed = .5;
		} else {
			maxSpeed = .23;
		}
		
		if (Math.abs(Motor.getInstance().frontLeft.getOutputCurrent()
				- Motor.getInstance().backLeft.getOutputCurrent()) < currentThreshold
				&& Math.abs(Motor.getInstance().frontRight.getOutputCurrent()
						- Motor.getInstance().backRight.getOutputCurrent()) < currentThreshold) {
			if (waiting) {
				return;
			}
			if (currentMode != Motor.MODE_POWER) {
				currentMode = Motor.MODE_POWER;

				Motor.getInstance().setLeftSideMotorsMode(currentMode);
				Motor.getInstance().setRightSideMotorsMode(currentMode);
			}

			double leftY = Controller.getInstance().getYRNeg() / Math.abs(Controller.getInstance().getYRNeg())
					* Math.sqrt(Math.abs(Controller.getInstance().getYRNeg()));
			double rightY = Controller.getInstance().getYLNeg() / Math.abs(Controller.getInstance().getYLNeg())
					* Math.sqrt(Math.abs(Controller.getInstance().getYLNeg()));

			if (Math.abs(Controller.getInstance().getYRNeg()) > deadZone) {
				Motor.getInstance().moveLeftSideMotors(leftY * maxSpeed * 1.05);
			} else {
				Motor.getInstance().moveLeftSideMotors(0);
			}

			if (Math.abs(Controller.getInstance().getYLNeg()) > deadZone) {
				Motor.getInstance().moveRightSideMotors(rightY * maxSpeed);
			} else {
				Motor.getInstance().moveRightSideMotors(0);
			}

//			SmartDashboard.putString("DB/String 6", "LY: " + leftY);
//			SmartDashboard.putString("DB/String 7", "RY: " + rightY);
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

	private static boolean waiting = false;

	public static void runUtilities() {
		if (Controller.getInstance().getJoy2Button(1)) {
			if (currentMode != Motor.MODE_POWER) {
				currentMode = Motor.MODE_POWER;

				Motor.getInstance().setLeftSideMotorsMode(currentMode);
				Motor.getInstance().setRightSideMotorsMode(currentMode);
			}
			Motor.getInstance().moveLeftSideMotors(.25);
			Motor.getInstance().moveRightSideMotors(.25);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}

			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(-0.1);

			try {
				Thread.sleep(1100);
			} catch (InterruptedException e) {
			}

			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}

			for (int i = 0; i < 100; i++) {
				Motor.getInstance().moveGrapplerArmMotor(260);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
				}
			}

			Motor.getInstance().moveLeftSideMotors(.25);
			Motor.getInstance().moveRightSideMotors(.25);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);

			waiting = true;
		}

		if (Controller.getInstance().getJoy2Button(2)) {
			for (int i = 0; i < 75; i++) {
				Motor.getInstance().moveGrapplerArmMotor(0);
				if (i > 20) {
					Motor.getInstance().moveWinchMotors(8000);
				}
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
				}
			}

			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(0);

			while (true) {
				Motor.getInstance().moveWinchMotors(8000);
				Motor.getInstance().moveGrapplerArmMotor(-260);
			}
		}

		if (Controller.getInstance().getJoy1Button(2)) {
			waiting = false;
		}

	}

	// arm test values
	// static int armEnc = 0;
	// public static void testArm(){
	// armEnc+=(Controller.getInstance().getRightTrigger())*10;
	//
	// if (Controller.getInstance().getJoy1Button(1)) {
	// armEnc -= 5;
	// }
	// Motor.getInstance().moveGrapplerArmMotor(armEnc);
	// SmartDashboard.putString("DB/String 9", "armEnc: " +
	// Motor.getInstance().getEncArm());
	// }
}
