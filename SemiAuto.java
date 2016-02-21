package org.usfirst.frc.team2473.robot;
import edu.wpi.first.wpilibj.AnalogInput;

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
	public static AnalogInput ultrasonic = new AnalogInput(0);                    //check
	
	//Variables
	public static Motor motor = Motor.getInstance();
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
		
	private static void updateAutoState(){
		//get status of robot position
		
		if(wallDetected()){
			goDown();
		}
		if(hasTraveled()){
			goUp();
		}	
	}
	
	public static void autoLoop(){
		// Get transitions + calculate state change if needed
		updateAutoState();
				
		// Calculate outputs
		if(currentAuto == AutoState.START){
			if(wallDetected()){
				currentAuto = AutoState.DOWN;
			}
			else{
				//do nothing
			}
		}
		else if(currentAuto == AutoState.DOWN){
			if(isDown()){
				currentAuto = AutoState.CREST;               						 // start is the same as end
			}
			else{
				encStart = motor.getEncBR();
				Shooter.setPosition(180);
			}
		}

		else if(currentAuto == AutoState.CREST){
			encEnd = motor.getEncBR();
			if(hasTraveled()){
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
		double vi = 5.0/512;
		return ultrasonic.getVoltage()/vi < 10;                                        //CHANGE, ULTRASONIC VAL
	}
	
	public static boolean hasTraveled(){
		return encEnd - encStart == 100;											   //CHANGE, ENC TRAVELED
	}
}
