
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
	
	// Constants
	public static int fwdPotMax  = 505; 
	public static int backPotMax = 420;
	
	// Joystick Mapping
	public static int loadButton   = 4;
	public static int unloadButton = 5;
	
	// Input
	public static DigitalInput breakBeam   = new DigitalInput(0); 
	public static CANTalon pot             = new CANTalon(6);
	public static CANTalon shootR          = new CANTalon(9);
	public static CANTalon shootL          = new CANTalon(10);
	public static Joystick joystick        = new Joystick(0);
		
	// Initialization

	public Shooter() {
		pot.enableBrakeMode(true);
		pot.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shootR.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shootL.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	}
	
	// Basic power instructions
	
	public static void moveForward(){
		pot.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		pot.set(0.15);
	}
	
	public static void moveBackward(){ //365 pot
		pot.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		pot.set(-0.15);
	}
	
	public static void stop(){
		pot.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		pot.set(0);
	}
	
	// Calibration: Finding max pot values
	
	public static void calibration(){
		while(pot.isFwdLimitSwitchClosed()){ //when forward is not pressed
			moveForward();
		}
		try {
			Thread.sleep(1000); 														//CHANGE
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fwdPotMax = pot.getAnalogInRaw();
		while(pot.isRevLimitSwitchClosed()){ //when backward is not pressed
			moveBackward();
		}
		try {
			Thread.sleep(1000); 														//CHANGE
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backPotMax = pot.getAnalogInRaw();
	}

	// Loading and Unloading
	
	public static void load(){
		
		SmartDashboard.putString("DB/String 4", "breakBeam.get: " + breakBeam.get());
		
		if(joystick.getRawButton(loadButton)) {
			
			// Move arm all the way forward
			setPosition(180);
			
			// Start spinning shooter
			shootR.set(-0.2);
			shootL.set(0.2);
			
			// Wait for beam to break
			while(breakBeam.get()) { 			
				// Failsafe: Button released without ball in place
				if(!joystick.getRawButton(loadButton)) {
					setPosition(0);
					return; 
				}
			}
			
			// Stop spinning shooters
			shootR.set(0);
			shootL.set(0);
			
			// Move into ball holding position
			setPosition(90);
		}
	}
	
	public static void unload() {
		
		if(joystick.getRawButton(unloadButton)) {
			
			// Move arm forward
			setPosition(180);
			
			// Waiting for ball to be unloaded
			while(!breakBeam.get()){										
				shootR.set(0.2);
				shootL.set(-0.2);
			}	
			
			// Reset arm
			setPosition(0);
		}
	}	
 
	// Motor Control 
	// TODO: This should be using the motor class
	
	public static double[] lookupTable = {0.28, 0.26, 0.23, 0.22, 0.21, 0.20, 0.19, 0.18, 0.17, 0.17, 0.16, 0.15, 0.12, 0.08, 0.08, 0.08, 0.08, 0.06, 0.06, 0.02};  

	public static void setPosition(int degrees) {
	
		int index = 0;
		int direction = 1; 
		double desiredPosition = (degrees*fwdPotMax)/180;
		
		// NOTE: There should be a buffer around desiredPosition
		while(pot.getAnalogInRaw() != desiredPosition) {
		
			// We will use direction as a multiplier, this will change the direction of setpot
			if(pot.getAnalogInRaw() > desiredPosition) direction = -1;
						
			// Determine ideal motor speed & set power to motor
			index = degrees/lookupTable.length; 
			pot.set(lookupTable[index]*direction);
			
			// TODO: Prevent motor from going outside of safe zone
			
		}
		
		// Stop the motor
		pot.set(0);
	}
}


