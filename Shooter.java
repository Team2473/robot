
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter {
	//private static AnalogPotentiometer pot = new AnalogPotentiometer(0); //channel 0;
	private static AnalogInput IR = new AnalogInput(1);
	private static boolean ballIn = false;
	
	public static void testIR(){	
		//SmartDashboard.putString("DB/String 0", "Potentiometer" + pot.get());	
		SmartDashboard.putString("DB/String 0", "IR: " + IR.getVoltage());
		SmartDashboard.putString("DB/String 1", "ballIn: " + ballIn);
		if(IR.getVoltage() < 2.3){
			ballIn = true;
		}
		else{
			ballIn = false;
		}
		//test
	}
	public static DigitalInput in = new DigitalInput(0);
	public static void testDigitalIO(){
		//true means nothing in the beam, false means something in the beam
		SmartDashboard.putString("DB/String 9", "Value: " + in.get());
	}
	
	//potentiometer
	/*
	 * To do: Create a table of values based on potentiometer readings.
	 * While motor is running, read pot values. For a certain value, give
	 * a certain amount of power to the motors. May or may not change with ball.
	 * Run motor on power mode. 
	 */
	public static CANTalon pot = new CANTalon(7);
	public static void testPotentiometer(){
		SmartDashboard.putString("DB/String 0", "Potentiometer " + pot.getAnalogInRaw());
		//returns value 1 - 1023
		//equivalent to voltage: 0.026 - 3.285
	}
	
	public void pickUp(){
//		moveShooterLever(0.5); //potentiometer value, ground level
//		//test value
//		while(IR.getVoltage() < 2.3){
//			spinShooter(0.5); //speed value
//		}
//		moveShooterLever(0.2); //carrying position
//	}
//	
//	public void spitOut(){
//		moveShooterLever(0.5); //ground level
//		spinShooter(-0.5);
//	}
//	
//	//reset after spitting out ball
//	public void reset(){
//		spinShooter(0); 
//		moveShooterLever(0); //up position
	} 
}
