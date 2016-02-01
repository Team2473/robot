package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Motor {
	private CANTalon frontLeft;
	private CANTalon frontRight;
	private CANTalon backLeft;
	private CANTalon backRight;

	// add addition cantalons as they are added to robot
	public static final ControlMode MODE_POWER = ControlMode.PercentVbus;
	public static final ControlMode MODE_POSITION = ControlMode.Position;
	public static final ControlMode MODE_FOLLOWER = ControlMode.Follower;

	// add more modes as necessary
	private static Motor motor = null;

	private Motor() {
		frontLeft = new CANTalon(3);
		frontRight = new CANTalon(2);
		backLeft = new CANTalon(4);
		backRight = new CANTalon(5);

		// test ids

		setUpDriveMotors(frontLeft);
		setUpDriveMotors(frontRight);
		setUpDriveMotors(backLeft);
		setUpDriveMotors(backRight);

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
		tal.changeControlMode(ControlMode.Position);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPID(1, 0, 0); // test pid values
		tal.setPosition(0);
		tal.enableControl();
	}

	public void moveRightSideMotors(double value) {
		if (frontRight.getControlMode() == MODE_POWER) {
			frontRight.set(value);
			backRight.set(value);
		} else if (frontRight.getControlMode() == MODE_POSITION) {
			frontRight.set(value);
			backRight.set(2);// frontRight integer id
		}
		SmartDashboard.putString("DB/String 2",
				"FR: " + frontRight.getEncPosition());
	}

	public void setRightSideMotorsMode(ControlMode mode) {
		if (mode == MODE_POWER) {
			frontRight.changeControlMode(mode);
			backRight.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontRight.changeControlMode(MODE_POSITION);
			backRight.changeControlMode(ControlMode.Follower);
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

	public void setLeftSideMotorsMode(ControlMode mode) {
		if (mode == MODE_POWER) {
			frontLeft.changeControlMode(mode);
			backLeft.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontLeft.changeControlMode(MODE_POSITION);
			frontLeft.reverseOutput(true);
			backLeft.changeControlMode(ControlMode.Follower);
			backLeft.reverseOutput(true);
		}
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
