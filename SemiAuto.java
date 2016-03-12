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
		SmartDashboard.putString("DB/String 0", "BL: " + motor.getEncBL());
		SmartDashboard.putString("DB/String 1", "BR: " + motor.getEncBR());
		SmartDashboard.putString("DB/String 4", "Ultras: " + Telemetry.getInstance().getUltrasonicRight());

		return motor.getEncBR();
	}
	
	public static void autoLoop(){
				
		// Calculate outputs
		//TODO: isDown() and isUp() should be used to restrict speed during drive //you liar
		
		SmartDashboard.putString("DB/String 9", "State: " + currentAuto);
		
		//ADD A STATE: press button: put in ready state
		//			   ultrasonic:   put in start state
		
		if(cont.getRightTrigger() == 1){
			currentAuto = AutoState.READY;
		}		
		else if(currentAuto == AutoState.READY){
			if(wallDetected()){
				currentAuto = AutoState.START;
			}
			else{
				//do nothing
			}
		}
		else if(currentAuto == AutoState.START){
			Shooter.setPosition(90); //already up at start, why is this here?
			encStart = getEnc();
			if(getEnc() - encStart == 4606){
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
			if(getEnc() - encStart == 6000){			//encoder value
				currentAuto = AutoState.UP;
			}
			else{
				//do nothing
			}
		}
		else if(currentAuto == AutoState.UP){
			if(isUp()){
				currentAuto = AutoState.START;
			}
			else{
				Shooter.setPosition(90);
			}
		}
	}
	
	public static boolean isUp(){
		return Math.abs(Shooter.getPosition() - 90) < 5;
	}
	
	public static boolean isDown(){
		return Math.abs(Shooter.getPosition() - 180) < 5;
	}
	
	public static boolean wallDetected(){
		return Telemetry.getInstance().getUltrasonicRight() > 6 && Telemetry.getInstance().getUltrasonicRight() < 26;                                        //CHANGE, ULTRASONIC VAL
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
