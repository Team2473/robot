package org.usfirst.frc.team2473.robot;

public class SemiAuto {
	private enum AutoState{
		START,
		UP,
		CREST,
		DOWN,
		END
	};
	
	//Current state
	private static AutoState currentAuto = AutoState.START;
	
	//Input
//	public static AnalogInput ultrasonic = new AnalogInput(0); 
	
	//Variables
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
	
	public static void autoLoop(){
				
		// Calculate outputs
		//TODO: isDown() and isUp() should be used to restrict speed during drive
		if(currentAuto == AutoState.START){
			Shooter.getInstance().setPosition(90);
			encStart = Motor.getInstance().getEncFL();
			if(Motor.getInstance().getEncFL() == -75){ 															//change to encoder value
				currentAuto = AutoState.DOWN;
			}
			else{
				//do nothing
			}
		}
		else if(currentAuto == AutoState.DOWN){
			if(isDown()){																	//change to encoder value
				currentAuto = AutoState.CREST;
			}
			else{
				Shooter.getInstance().setPosition(180);
			}
		}

		else if(currentAuto == AutoState.CREST){
			if(Motor.getInstance().getEncFL() == -6000){																//encoder value
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
				Shooter.getInstance().setPosition(90);
			}
		}
	}
	
	public static boolean isUp(){
		return Math.abs(Shooter.getInstance().getPosition() - 90) < 5;
	}
	
	public static boolean isDown(){
		return Math.abs(Shooter.getInstance().getPosition() - 180) < 5;
	}
	
	public static boolean wallDetected(){
		return Telemetry.getInstance().getUltrasonicRight() > 6 && Telemetry.getInstance().getUltrasonicRight() < 26;                                        //CHANGE, ULTRASONIC VAL
	}
	
	public static boolean hasTraveled(){
		return encEnd - encStart == 100;											   //CHANGE, ENC TRAVELED
	}
}
