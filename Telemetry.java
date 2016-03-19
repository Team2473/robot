package org.usfirst.frc.team2473.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import java.util.Arrays;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Telemetry {
	//Ultrasonic
	private AnalogInput ultrasonicLeft; 
	private AnalogInput ultrasonicRight; 
	
	//Gyro
	private AnalogGyro gyro;

	//breakbeam
	private DigitalInput breakBeam;
	
	private static Telemetry telemetry = null;

	//for calculating ultrasonic values
	private double vi = 5.0 / 512; 
	
	private double[] vals = new double[8];
	private boolean firstTime = true;

	private Telemetry() {
		ultrasonicRight = new AnalogInput(0);
		
//		gyro = new AnalogGyro(1);
//		gyro.calibrate();
		
		breakBeam   = new DigitalInput(0);
	}

	public static Telemetry getInstance() {
		if (telemetry == null) {
			telemetry = new Telemetry();
		}
		return telemetry;
	}

	//Just prints out values
	public void updateUltrasonicValue() {
		if (firstTime) {
			for (int i = 0; i < 8; i++) {
				vals[i] = getUltrasonicRight();
			}
			firstTime = false;
		}
		else {
			for(int i = 0; i < 7; i++) {
				vals[i] = vals[i + 1];
			}
			vals[7] = getUltrasonicRight();
		}
	}

	//Returns range in in. for left ultrasonic sensor
	public double getUltrasonicLeft() {
		return (ultrasonicLeft.getVoltage() / vi);
	}

	//Returns range in in. for left ultrasonic sensor
	public double getUltrasonicRight() {
		return (ultrasonicRight.getVoltage() / vi);
	}
	
	//Returns range in in. for left ultrasonic sensor
	public double getAvgRight() {
		return arrayAvg(vals);
	}
	
	public double arrayAvg(double[] arr) {
		double sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return (sum/arr.length);
	}
	
	//Prints out Gyro Angle
	public void updateGyroValue () {
		SmartDashboard.putString("DB/String 2", "G:" + gyro.getAngle());
	}
	
	//Returns Gyro Value in Degrees
	public double getGyro () {
		return gyro.getAngle();
	}
	
	//Resets Gyro to 0 degrees
	public void resetGyro () {
		gyro.reset();
	}
	
	public boolean getBreakBeam(){
		return breakBeam.get();
	}
}
