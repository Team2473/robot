package org.usfirst.frc.team2473.robot;


import edu.wpi.first.wpilibj.DigitalInput;

import java.util.Arrays;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Telemetry {
	//Ultrasonic
	private AnalogInput ultrasonicLeft; //Analog 0
	private AnalogInput ultrasonicRight; //Analog 1
	
	//Gyro
	private AnalogGyro gyro;

	private static Telemetry telemetry = null;

	//for calculating ultrasonic values
	private double vi = 5.0 / 512; 

	private Telemetry() {
		ultrasonicLeft = new AnalogInput(1);
		ultrasonicRight = new AnalogInput(2);
		
		gyro = new AnalogGyro(0);
		gyro.calibrate();
	}

	public static Telemetry getInstance() {
		if (telemetry == null) {
			telemetry = new Telemetry();
		}
		return telemetry;
	}

	//Just prints out values
	public void updateUltrasonicValue() {
		double rangeInInchesLeft = ultrasonicLeft.getVoltage() / vi;
		double rangeInInchesRight = ultrasonicRight.getVoltage() / vi;

		SmartDashboard.putString("DB/String 0", "L:" + rangeInInchesLeft);
		SmartDashboard.putString("DB/String 1", "R:" + rangeInInchesRight);
	}

	//Returns range in in. for left ultrasonic sensor
	public double getUltrasonicLeft() {
		return (ultrasonicLeft.getVoltage() / vi);
	}

	//Returns range in in. for left ultrasonic sensor
	public double getUltrasonicRight() {
		return (ultrasonicRight.getVoltage() / vi);
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
}