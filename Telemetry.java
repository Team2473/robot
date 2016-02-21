package org.usfirst.frc.team2473.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import java.util.Arrays;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Telemetry {
	private AnalogInput ultrasonic1;
	private static Telemetry telemetry = null;
	private double vi = 5.0 / 512; 
	
	private Telemetry() {
		ultrasonic1 = new AnalogInput(0);
	}

	public static Telemetry getInstance() {
		if (telemetry == null) {
			telemetry = new Telemetry();
		}
		return telemetry;
	}
	
	//needs to run continuously for values to be correct
	public void updateUltrasonicValue() {
		double rangeInInches = ultrasonic1.getVoltage() / vi;
		
		SmartDashboard.putString("DB/String 1", "Theoretical Value: " + rangeInInches);
		SmartDashboard.putString("DB/String 2", "Voltage " + ultrasonic1.getVoltage());
	}
}