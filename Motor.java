package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class Motor {
	static CANTalon frontRight;
	static CANTalon frontLeft;
	static CANTalon backRight;
	static CANTalon backLeft;
	//add addition cantalons as they are added to robot
	public static final ControlMode MODE_POWER = ControlMode.PercentVbus;
	public static final ControlMode MODE_POSITION = ControlMode.Position;
	//add more modes as necessary
	
	public static void motorInit(){
		frontRight = new CANTalon(-1/*sampleID*/);
		frontLeft = new CANTalon(-1/*sampleID*/);
		backRight = new CANTalon(-1/*sampleID*/);
		backLeft = new CANTalon(-1/*sampleID*/);
		setUp(frontRight);
		setUp(frontLeft);
		setUp(backRight);
		setUp(backLeft);
		//add addition cantalons as they are added to robot
		
	}
	
	//Should only run once for each cantalon
	private static void setUp(CANTalon tal) {
		tal.changeControlMode(ControlMode.PercentVbus);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPosition(0);
		tal.enableControl();
	}
	
	public static void moveLeftSideMotors(ControlMode mode, int value){
		if(frontLeft.getControlMode() != mode){
			frontLeft.changeControlMode(mode);
		}
		if(backLeft.getControlMode() != mode){
			backLeft.changeControlMode(mode);
		}
		
		//Set front and back left motors to Value
		
	}
	
	public static void moveRightSideMotors(ControlMode mode, int value){
		if(frontRight.getControlMode() != mode){
			frontRight.changeControlMode(mode);
		}
		if(backRight.getControlMode() != mode){
			backRight.changeControlMode(mode);
		}
		
		//Set front and back right motors to Value
		
	}
}
