package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Switches {

	DigitalInput ioSwitch1;
	DigitalInput ioSwitch2;
	DigitalInput ioSwitch3;
	DigitalInput ioSwitch4;
	DigitalInput ioSwitch5;
	DigitalInput ioSwitch6;
	DigitalInput ioSwitch7;
	DigitalInput ioSwitch8;
	
	private static Switches switches = null;
	
	private Switches() {
		// switch 1 (on =false)
		ioSwitch1 = new DigitalInput(1);
		//switch2(on =false)
		ioSwitch2 = new DigitalInput(2);
		//switch3(on =false)
		ioSwitch3 = new DigitalInput(3);
	    //dial2
		ioSwitch4 = new DigitalInput(4);
		ioSwitch5 = new DigitalInput(5);
		//ioSwitch6 = new DigitalInput(6);
		// dial1
		ioSwitch7 = new DigitalInput(7);
		ioSwitch8 = new DigitalInput(8);
	}
	
	public static Switches getInstance() {
		if (switches == null) {
			switches = new Switches();
		}
		return switches;
	}

	public void printDigitalInputs() {
		SmartDashboard.putString("DB/String 1", "" + ioSwitch1.get());
		SmartDashboard.putString("DB/String 2", "" + ioSwitch2.get());
		SmartDashboard.putString("DB/String 3", "" + ioSwitch3.get());
		SmartDashboard.putString("DB/String 4", "" + ioSwitch4.get());
		SmartDashboard.putString("DB/String 5", "" + ioSwitch5.get());
		SmartDashboard.putString("DB/String 6", "" + ioSwitch6.get());
		SmartDashboard.putString("DB/String 7", "" + ioSwitch7.get());
		SmartDashboard.putString("DB/String 8", "" + ioSwitch8.get());
	}

	public int getSwitch1Value() {
		int total = 0;
		if (ioSwitch1.get()) {
			total += Math.pow(2, 0);
		}
		SmartDashboard.putString("DB/String 1", "" + ioSwitch1.get());
		return total;
	}

	public int getSwitch2Value() {
		int total = 0;
		if (ioSwitch2.get()) {
			total += Math.pow(2, 0);
		}
		SmartDashboard.putString("DB/String 2", "" + ioSwitch2.get());
		return total;
	}

	public int getSwitch3Value() {
		int total = 0;
		if (ioSwitch3.get()) {
			total += Math.pow(2, 0);

		}
		SmartDashboard.putString("DB/String 3", "" + ioSwitch3.get());
		return total;
	}

	public int getDial2Value() {
		int total = 0;
		if (ioSwitch4.get()) {
			total += Math.pow(2, 0);

		}
		if (ioSwitch5.get()) {
			total += Math.pow(2, 1);

		}
		if (ioSwitch6.get()) {
			total += Math.pow(2, 2);

		}
		SmartDashboard.putString("DB/String 4", "" + ioSwitch4.get());
		SmartDashboard.putString("DB/String 5", "" + ioSwitch5.get());
		SmartDashboard.putString("DB/String 6", "" + ioSwitch6.get());
		return total;
	}

	public int getDial1Value(int num) {
		int total = 0;
		if (ioSwitch7.get()) {
			total += Math.pow(2, 0);

		}
		if (ioSwitch8.get()) {
			total += Math.pow(2, 1);

		}
		SmartDashboard.putString("DB/String 7", "" + ioSwitch7.get());
		SmartDashboard.putString("DB/String 8", "" + ioSwitch8.get());
		return total;
	}
}
