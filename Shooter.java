package org.usfirst.frc.team2473.robot;

public class Shooter {
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
	
}
