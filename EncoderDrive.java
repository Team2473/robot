package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderDrive {
	CANTalon frontLeft;
	CANTalon frontRight;
	CANTalon backLeft;
	CANTalon backRight;
	Joystick joyOne;
	
	final double maxSpeed = 0.50;
	final double dz = .05;
	
	double fl = 0;
	double fr = 0;
	double bl = 0;
	double br = 0;
	
	int frTime = 0;
	int brTime = 0;
	int flTime = 0;
	int blTime = 0;
	
	int frJoystick = 0;
	int brJoystick = 0;
	int flJoystick = 0;
	int blJoystick = 0;
	
	int loopTime = 0;
	
	boolean encBroke = false;
	
	public EncoderDrive() {
		//instantiations
		frontLeft = new CANTalon(2);
		frontRight = new CANTalon(5);
		backLeft = new CANTalon(3);
		backRight = new CANTalon(4);
		
		joyOne = new Joystick(0);
		
		//configure encoders
		
		
		//configure potentiometers
		
	}
	
	public void teleopInit(){
		setEncoder(backLeft);
		setEncoder(backRight);
		setEncoder(frontLeft);
		setEncoder(frontRight);
		frontLeft.reverseSensor(true);
		frontRight.reverseSensor(true);
		
		fl = 0;
		fr = 0;
		bl = 0;
		br = 0;
	}
	
	public void teleop () {
		
		
		double x = joyOne.getX();
		double y = -joyOne.getY();
		double z = joyOne.getZ();
		
		double angle = (x == 0)?90:Math.toDegrees(Math.atan(Math.abs(y)/Math.abs(x)));
		double magnitude = Math.min(Math.sqrt(x*x + y*y),1);
		
		if(z > .20 || z < -.20){
			fl +=z*maxSpeed;
			fr +=z*maxSpeed;
			bl+=z*maxSpeed;
			br+=z*maxSpeed;
		}

		if((y >= 0 && x >= 0) && (magnitude > dz)){ //Q1
			fl += maxSpeed * magnitude;
			fr -= maxSpeed*(angle / 45 - 1) * magnitude;
			br -= maxSpeed * magnitude;
			bl += maxSpeed*(angle / 45 - 1) * magnitude;
		}else if((y > 0 && x < 0) && (magnitude > dz)){// Q2
			fl += maxSpeed*(angle / 45 - 1) * magnitude;
			fr -= maxSpeed * magnitude;
			br -= maxSpeed*(angle / 45 - 1) * magnitude;
			bl += maxSpeed * magnitude;
		}else if((y < 0 && x < 0) && (magnitude > dz)){// Q3
			fl -= maxSpeed * magnitude;
			fr += maxSpeed*(angle / 45 - 1) * magnitude;
			br += maxSpeed * magnitude;
			bl -= maxSpeed*(angle / 45 - 1) * magnitude;
		}else if((y < 0 && x > 0) && (magnitude > dz)) {// Q4
			fl -= maxSpeed*(angle / 45 - 1) * magnitude;
			fr += maxSpeed * magnitude;
			br += maxSpeed*(angle / 45 - 1) * magnitude;
			bl -= maxSpeed * magnitude;
		}
	
	
			
		
		if(joyOne.getRawButton(6)){
			encBroke = true;
		}
		
		//CANTalon.getSpeed()
		//CANTalon.getEncVelocity()
		
		frontRight.set(fr*150);
		frontLeft.set(fl*150);
		backRight.set(br*150);
		backLeft.set(bl*150);
		
		if(frontRight.getEncVelocity() < 0){ 
			frTime++;
		}else{
			frTime = 0;
			frJoystick = 0;
		}
		
		if(backRight.getEncVelocity() < 0){
			brTime++;
		}else{
			brTime = 0;
			brJoystick = 0;
		}
		
		if(frontLeft.getEncVelocity() < 0){
			flTime++;
		}else{
			flTime = 0;
			flJoystick = 0;
		}
		
		if(backLeft.getEncVelocity() < 0){
			blTime++;
		}else{
			blTime = 0;
			blJoystick = 0;
		}
		
		if((z > 0.40 || z < -0.40) || (y > .40 || y < -.40) || (x > .40 || x < -.40) && frJoystick == 0){
			frJoystick = frTime;
		}
		
		if((z > 0.40 || z < -0.40) || (y > .40 || y < -.40) || (x > .40 || x < -.40) && brJoystick == 0){
			brJoystick = brTime;
		}
		
		if((z > 0.40 || z < -0.40) || (y > .40 || y < -.40) || (x > .40 || x < -.40) && flJoystick == 0){
			flJoystick = flTime;
		}
		
		if((z > 0.40 || z < -0.40) || (y > .40 || y < -.40) || (x > .40 || x < -.40) && blJoystick == 0){
			blJoystick = blTime;
		}
		
		if((frTime - frJoystick > 50 && frJoystick != 0)||(brTime - brJoystick > 50 && brJoystick != 0)||(flTime - flJoystick > 50 && flJoystick != 0)||(blTime - blJoystick > 50 && blJoystick != 0)){
			encBroke = true;
		}
		
		SmartDashboard.putString("DB/String 2", "angle: " + Math.round(angle));
		SmartDashboard.putString("DB/String 3", "magnitude: " + Math.round(magnitude*100)/100.0);
		SmartDashboard.putString("DB/String 4", "zpos: " + Math.round(z*100)/100.0);
		SmartDashboard.putString("DB/String 6", "FL EncPos: " + frontLeft.getPosition());
		SmartDashboard.putString("DB/String 7", "FR EncPos: " + frontRight.getPosition());
		SmartDashboard.putString("DB/String 8", "BL EncPos: " + backLeft.getPosition());
		SmartDashboard.putString("DB/String 9", "BR EncPos: " + backRight.getPosition());
		
		loopTime++;
		SmartDashboard.putString("DB/String 0", "Loop time: " + loopTime);
	}
	
	
	
	public boolean isBroke(){
		return encBroke;
	}
	
	private void setEncoder(CANTalon tal) {
//		System.out.println("Current mode is: " + tal.getControlMode());
		tal.changeControlMode(ControlMode.Position);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//tal.setPID(.020, 0.000003, 0.0); //scales power difference, the longer you haven't reached goal > faster it gets, how quickly you move towards the goal
//		tal.setPID(2.0, 0.0, 0.0);
		tal.setPosition(0);
		tal.enableControl();
	}
}
