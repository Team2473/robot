package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Motor {
	private CANTalon frontLeft;
	private CANTalon frontRight;
	private CANTalon backLeft;
	private CANTalon backRight;

	private CANTalon arm; // the motor to move the arm into position
	private CANTalon winch1; // the motor to extend the arm
	private CANTalon winch2; // the motor to pull the robot up

	private CANTalon shooterLever; // pick up arm for shooter
	private CANTalon spinner1; // spinners to grab ball
	private CANTalon spinner2;

	// add addition cantalons as they are added to robot
	public static final CANTalon.TalonControlMode MODE_POWER = CANTalon.TalonControlMode.PercentVbus;
	public static final CANTalon.TalonControlMode MODE_POSITION = CANTalon.TalonControlMode.Position;
	public static final CANTalon.TalonControlMode MODE_FOLLOWER = CANTalon.TalonControlMode.Follower;

	// add more modes as necessary
	private static Motor motor = null;

	private Motor() {
		frontLeft = new CANTalon(3);
		frontRight = new CANTalon(7);
		backLeft = new CANTalon(8);
		backRight = new CANTalon(2);

		// test ids

		arm = new CANTalon(5);
		winch1 = new CANTalon(1);
		winch2 = new CANTalon(4);

		shooterLever = new CANTalon(6);
		spinner1 = new CANTalon(0);
		spinner2 = new CANTalon(0);

		setUpDriveMotors(frontLeft);
		setUpDriveMotors(frontRight);
		setUpDriveMotors(backLeft);
		setUpDriveMotors(backRight);

		setUpArm();
		setUpWinches();

		setUpShooterLever();
		setUpSpinners();
		// add addition cantalons as they are added to robot
	}

	public static Motor getInstance() {
		if (motor == null) {
			motor = new Motor();
		}
		return motor;

	}

	// Should only run once for each cantalon
	private void setUpDriveMotors(CANTalon tal) {
		tal.changeControlMode(MODE_POSITION);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPID(.8, 0, 0.02); // test pid values
		tal.setPosition(0);
		tal.enableControl();
	}

	private void setUpArm() {
		arm.changeControlMode(MODE_POWER);
		arm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		// arm.ConfigRevLimitSwitchNormallyOpen(false);
		// arm.ConfigFwdLimitSwitchNormallyOpen(false);
		arm.enableControl();
	}

	private void setUpWinches() {
		winch1.changeControlMode(MODE_POWER);
		winch1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		winch1.enableControl();

		winch2.changeControlMode(MODE_FOLLOWER);
		winch2.enableControl();
	}

	private void setUpShooterLever() {
		shooterLever.changeControlMode(CANTalon.TalonControlMode.Voltage);
		shooterLever.setFeedbackDevice(FeedbackDevice.AnalogPot);
		shooterLever.setPID(.1, 0, 0); // test pid values
		shooterLever.setPosition(0);
		shooterLever.enableControl();
	}

	private void setUpSpinners() {
		spinner1.changeControlMode(MODE_POWER);
		spinner1.enableControl();

		spinner2.changeControlMode(MODE_POWER);
		spinner2.enableControl();
	}

	public void moveRightSideMotors(double value) {
		if (frontRight.getControlMode() == MODE_POWER) {
			frontRight.set(value);
			backRight.set(value);
		} else if (frontRight.getControlMode() == MODE_POSITION) {
			frontRight.set(value);
			backRight.set(7);// frontRight integer id
		}
		SmartDashboard.putString("DB/String 2",
				"FR: " + frontRight.getEncPosition());
	}

	public void setRightSideMotorsMode(CANTalon.TalonControlMode mode) {
		if (mode == MODE_POWER) {
			frontRight.changeControlMode(mode);
			frontRight.ConfigRevLimitSwitchNormallyOpen(true);
			frontRight.ConfigFwdLimitSwitchNormallyOpen(true);
			backRight.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontRight.changeControlMode(MODE_POSITION);
			frontRight.ConfigRevLimitSwitchNormallyOpen(true);
			frontRight.ConfigFwdLimitSwitchNormallyOpen(true);
			backRight.changeControlMode(MODE_FOLLOWER);
		}
	}

	public void moveLeftSideMotors(double value) {
		if (frontLeft.getControlMode() == MODE_POWER) {
			frontLeft.set(-value);
			backLeft.set(-value);
		} else if (frontLeft.getControlMode() == MODE_POSITION) {
			frontLeft.set(value);
			backLeft.set(3);// frontLeft integer id
		}

		SmartDashboard.putString("DB/String 3",
				"FL: " + frontLeft.getEncPosition());
	}

	public void setLeftSideMotorsMode(CANTalon.TalonControlMode mode) {
		if (mode == MODE_POWER) {
			frontLeft.changeControlMode(mode);
			frontLeft.reverseOutput(false);
			backLeft.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontLeft.changeControlMode(MODE_POSITION);
			frontLeft.reverseOutput(true);
			backLeft.changeControlMode(MODE_FOLLOWER);
			// backLeft.reverseOutput(true);
		}
	}

	public void moveGrapplerArmMotor(double value) {
		arm.set(value);
		SmartDashboard.putString("DB/String 6", "Arm: " + arm.getEncPosition());
	}

	public void moveWinchMotors(double value) {
		winch1.set(value);
		winch2.set(1); /* winch1 motor id */
		SmartDashboard.putString("DB/String 8", "Winch: " + winch1.getEncPosition());
	}

	public void moveShooterLever(double value) {
		shooterLever.set(value);
	}

	public void spinShooter(double value) { // runs on speed
		spinner1.set(value);
		spinner2.set(value);
	}

	public int getEncoder(CANTalon motor) {
		return motor.getEncPosition();
	}

	public void resetDriveEncoders() {
		frontLeft.setPosition(0);
		frontRight.setPosition(0);
		backLeft.setPosition(0);
		backRight.setPosition(0);
	}

	// create additional move methods using the below format
	/*
	 * public void moveSAMPLE_MOTORMotors(ControlMode mode, int value) {
	 * if(SAMPLE_MOTOR.getControlMode() == MODE_POWER){ SAMPLE_MOTOR.set(value);
	 * }else if(SAMPLE_MOTOR.getControlMode() == MODE_POSITION){
	 * SAMPLE_MOTOR.set(value); }
	 * 
	 * }
	 * 
	 * public void setSAMPLE_MOTORMotorsMode(ControlMode mode) { if (mode ==
	 * MODE_POWER) { SAMPLE_MOTOR.changeControlMode(mode); } else if (mode ==
	 * MODE_POSITION) { SAMPLE_MOTOR.changeControlMode(MODE_POSITION); } }
	 */
}
