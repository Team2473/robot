package org.usfirst.frc.team2473.robot;

public class Shooter {
	AnalogPotentiometer pot = new AnalogPotentiometer(0); //channel 0;
	
	SmartDashboard.putString("DB/String 3", "Potentiometer", + pot.get());
}
