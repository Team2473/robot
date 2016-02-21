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
		ioSwitch1 = new DigitalInput(1);
		ioSwitch2 = new DigitalInput(2);
		ioSwitch3 = new DigitalInput(3);
		ioSwitch4 = new DigitalInput(4);
		ioSwitch5 = new DigitalInput(5);
		ioSwitch6 = new DigitalInput(6);
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
		SmartDashboard.putString("DB/String 1", "Switch 1:" + ioSwitch1.get());
		SmartDashboard.putString("DB/String 2", "Switch 2:" + ioSwitch2.get());
		SmartDashboard.putString("DB/String 3", "Switch 3:" + ioSwitch3.get());
		SmartDashboard.putString("DB/String 4", "Switch 4:" + ioSwitch4.get());
		SmartDashboard.putString("DB/String 5", "Switch 5:" + ioSwitch5.get());
		SmartDashboard.putString("DB/String 6", "Switch 6:" + ioSwitch6.get());
		SmartDashboard.putString("DB/String 7", "Switch 7:" + ioSwitch7.get());
		SmartDashboard.putString("DB/String 8", "Switch 8:" + ioSwitch8.get());
	}
}
