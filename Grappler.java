package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class Grappler {
	CANTalon arm;
	CANTalon elevator;
	CANTalon reel;
	
	public Grappler(){
		//change IDs
		arm = new CANTalon(0);
		elevator = new CANTalon(0);
		reel = new CANTalon(0);
	}
	public void teleop(){
		//wait for an input then move motors
		
	}
	public void armLaunchMotor() {
		//1st stage
	}
	public void elevatorMotor() {
		//2nd stage
		
	}
	public void reelingMotor() {
		//3rd stage: 2 feet up
	}
}
