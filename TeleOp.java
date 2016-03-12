package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp {

	private static CANTalon.TalonControlMode currentMode = null;
	private static double deadZone = .02;
	private static double maxSpeed = .23;
	private static double currentThreshold = 1.5;
	private static Controller cont = Controller.getInstance();
	private static Motor mot = Motor.getInstance();
	public static void runPower() {
		if (Math.abs(mot.frontLeft.getOutputCurrent()
				- mot.backLeft.getOutputCurrent()) < currentThreshold
				&& Math.abs(mot.frontRight.getOutputCurrent()
						- mot.backRight.getOutputCurrent()) < currentThreshold) {
			if (waiting) {
				return;
			}

			if (currentMode != Motor.MODE_POWER) {
				currentMode = Motor.MODE_POWER;

				mot.setLeftSideMotorsMode(currentMode);
				mot.setRightSideMotorsMode(currentMode);
			}

			double leftY = cont.getYL() / Math.abs(cont.getYL())
					* Math.sqrt(Math.abs(cont.getYL()));
			double rightY = cont.getYR() / Math.abs(cont.getYR())
					* Math.sqrt(Math.abs(cont.getYR()));

			if (Math.abs(cont.getYL()) > deadZone) {
				mot.moveLeftSideMotors(leftY * maxSpeed * 1.06);
			} else {
				mot.moveLeftSideMotors(0);
			}

			if (Math.abs(cont.getYR()) > deadZone) {
				mot.moveRightSideMotors(rightY * maxSpeed);
			} else {
				mot.moveRightSideMotors(0);
			}

			SmartDashboard.putString("DB/String 6", "LY: " + leftY);
			SmartDashboard.putString("DB/String 7", "RY: " + rightY);
		} else {
			SmartDashboard.putString("DB/String 0", "DRIVE MOTOR FAILURE");
		}
	}

	public static void runPowerReverse() {
		if (Math.abs(mot.frontLeft.getOutputCurrent()
				- mot.backLeft.getOutputCurrent()) < currentThreshold
				&& Math.abs(mot.frontRight.getOutputCurrent()
						- mot.backRight.getOutputCurrent()) < currentThreshold) {
			if (waiting) {
				return;
			}
			if (currentMode != Motor.MODE_POWER) {
				currentMode = Motor.MODE_POWER;

				mot.setLeftSideMotorsMode(currentMode);
				mot.setRightSideMotorsMode(currentMode);
			}

			double leftY = cont.getYRNeg() / Math.abs(cont.getYRNeg())
					* Math.sqrt(Math.abs(cont.getYRNeg()));
			double rightY = cont.getYLNeg() / Math.abs(cont.getYLNeg())
					* Math.sqrt(Math.abs(cont.getYLNeg()));

			if (Math.abs(cont.getYRNeg()) > deadZone) {
				mot.moveLeftSideMotors(leftY * maxSpeed * 1.06);
			} else {
				mot.moveLeftSideMotors(0);
			}

			if (Math.abs(cont.getYLNeg()) > deadZone) {
				mot.moveRightSideMotors(rightY * maxSpeed);
			} else {
				mot.moveRightSideMotors(0);
			}

			SmartDashboard.putString("DB/String 6", "LY: " + leftY);
			SmartDashboard.putString("DB/String 7", "RY: " + rightY);
		} else {
			SmartDashboard.putString("DB/String 0", "DRIVE MOTOR FAILURE");
		}
	}

	private static int leftEnc;
	private static int rightEnc;

	public static void runPosition() {
		if (currentMode != Motor.MODE_POSITION) {
			currentMode = Motor.MODE_POSITION;

			mot.setLeftSideMotorsMode(currentMode);
			mot.setRightSideMotorsMode(currentMode);

			leftEnc = 0;
			rightEnc = 0;
			mot.resetDriveEncoders();
		}

		double leftY = cont.getYL();
		double rightY = cont.getYR();

		if (Math.abs(leftY) > deadZone) {
			leftEnc += leftY * 75;
		}

		if (Math.abs(rightY) > deadZone) {
			rightEnc += rightY * 50;
		}

		mot.moveLeftSideMotors(leftEnc);
		mot.moveRightSideMotors(rightEnc);

	}

	private static boolean waiting = false;

	public static void runUtilities() {

		if (cont.getJoy1Button(6)) {
			maxSpeed = .5;
		} else {
			maxSpeed = .23;
		}

		if (cont.getJoy2Button(1)) {
			if (currentMode != Motor.MODE_POWER) {
				currentMode = Motor.MODE_POWER;

				mot.setLeftSideMotorsMode(currentMode);
				mot.setRightSideMotorsMode(currentMode);
			}
			mot.moveLeftSideMotors(.25);
			mot.moveRightSideMotors(.25);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}

			mot.moveLeftSideMotors(0);
			mot.moveRightSideMotors(-0.1);

			try {
				Thread.sleep(1100);
			} catch (InterruptedException e) {
			}

			mot.moveLeftSideMotors(0.09);
			mot.moveRightSideMotors(0.09);

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}

			for (int i = 0; i < 100; i++) {
				mot.moveGrapplerArmMotor(260);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
				}
			}

			mot.moveLeftSideMotors(.25);
			mot.moveRightSideMotors(.25);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			mot.moveLeftSideMotors(0.09);
			mot.moveRightSideMotors(0.09);

			waiting = true;
		}

		if (cont.getJoy2Button(2)) {
			for (int i = 0; i < 75; i++) {
				mot.moveGrapplerArmMotor(0);
				if (i > 20) {
					mot.moveWinchMotors(8000);
				}
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
				}
			}

			mot.moveLeftSideMotors(0);
			mot.moveRightSideMotors(0);

			while (true) {
				mot.moveWinchMotors(8000);
				mot.moveGrapplerArmMotor(-260);
			}
		}

		if (cont.getJoy1Button(2)) {
			waiting = false;
		}

	}

	// arm test values
	// static int armEnc = 0;
	// public static void testArm(){
	// armEnc+=(cont.getRightTrigger())*10;
	//
	// if (cont.getJoy1Button(1)) {
	// armEnc -= 5;
	// }
	// mot.moveGrapplerArmMotor(armEnc);
	// SmartDashboard.putString("DB/String 9", "armEnc: " +
	// mot.getEncArm());
	// }
}