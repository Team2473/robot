package org.usfirst.frc.team2473.robot;

public class Motor {
<<<<<<< HEAD
=======
	private CANTalon frontRight;
	private CANTalon frontLeft;
	private CANTalon backRight;
	private CANTalon backLeft;

	// add addition cantalons as they are added to robot
	public static final ControlMode MODE_POWER = ControlMode.PercentVbus;
	public static final ControlMode MODE_POSITION = ControlMode.Position;
	public static final ControlMode MODE_FOLLOWER = ControlMode.Follower;

	// add more modes as necessary
	private static Motor motor = null;
	
	private Motor() {
		frontRight = new CANTalon(3);
		frontLeft = new CANTalon(2);
		backRight = new CANTalon(4);
		backLeft = new CANTalon(5);

		// test ids

		setUpDriveMotors(frontRight);
		setUpDriveMotors(frontLeft);
		setUpDriveMotors(backRight);
		setUpDriveMotors(backLeft);

		// add addition cantalons as they are added to robot

	}

	public static Motor getInstance(){
		if(motor == null){
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

	public void moveLeftSideMotors(double value) {
		if (frontLeft.getControlMode() == MODE_POWER) {
			frontLeft.set(value);
			backLeft.set(value);
		} else if (frontLeft.getControlMode() == MODE_POSITION) {
			frontLeft.set(value);
			backLeft.set(2);// frontleft integer id
		}
		SmartDashboard.putString("DB/String 2",
				"FL: " + frontLeft.getEncPosition());
	}

	public void setLeftSideMotorsMode(ControlMode mode) {
		if (mode == MODE_POWER) {
			frontLeft.changeControlMode(mode);
			backLeft.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontLeft.changeControlMode(MODE_POSITION);
			backLeft.changeControlMode(ControlMode.Follower);
		}
	}

	public void moveRightSideMotors(double value) {
		if (frontRight.getControlMode() == MODE_POWER) {
			frontRight.set(-value);
			backRight.set(-value);
		} else if (frontRight.getControlMode() == MODE_POSITION) {
			frontRight.set(value);
			backRight.set(3);// frontright integer id
		}

		SmartDashboard.putString("DB/String 3",
				"FR: " + frontRight.getEncPosition());
	}

	public void setRightSideMotorsMode(ControlMode mode) {
		if (mode == MODE_POWER) {
			frontRight.changeControlMode(mode);
			backRight.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontRight.changeControlMode(MODE_POSITION);
			frontRight.reverseOutput(true);
			backRight.changeControlMode(ControlMode.Follower);
			backRight.reverseOutput(true);
		}
	}
	
	public void resetDriveEncoders(){
		frontRight.setPosition(0);
		frontLeft.setPosition(0);
		backRight.setPosition(0);
		backLeft.setPosition(0);
	}
>>>>>>> parent of 164600f... added get encoder method in motor

}
