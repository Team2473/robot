package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class Motor {
	private static CANTalon frontRight;
	private static CANTalon frontLeft;
	private static CANTalon backRight;
	private static CANTalon backLeft;
	
	private static CANTalon arm;		//the motor to move the arm into position
	private static CANTalon elevator;	//the motor to extend the arm
	private static CANTalon grappler;	//the motor to grab onto the rung and pull up
	// add addition cantalons as they are added to robot
	public static final ControlMode MODE_POWER = ControlMode.PercentVbus;
	public static final ControlMode MODE_POSITION = ControlMode.Position;

	// add more modes as necessary

	public static void motorInit() {
		frontRight = new CANTalon(3);
		frontLeft = new CANTalon(2);
		backRight = new CANTalon(4);
		backLeft = new CANTalon(5);
		
		//test ids
		arm = new CANTalon(0);
		elevator = new CANTalon(0);
		grappler = new CANTalon(0);
		
		setUp(frontRight);
		setUp(frontLeft);
		setUp(backRight);
		setUp(backLeft);
		
		setUp(arm);
		setUp(elevator);
		setUp(grappler);
		// add addition cantalons as they are added to robot

	}

	// Should only run once for each cantalon
	private static void setUp(CANTalon tal) {
		tal.changeControlMode(ControlMode.Position);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPosition(0);
		tal.enableControl();
	}

	public static void moveLeftSideMotors(double value) {
		if(frontLeft.getControlMode() == MODE_POWER){
			frontLeft.set(value);
			backLeft.set(value);
		}else if(frontLeft.getControlMode() == MODE_POSITION){
			frontLeft.set(value);
			backLeft.set(2);//frontleft integer id
		}
	}

	public static void setLeftSideMotorsMode(ControlMode mode) {
		if (mode == MODE_POWER) {
			frontLeft.changeControlMode(mode);
			backLeft.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontLeft.changeControlMode(MODE_POSITION);
			backLeft.changeControlMode(ControlMode.Follower);
		}
	}

	public static void moveRightSideMotors(double value) {
		if(frontRight.getControlMode() == MODE_POWER){
			frontRight.set(-value);
			backRight.set(-value);
		}else if(frontRight.getControlMode() == MODE_POSITION){
			frontRight.set(value);
			backRight.set(3);//frontright integer id
		}

	}

	public static void setRightSideMotorsMode(ControlMode mode) {
		if (mode == MODE_POWER) {
			frontRight.changeControlMode(mode);
			backRight.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontRight.changeControlMode(MODE_POSITION);
			backRight.changeControlMode(ControlMode.Follower);
		}
	}
	
	public static void moveGrapplerArmMotor(double value){
		arm.set(value);
	}
	
	public static void moveGrapplerElevatorMotor(double value){
		elevator.set(value);
	}
	
	public static void moveGrapplerMotor(double value){
		grappler.set(value);
	}

	// create additional move methods using the below format
	/*
	public static void moveSAMPLE_MOTORMotors(ControlMode mode, int value) {
		if(SAMPLE_MOTOR.getControlMode() == MODE_POWER){
			SAMPLE_MOTOR.set(value);
		}else if(SAMPLE_MOTOR.getControlMode() == MODE_POSITION){
			SAMPLE_MOTOR.set(value);
		}

	}

	public static void setSAMPLE_MOTORMotorsMode(ControlMode mode) {
		if (mode == MODE_POWER) {
			SAMPLE_MOTOR.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			SAMPLE_MOTOR.changeControlMode(MODE_POSITION);
		}
	}
	 */
}
