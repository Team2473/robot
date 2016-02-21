package org.usfirst.frc.team2473.robot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SemiAuto {
	private enum AutoState{
		START,
		UP,
		CREST,
		DOWN,
		END
	};
	
	private static AutoState currentAuto = AutoState.START;
	
	//move arm up
	public static boolean goUp(){
		if(currentAuto == AutoState.START){
			currentAuto = AutoState.UP;
		}
		return true;
	}
	
	//move arm down to 180
	public static boolean goDown(){
		if(currentAuto == AutoState.CREST){
			currentAuto = AutoState.DOWN;
		}
		return true;
	}
		
	private static void updateAutoState(){
		//get status of robot position
		
		//if wall detected:
			goUp();
		//if distanceTravelled:
			goDown();
	}
	
	public static void autoLoop(){
		// Get transitions + calculate state change if needed
		updateAutoState();
				
		// Calculate outputs
		if(currentAuto == AutoState.UP){
			if(isUp()){
				currentAuto = AutoState.CREST;
			}
			else{
				Shooter.setPosition(120);
			}
		}
		else if(currentAuto == AutoState.DOWN){
			if(isDown()){
				currentAuto = AutoState.START;                // start is the same as end
			}
			else{
				Shooter.setPosition(180);
			}
		}
		else if(currentAuto == AutoState.START){
			
		}
	}
	
	public static boolean isUp(){
		return Math.abs(Shooter.getPosition() - 120) < 5;
	}
	
	public static boolean isDown(){
		return Math.abs(Shooter.getPosition() - 180) < 5;
	}
}
