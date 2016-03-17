package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SemiAuto {
	private enum AutoState{
		READY,
		START,
		UP,
		CREST,
		DOWN,
		END
	};
	
	//Current state
	private static AutoState currentAuto;
	
	public static boolean isOnRamp; 
	
	//Input
//	public static AnalogInput ultrasonic = new AnalogInput(0); 
	
	//Variables
	public static Motor motor = Motor.getInstance();
	public static Controller cont = Controller.getInstance();
	public static double encStart = 0;
	public static double encEnd = 0;
	
	//Move arm down to 180
	public static boolean goDown(){
		if(currentAuto == AutoState.START){
			currentAuto = AutoState.DOWN;
		}
		return true;
	}
	
	//Move arm up to carrying
	public static boolean goUp(){
		if(currentAuto == AutoState.CREST){
			currentAuto = AutoState.UP;
		}
		return true;
	}
		
//	private static void updateAutoState(){
//		//get status of robot position
//		
//		if(wallDetected()){
//			goDown();
//		}
//		if(hasTraveled()){
//			goUp();
//		}	
//	}
	
	
	public static double getEnc(){
		return motor.getEncBR();
	}
	
	
	public static void autoLoop(){
				
		// Calculate outputs
		//TODO: isDown() and isUp() should be used to restrict speed during drive //??
		
		
		SmartDashboard.putString("DB/String 0", "BR: " + getEnc()); //motor.getEncBR()
		SmartDashboard.putString("DB/String 1", "W: " + Telemetry.getInstance().getUltrasonicRight());
		SmartDashboard.putString("DB/String 4", "ShootPos: " + Shooter.getPosition());
		SmartDashboard.putString("DB/String 9", "State: " + currentAuto);
		
		if(cont.getRightTrigger() == 1){
			currentAuto = AutoState.READY;
			//Shooter.setPosition(90);
		}	
		else if(currentAuto == AutoState.READY){
			if(wallDetected()){
				Motor.getInstance().resetDriveEncoders(); // resetting encoders to 0
				currentAuto = AutoState.START;
			}
			else{
				//do nothing
			}
		}
		else if(currentAuto == AutoState.START){
			isOnRamp = true;
			if(getEnc() >= 4200 && getEnc() < 6000){
				currentAuto = AutoState.DOWN;
			}
			else{
				//do nothing
			}
		}
		else if(currentAuto == AutoState.DOWN){
			if(isDown()){						//change to encoder value
				currentAuto = AutoState.CREST;
			}
			else{
				Shooter.setPosition(180);
			}
		}

		else if(currentAuto == AutoState.CREST){
<<<<<<< HEAD
			if(getEnc() - encStart == 6000){			//encoder value
=======
			if(getEnc() >= 8000){                  //check this value (needs tuning): Mavis thinks this is too early
>>>>>>> origin/Shooter
				currentAuto = AutoState.UP;
			}
			else{
				//do nothing
			}
		}
		else if(currentAuto == AutoState.UP){
			isOnRamp = false;
			if(isUp()){
				//do nothing: should be done by now
			}
			else{
				Shooter.setPosition(90);
			}
		}
	}
	
	public static boolean isUp(){
		return Math.abs(Shooter.getPosition() - 90) < 6.5;
	}
	
	public static boolean isDown(){
		return Math.abs(Shooter.getPosition() - 180) < 6.5;
	}
	
	public static boolean wallDetected(){
		return Telemetry.getInstance().getUltrasonicRight() > 7 && Telemetry.getInstance().getUltrasonicRight() < 22;
	}
	
	public static boolean hasTraveled(){
		return encEnd - encStart == 100;				   //CHANGE, ENC TRAVELED
	}
	
	public static void encValues(){ //-6000 = cleared bar
		SmartDashboard.putString("DB/String 0", "Enc: " + motor.getEncBL());
	}
	public static void testUS(){ //6 - 25
		SmartDashboard.putString("DB/String 1", "US: " + Telemetry.getInstance().getUltrasonicRight());    
	}
}
