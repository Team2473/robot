package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Switches {

	DigitalInput dio1;
	DigitalInput dio2;
	DigitalInput dio3;
	DigitalInput dio4;
	DigitalInput dio5;
	DigitalInput dio6;
	DigitalInput dio7;
	DigitalInput dio8;

	private static Switches switches = null;

	private Switches() {
		dio1 = new DigitalInput(1);
		dio2 = new DigitalInput(2);
		dio3 = new DigitalInput(3);
		dio4 = new DigitalInput(4);
		dio5 = new DigitalInput(5);
		dio6 = new DigitalInput(6);
		dio7 = new DigitalInput(7);
		dio8 = new DigitalInput(8);
	}
    //Get an instance of a switch
	//Returns a switch object
	public static Switches getInstance() {
		if (switches == null) {
			switches = new Switches();
		}
		return switches;
	}
    //For testing: Prints out all of the switch values (boolean) to the dashboard
	public void printDigitalInputs() {
		SmartDashboard.putString("DB/String 1", "Switch 1:" + dio1.get());
		SmartDashboard.putString("DB/String 2", "Switch 2:" + dio2.get());
		SmartDashboard.putString("DB/String 3", "Switch 3:" + dio3.get());
		SmartDashboard.putString("DB/String 4", "Switch 4:" + dio4.get());
		SmartDashboard.putString("DB/String 5", "Switch 5:" + dio5.get());
		SmartDashboard.putString("DB/String 6", "Switch 6:" + dio6.get());
		SmartDashboard.putString("DB/String 7", "Switch 7:" + dio7.get());
		SmartDashboard.putString("DB/String 8", "Switch 8:" + dio8.get());
	}

	//returns number of switch on triple switch that is pressed
	//-1 means no switch is pressed
	//DIO: 1, 2, 3
	public int getTripleSwitch() {
		if (dio1.get() == false)
			return 1;
		else if (dio2.get() == false)
			return 2;
		else if (dio3.get() == false)
			return 3;
		else
			return -1;
	}

	//returns dial setting on dial with 0-3
	//DIO: 7, 8
	public int getFourDial() {
		if (dio7.get() == true && dio8.get() == true)
			return 0;
		else if (dio7.get() == false && dio8.get() == true)
			return 1;
		else if (dio7.get() == true && dio8.get() == false)
			return 2;
		else if (dio7.get() == false && dio8.get() == false)
			return 3;

		//this is just so that return is satisfied- it should never happen
		return -1;

	}

	//returns dial setting on dial with 0-7
	//DIO: 4, 5, 6
	public int getEightDial() {
		if (dio4.get() == true && dio5.get() == true && dio6.get() == true)
			return 0;
		else if (dio4.get() == false && dio5.get() == true && dio6.get() == true)
			return 1;
		else if (dio4.get() == true && dio5.get() == false && dio6.get() == true)
			return 2;
		else if (dio4.get() == false && dio5.get() == false && dio6.get() == true)
			return 3;
		else if (dio4.get() == true && dio5.get() == true && dio6.get() == false)
			return 4;
		else if (dio4.get() == false && dio5.get() == true && dio6.get() == false)
			return 5;
		else if (dio4.get() == true && dio5.get() == false && dio6.get() == false)
			return 6;
		else if (dio4.get() == false && dio5.get() == false && dio6.get() == false)
			return 7;
		
		//this is just so that return is satisfied- it should never happen
		return -1;

	}

}
