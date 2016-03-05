package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TalonTest {
		private static TalonTest talonTest = null;
		private boolean createTalon = true;
		CANTalon talon;
		Joystick joy = new Joystick(0);

		private TalonTest() {
		}
	    //Gets a new instance of the grappler object (returns grappler object)
		public static TalonTest getInstance() {
			if (talonTest == null) {
				talonTest = new TalonTest();
			}
			return talonTest;

		}
	     
		public void testTalon(int talonID) {
	        if(createTalon) {
	        	talon = new CANTalon(talonID);
	        	createTalon = false;
	        }
	        
	        talon.set(joy.getY());
	        
//	        if (talon.isFwdLimitSwitchClosed()) {
//	        	SmartDashboard.putString("DB/String 0", "ForwardLimitSwitchClosed: ");
//	        }
//	        else {
//	        	SmartDashboard.putString("DB/String 0", "ForwardLimitSwitchOpen: ");
//	        }
//	        
//	        if (talon.isRevLimitSwitchClosed()) {
//	        	SmartDashboard.putString("DB/String 1", "ReverseLimitSwitchClosed: ");
//	        }
//	        else {
//	        	SmartDashboard.putString("DB/String 1", "ReverseLimitSwitchOpen: ");
//	        }
	        
	        SmartDashboard.putString("DB/String 0", "" + talon.isFwdLimitSwitchClosed());
	        SmartDashboard.putString("DB/String 1", "" + talon.isRevLimitSwitchClosed());
	}
}
