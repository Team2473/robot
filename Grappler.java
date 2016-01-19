package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class Grappler {
	
	//consult with vishal or william over the motor format
	/*
	 * check the pull request on github.com for the motor branch
	 * learn the motor class format in motor class in the motor branch
	 * write your own motor methods in the motor branch
	 * only use those methods to move motors
	 */
	


	public Grappler(){
		
	}
	public void teleopInit(){
		
	}
	public void teleop(){
		//wait for an input then move motors
		
		//temporary values
		Motor.moveGrapplerArmMotor(2000);
		Motor.moveGrapplerElevatorMotor(10000);
		Motor.moveGrappler(2000);
	}
}
