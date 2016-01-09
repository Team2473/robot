package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderTest {
	CANTalon frontLeft;
	CANTalon frontRight;
	CANTalon backLeft;
	CANTalon backRight;
	CANTalon talon;
	
	int prevFLValue;
	int prevFRValue;
	int prevBLValue;
	int prevBRValue;
	
	double encoderTestSpeed = 0.2;
	
	public EncoderTest(){
		frontLeft = new CANTalon(2);
		frontRight = new CANTalon(3);
		backLeft = new CANTalon(5);
		backRight = new CANTalon(4);
	}
	public void teleopInit(){
		setEncoder(backLeft);
		setEncoder(backRight);
		setEncoder(frontLeft);
		setEncoder(frontRight);
		
		prevFLValue = frontLeft.getEncPosition();
		prevFRValue = frontRight.getEncPosition();
		prevBLValue = backLeft.getEncPosition();
		prevBRValue = backRight.getEncPosition();
	}
	public void teleop(){
		backLeft.set(encoderTestSpeed);
		frontLeft.set(encoderTestSpeed);
		frontRight.set(encoderTestSpeed);
		backRight.set(encoderTestSpeed);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backLeft.set(0);
		frontLeft.set(0);
		frontRight.set(0);
		backRight.set(0);
		
		if(frontLeft.getEncPosition() == prevFLValue){
			SmartDashboard.putString("DB/String 1", "Fl: Broken");
		}else{
			SmartDashboard.putString("DB/String 1", "Fl: Working");
		}
		
		if(frontRight.getEncPosition() == prevFRValue){
			SmartDashboard.putString("DB/String 2", "Fr: Broken");
		}else{
			SmartDashboard.putString("DB/String 2", "Fr: Working");
		}
		
		if(backLeft.getEncPosition() == prevBLValue){
			SmartDashboard.putString("DB/String 3", "Bl: Broken");
		}else{
			SmartDashboard.putString("DB/String 3", "Bl: Working");
		}
		
		if(backRight.getEncPosition() == prevBRValue){
			SmartDashboard.putString("DB/String 4", "Br: Broken");
		}else{
			SmartDashboard.putString("DB/String 4", "Br: Working");
		}
		
//		SmartDashboard.putString("DB/String 5", "Fl: " + frontLeft.getEncPosition());
//		SmartDashboard.putString("DB/String 6", "Bl: " + backLeft.getEncPosition());
//		SmartDashboard.putString("DB/String 7", "Fr: " + frontRight.getEncPosition());
//		SmartDashboard.putString("DB/String 8", "Br: " + backRight.getEncPosition());
		
//		prevFLValue = frontLeft.getEncPosition();
//		prevFRValue = frontRight.getEncPosition();
//		prevBLValue = backLeft.getEncPosition();
//		prevBRValue = backRight.getEncPosition();
	}
	private void setEncoder(CANTalon tal) {
//		System.out.println("Current mode is: " + tal.getControlMode());
		tal.changeControlMode(ControlMode.PercentVbus);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//tal.setPID(.020, 0.000003, 0.0); //scales power difference, the longer you haven't reached goal > faster it gets, how quickly you move towards the goal
//		tal.setPID(2.0, 0.0, 0.0);
		tal.setPosition(0);
		tal.enableControl();
	}
}
