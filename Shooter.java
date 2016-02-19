
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter {
	//private static AnalogPotentiometer pot = new AnalogPotentiometer(0); //channel 0;
	private static AnalogInput IR = new AnalogInput(1);
	private static boolean ballIn = false;
	public static CANTalon pot = new CANTalon(6);
	public static CANTalon shootR = new CANTalon(9);
	public static CANTalon shootL = new CANTalon(10);
	public static Joystick joy1 = new Joystick(0);
	
	public static int fwdPot = 1023; //failsafe
	public static int backPot = 851; //failsafe
	
	public static double delta;
	//make backwards array by flipping fwdTable or make 2D array
	public static double[] fwdTable = {0.28, 0.26, 0.23, 0.22, 0.21, 0.20, 0.19, 0.18, 0.17, 0.15, 0.12, 0.08, 0.08, 0.08, 0.08, 0.08, 0.08, 0.06, 0.06, 0.02};  
	
	public Shooter(){
		pot.enableBrakeMode(true);
		pot.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shootR.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shootL.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	}
	
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
//	public static DigitalInput in = new DigitalInput(0);
	public static void testDigitalIO(){
		//true means nothing in the beam, false means something in the beam
//		SmartDashboard.putString("DB/String 9", "Value: " + in.get());
	}
	
	//potentiometer
	/*
	 * To do: Create a table of values based on potentiometer readings.
	 * While motor is running, read pot values. For a certain value, give
	 * a certain amount of power to the motors. May or may not change with ball.
	 * Run motor on power mode. 
	 */
	public static void testPotentiometer(){
		SmartDashboard.putString("DB/String 0", "Potentiometer " + pot.getAnalogInRaw());
		//returns value 1 - 1023
		//equivalent to voltage: 0.026 - 3.285
	}
	
	
	public static void checkLS(){
		//talon 6 = TALON for arm + limit switches
		pot.ConfigFwdLimitSwitchNormallyOpen(false);
		pot.ConfigRevLimitSwitchNormallyOpen(false);
		
		
		SmartDashboard.putString("DB/String 3", "Fwd: " + pot.isFwdLimitSwitchClosed());
		SmartDashboard.putString("DB/String 4", "Rev: " + pot.isRevLimitSwitchClosed());
	}
	
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
	
	
	//DON'T DELETE THIS YET: CONVERSION TOOL:
	
//	public static void moveWithJoy(){ // finding index value of table (bucket) given an AnalogInRaw value
//		if(joy1.getRawButton(3)){
////			moveForward();
//			int pos = pot.getAnalogInRaw();
//			int one = backPot;
//			boolean found = false;
//			int n = 1;
//			while(!found && joy1.getRawButton(3)){
//				SmartDashboard.putString("DB/String 1", "n: " + n);
//				while((pos >= one) && (pos < backPot + n*(int)(delta)) && joy1.getRawButton(3)){ //if between two ranges, finds arr value
//					pot.set(fwdTable[n-1]);
//				}
//				one = (int)(backPot + n*delta);
//				n++;
//			}
//		}
//		else if(joy1.getRawButton(2)){
////			moveBackward();
//			
//		}
//		else{
//			stop();
//		}
//	}
		
	
	
	public static void calibration(){
		while(pot.isFwdLimitSwitchClosed()){ //when forward is not pressed
			moveForward();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fwdPot = pot.getAnalogInRaw();
		while(pot.isRevLimitSwitchClosed()){ //when backward is not pressed
			moveBackward();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backPot = pot.getAnalogInRaw();
		delta = (fwdPot - backPot) / 20.0;
		
		SmartDashboard.putString("DB/String 5", "fwdPot: " + fwdPot);
		SmartDashboard.putString("DB/String 6", "backPot: " + backPot);
		SmartDashboard.putString("DB/String 7", "delta: " + delta);
	}
	
	
	//100 means that it goes ALL THE WAY forward or ALL THE WAY backward
	public static void mapFwdTable(int n){
		int count = 0;
		for(double i = backPot + delta; i < fwdPot; i += delta){
			SmartDashboard.putString("DB/String 1", "count: " + count);
			SmartDashboard.putString("DB/String 2", "pot: " + pot.getAnalogInRaw());
			SmartDashboard.putString("DB/String 8", "pwr: " + fwdTable[count]);
			SmartDashboard.putString("DB/String 9", "i: " + i);
			
			while(pot.getAnalogInRaw() < i && count < fwdTable.length){
				if(!joy1.getRawButton(n) && n!= 100){
					stop();
					break;
				}
				pot.set(fwdTable[count]);
			}
			count++;
		}
		
	}
	
	public static void mapBackTable(int n){	
		int count2 = 0;
		for(double i = fwdPot - delta; i > backPot; i -= delta){
			SmartDashboard.putString("DB/String 1", "count: " + count2);
			SmartDashboard.putString("DB/String 2", "pot: " + pot.getAnalogInRaw());
			SmartDashboard.putString("DB/String 8", "pwr: " + fwdTable[count2]);
			SmartDashboard.putString("DB/String 9", "i: " + i);
			
			while(pot.getAnalogInRaw() > i && count2 < fwdTable.length){
				if(!joy1.getRawButton(n) && n!=100){
					stop();
					break;
				}
				pot.set(-fwdTable[count2]);
			}
			count2++;
		}
		
	}
	
	//works with joystick, but if below 45 degrees, falls on its own due to weight
	public static void joyControlled(){
		if(joy1.getRawButton(3)){
			mapFwdTable(3);
		}
		else if(joy1.getRawButton(2)){
			mapBackTable(2);
		}
		else{
			stop();
		}
	}
	
	
	
	public static DigitalInput spin = new DigitalInput(0); 
	
	public static void load(){
		
		
		if(joy1.getRawButton(4)){
			mapFwdTable(4);
		}
		while(spin.get()){ 							//beam is whole; no break; spins in  TRUE --> WHOLE BEAM
			shootR.set(-0.2);
			shootL.set(0.2);
		}
			shootR.set(0);
			shootL.set(0);
			for(int i = 0; i < 100; i++){
				i++;
			}
		if(!joy1.getRawButton(4)){ 				//button release
			if(spin.get()){
				mapBackTable(100);
			}
			else{
				//move to have ball position
			}
		}
	}
	
	
	public static void unload(){
		if(joy1.getRawButton(5)){						//bring arm fwd
			mapFwdTable(5);
		}
		while(!spin.get()){								//break beam is broken, unload
			shootR.set(0.2);
			shootL.set(-0.2);
		}							
			mapBackTable(100);
		if(!joy1.getRawButton(5)){
			mapBackTable(100);
		}
	}
	
	
	
//	public void pickUp(){
//		mapFwdTable(); //potentiometer value, ground level
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
//	} 
}
