package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive {
	CANTalon frontLeft;
	CANTalon frontRight;
	CANTalon backLeft;
	CANTalon backRight;
	Joystick joyOne;
	

	final double maxSpeed = .30;

	final double dz = .05;
	
	final double encScale = 150;
	
	private double bl;
	private double fl;
	
	public TankDrive() {
		//instantiations
		frontLeft = new CANTalon(2);
		frontRight = new CANTalon(3);
		backLeft = new CANTalon(5);
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
		
		
		bl = 0;
		fl = 0;
	
	}
	
	public void teleop () {
		double x1 = joyOne.getX();
		double y1 = -joyOne.getY();
		double x2 = joyOne.getZ();
		double y2 = -joyOne.getThrottle();
		
		fl += maxSpeed * y1;
		
		//backLeft.set(y1*maxSpeed);
		//frontLeft.set(y1*maxSpeed);
		backLeft.set(fl*encScale);
		//frontRight.set(-y2*maxSpeed);
		//backRight.set(-y2*maxSpeed);

		SmartDashboard.putString("DB/String 1", "Fl: " + frontLeft.getEncPosition());
		//SmartDashboard.putString("DB/String 2", "Bl: " + backRight.getEncPosition());
		SmartDashboard.putString("DB/String 2", "Val " + y1);
		SmartDashboard.putString("DB/String 3", "Fr: " + frontRight.getEncPosition());
		SmartDashboard.putString("DB/String 4", "Br: " + backRight.getEncPosition());


//		SmartDashboard.putString("DB/String 6", "Left X: " + x1);
//		SmartDashboard.putString("DB/String 7", "Left Y: " + y1);
//		SmartDashboard.putString("DB/String 8", "Right X: " + x2);
//		SmartDashboard.putString("DB/String 9", "Right Y: " + y2);

		
	}
	
	
	
	private void setEncoder(CANTalon tal) {
		tal.changeControlMode(ControlMode.Position);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPosition(0);
		tal.enableControl();
	}
}



