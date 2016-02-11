package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
//import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public void checkLS(){
		//talon 6 = TALON for arm + limit switches
		Motor.getInstance().arm.ConfigFwdLimitSwitchNormallyOpen(true); //how do i access the arm talon?? placeholder is currently "arm"
		Motor.getInstance().arm.ConfigRevLimitSwitchNormallyOpen(true);
		
		SmartDashboard.putString("DB/String 3", "Fwd: " + Motor.getInstance().arm.isFwdLimitSwitchClosed());
		SmartDashboard.putString("DB/String 4", "Rev: " + Motor.getInstance().arm.isRevLimitSwitchClosed());
	}
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
