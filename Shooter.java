package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
<<<<<<< HEAD
	public void pickUp(){
		Motor.moveShooterLever(255); //temp value		
		while(ballLoaded() == false){
			Motor.spinShooter(0.5);
		}
		Motor.moveShooterLever(0); //return to default position
	}
	
	public void score(){
		Motor.moveShooterLever(255); //temp value
		spinShooter(-0.5);		
	}
	
	public void reset(){
		Motor.moveShooterLever(0); //default value
		spinShooter(0); 
	}
	//using lidar
	private boolean ballLoaded(){
		return false;
	}
	
	public void teleOp(){
		
	}
	
=======

	//private static AnalogPotentiometer pot = new AnalogPotentiometer(0); //channel 0;
	private static AnalogInput IR = new AnalogInput(0);
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
	
	public static void testPotentiometer(){
	//	SmartDashboard.putString("DB/String 0", "Potentiometer" + pot.get());	
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
>>>>>>> origin/Shooter
}
