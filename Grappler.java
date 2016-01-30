package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class Grappler {
	
	/*
	 * Current explanation of Grappler:
	 * 1 or 2 motors to raise the arm with the claw to the bar. The claw does not need
	 * software to run, and once the claw has grasped the bar, we use the winch to lift 
	 * the robot. The winch is another single motor not connected to the grappler.
	 */
	private static Grappler grappler = null;
	
	private Grappler(){
		
	}
	
	public static Grappler getInstance() {
		if (grappler == null) {
			grappler = new Grappler();
		}
		return grappler;
	}
	//adjust values for following methods
	public void moveArmUp() {
		Motor.getInstance().moveGrapplerArmMotor(100);
		Motor.getInstance().moveGrapplerElevatorMotor(100);
	}
	public void moveArmDown() {
		Motor.getInstance().moveGrapplerArmMotor(-100);
		Motor.getInstance().moveGrapplerElevatorMotor(-100);
	}
	public void moveWinch(){
		Motor.getInstance().moveWinchMotor(0);
	}
	
}
