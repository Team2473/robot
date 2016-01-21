package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

	private static AnalogPotentiometer pot = new AnalogPotentiometer(0); //channel 0;
	
	public static void testPotentiometer(){	
		SmartDashboard.putString("DB/String 0", "Potentiometer" + pot.get());			
	}
	
	public static void pickUp(){
		moveShooterLever(0.5); //potentiometer value
		
	}
}
