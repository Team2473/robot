package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Motor {
	private static CANTalon frontRight;
	private static CANTalon frontLeft;
	private static CANTalon backRight;
	private static CANTalon backLeft;
	
	private static CANTalon arm;		//the motor to move the arm into position
	private static CANTalon elevator;	//the motor to extend the arm
	private static CANTalon grappler;	//the motor to grab onto the rung and pull up

	private static CANTalon shooterLever; //pick up arm for shooter
	private static CANTalon spinner1; //spinners to grab ball
	private static CANTalon spinner2;

	// add addition cantalons as they are added to robot
	public static final ControlMode MODE_POWER = ControlMode.PercentVbus;
	public static final ControlMode MODE_POSITION = ControlMode.Position;
	public static final ControlMode MODE_FOLLOWER = ControlMode.Follower;

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

		shooterLever = new CANTalon(0);
		spinner1 = new CANTalon(0);
		spinner2 = new CANTalon(0);

		setUpDriveMotors(frontRight);
		setUpDriveMotors(frontLeft);
		setUpDriveMotors(backRight);
		setUpDriveMotors(backLeft);
		
		setUpArm(arm);
		setUpElevator(elevator);
		setUpGrappler(grappler);

		setUpShooterLever(shooterLever);
		setUpSpinners(spinner1);
		setUpSpinners(spinner2);
		// add addition cantalons as they are added to robot

	}

	// Should only run once for each cantalon
	private static void setUpDriveMotors(CANTalon tal) {
		tal.changeControlMode(ControlMode.Position);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPID(.1,0,0); //test pid values
		tal.setPosition(0);
		tal.enableControl();
	}
	
	private static void setUpArm(CANTalon tal) {
		tal.changeControlMode(ControlMode.Position);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPID(.1,0,0); //test pid values
		tal.setPosition(0);
		tal.enableControl();
	}
	
	private static void setUpElevator(CANTalon tal) {
		tal.changeControlMode(ControlMode.Position);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPID(.1,0,0); //test pid values
		tal.setPosition(0);
		tal.enableControl();
	}
	
	private static void setUpGrappler(CANTalon tal) {
		tal.changeControlMode(ControlMode.Position);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPID(.1,0,0); //test pid values
		tal.setPosition(0);
		tal.enableControl();
	}
	
	
	//potentiometer: incomplete
	private static void setUpShooterLever(CANTalon tal) {
		tal.changeControlMode(ControlMode.Voltage);
		tal.setFeedBackDevice(FeedbackDevice.AnalogPot);
		tal.setPID(.1,0,0); //test pid values
		tal.setPosition(0);
		tal.enableControl();
	}
	
	private static void setUpSpinners(CANTalon tal) {
		tal.changeControlMode(ControlMode.PercentVbus);
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
		SmartDashboard.putString("DB/String 2", "FL: " + frontLeft.getEncPosition() );
	}

	public static void setLeftSideMotorsMode(ControlMode mode) {
		if (mode == MODE_POWER) {
			frontLeft.changeControlMode(mode);
			backLeft.changeControlMode(mode);
		} else if (mode == MODE_POSITION) {
			frontLeft.changeControlMode(MODE_POSITION);
			//set pid for front left
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
		
		SmartDashboard.putString("DB/String 3", "FR: " + frontRight.getEncPosition() );
	}

	public static void setRightSideMotorsMode(ControlMode mode) {
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

	
	public static void moveGrapplerArmMotor(double value){
		arm.set(value);
	}
	
	public static void moveGrapplerElevatorMotor(double value){
		elevator.set(value);
	}
	
	public static void moveGrapplerMotor(double value){
		grappler.set(value);
	}
	
	public static void moveShooterLever(double value){
		shooterLever.set(value);
	}
	
	public static void spinShooter(double value){ //runs on speed
		spinner1.set(value);
		spinner2.set(value);
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
