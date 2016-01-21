package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

	private static AnalogPotentiometer pot = new AnalogPotentiometer(0); //channel 0;
	
	public static void testPotentiometer(){	
		SmartDashboard.putString("DB/String 0", "Potentiometer" + pot.get());			
	}
	
	public void pickUp(){
		moveShooterLever(0.5); //potentiometer value, ground level
		//need to add while loop using lidar
		spinShooter(0.5); //speed value
		moveShooterLever(0.2); //carrying position
	}
	
	public void spitOut(){
		moveShooterLever(0.5); //ground level
		spinShooter(-0.5);
	}
	
	//reset after spitting out ball
	public void reset(){
		spinShooter(0); 
		moveShooterLever(0); //up position
	}
}
