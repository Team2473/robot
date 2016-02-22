
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
	private enum State{
		COLLAPSED,
		EXTENDING,
		EXTENDED,
		COLLAPSING,
		RAISING,
		RAISED,
		LOWERING,
		FIRING
	};
	
	
	private static String stateString(State state){
		switch(state){
		case COLLAPSED:
			return "Collapsed";
		case EXTENDING:
			return "Extending";
		case COLLAPSING:
			return "Collapsing";
		case EXTENDED:
			return "Extended";
		case RAISING:
			return "Raising";
		case RAISED:
			return "Raised";
		case LOWERING:
			return "Lowering";
		case FIRING:
			return "Firing";
		}
		
		return "Unknown";
	}
	
	private static State currentState = State.COLLAPSED;
	
	// Constants
	public static int fwdPotMax  = 505; 
	public static int backPotMax = 420;
	
	// Joystick Mapping
	public static int loadButton     = 4;
	public static int unloadButton   = 5;
	public static boolean abortShoot = false;
	
	// Input
	public static DigitalInput breakBeam   = new DigitalInput(0); 
	public static CANTalon pot             = new CANTalon(6);
	public static CANTalon shootR          = new CANTalon(9);
	public static CANTalon shootL          = new CANTalon(10);
		
	// Initialization

	public static void init() {
		pot.enableBrakeMode(true);
		pot.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shootR.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shootL.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	}
	
	//Pot reading for positioning
	public static void testPot(){
		SmartDashboard.putString("DB/String 1", "" + pot.getAnalogInRaw());	
		if(Controller.getInstance().getJoy1Button(2)){
			moveBackward();
		}
		else if(Controller.getInstance().getJoy1Button(3)){
			moveForward();
		}
		else{
			stop();
		}
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
	
	// Test method
	public static void test(){
		setPosition(90);
		setPosition(0);
	}

	
	
	// Loading and Unloading
	public static boolean fire(){
		if(currentState == State.RAISED || currentState == State.RAISING){
			currentState = State.LOWERING;
		}
		return true;
	}
	
	//Get to extend state
	public static boolean extend(){
		if(currentState == State.COLLAPSED || currentState == State.COLLAPSING){
			currentState = State.EXTENDING;
		}
		return true;
	}
	
	public static boolean collapse(){
		if(currentState == State.EXTENDED || currentState == State.EXTENDING){
			currentState = State.COLLAPSING;
		}
		return true;
	}
	
	private static boolean hasBall(){
		return !breakBeam.get();
	}
	
	private static void intakeBall(){
		shootR.set(-0.2);
		shootL.set(0.2);
	}
	
	private static void fireBall(){
		shootR.set(0.6);
		shootL.set(-0.6);
		//Need to allow the ball to be fired
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void holdBall(){
		shootR.set(0);
		shootL.set(0);
	}
	
	private static void updateControlState(){
		//get status of extend button
		if(Controller.getInstance().getJoy1Button(5)){
			extend();
		}
		else{
			collapse();
		}
		//get status of fire button
		if(Controller.getInstance().getLeftTrigger() == 1){
			fire();
		}
		
		//safety abort
		if(Controller.getInstance().getJoy1Button(2)){
			if(currentState == State.LOWERING) abortShoot = true;
		}
		
	}
	
	public static void runLoop(){
		// Get transitions + calculate state change if needed
		updateControlState();
		
		// Calculate outputs
		if(currentState == State.COLLAPSING){
			holdBall();
			if(isCollapsed()){
				currentState = State.COLLAPSED;
			}
			else{
				setPosition(0);
			}
		}
		else if(currentState == State.EXTENDING){
			if(isExtended()){
				currentState = State.EXTENDED;
			}
			else{
				setPosition(180);
			}
		}
		else if(currentState == State.EXTENDED){
			if(hasBall()){
				currentState = State.RAISING;
			}
			else{
				intakeBall();
			}
		}
		else if(currentState == State.RAISING){
			if(isRaised()){
				currentState = State.RAISED;
			}
			else{
				holdBall();
				setPosition(90);
			}
		}
		else if(currentState == State.RAISED){
			pot.set(0); //This is temporary, need to fine tune table values
		}
		//firing, abort shoot
		else if(currentState == State.LOWERING){
			if(isExtended() || abortShoot){ 
				currentState = State.FIRING;
			}
			else{
				setPosition(180);
			}
		}
		else if(currentState == State.FIRING){
			if(!hasBall()){
				currentState = State.EXTENDED;
			}
			else{
				fireBall();
				abortShoot = false;
			}
		}
	}
	
	public static boolean isCollapsed(){
		return (Math.abs(pot.getAnalogInRaw() - backPotMax) < 5);
	}
	
	public static boolean isExtended(){
		return Math.abs(pot.getAnalogInRaw() - fwdPotMax) < 5;
	}
	
	public static boolean isRaised(){
		return getPosition() <= 90; 
	}
	
		
	
	//DO NOT USE. Saving for use in test program.
	public static void load(){
		boolean continueMethod = true;
		boolean atNinety = false;
		SmartDashboard.putString("DB/String 4", "breakBeam.get: " + breakBeam.get());
		
		if(Controller.getInstance().getJoy1Button(loadButton)) {
			
			if(!breakBeam.get()){
				// Stop spinning shooters
				shootR.set(0);
				shootL.set(0);
				
				// Move into ball holding position
				if(continueMethod){
					SmartDashboard.putString("DB/String 0", "got here");
					setPosition(90);
					shootR.set(0);
					shootL.set(0);
					atNinety = true;
				}
			}
			// Move arm all the way forward
			if(!atNinety){ //if it's not at 90
				setPosition(180);
			}
						
			// Wait for beam to break
			while(breakBeam.get()) {
				
				// Start spinning shooter
				shootR.set(-0.2);
				shootL.set(0.2); 
				
				// Failsafe: Button released without ball in place
				if(!Controller.getInstance().getJoy1Button(loadButton) && !atNinety) {
					shootR.set(0);
					shootL.set(0);
					setPosition(0);
					continueMethod = false;
					atNinety = false;
					if(Math.abs(pot.getAnalogInRaw() - backPotMax) < 5){
						continueMethod = false;
						break;
					}
				}
			}						
		}
	}
	
	public static void unload() {
		
		if(Controller.getInstance().getJoy1Button(unloadButton)) {
			
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
	//
	public static double[] lookupTable = {0.34, 0.32, 0.32, 0.30, 0.28, 0.26, 0.26, 0.26, 0.24, 0.24, 0.22, 0.22, 0.20, 0.19, 0.16, 0.14, 0.14, 0.12, 0.10, 0.02};  

	public static double getPosition(){
		double diff = fwdPotMax - backPotMax;
		return (pot.getAnalogInRaw() - backPotMax) * 180 / diff;
	}
	
	public static void setPosition(int degrees) {
	
		int index = 0;
		int direction = 1; 
		double diff = fwdPotMax - backPotMax;
		double desiredPosition = (degrees*diff)/180 + backPotMax;
		
		//if already at currentPosition, do nothing
		if(Math.abs(pot.getAnalogInRaw() - desiredPosition) < 5){
			return;
		}
		// We will use direction as a multiplier, this will change the direction of setpot
		if(pot.getAnalogInRaw() > desiredPosition) direction = -1;
					
		// Determine ideal motor speed & set power to motor
		double toDegrees = (pot.getAnalogInRaw() - backPotMax) * 180 / diff;
		//9 is 180/20 (because 20 buckets)
		index = (int) toDegrees/9;
		if(direction == -1){
			index = Math.abs(index- 20);
		}
		
		// Prevent motor from going outside safe zone
		if(index > 19 || index < 0){
			return;
		}

		pot.set(lookupTable[index]*direction);
	}
}


