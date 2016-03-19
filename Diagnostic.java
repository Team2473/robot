package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

public class Diagnostic {
	public Diagnostic() {

	}

	public void testEverything() {
//		controllerTest();
		//switchesTest();
		driveTest();
		
		System.exit(0);

	}

	// testing the controller
	public boolean controllerTest() {
		SmartDashboard.putString("DB/String 1", "Press green button");
		while (!Controller.getInstance().getJoy1Button(1)) {
		}

		SmartDashboard.putString("DB/String 1", "Press red button");
		while (!Controller.getInstance().getJoy1Button(2)) {
		}

		SmartDashboard.putString("DB/String 1", "Press blue button");
		while (!Controller.getInstance().getJoy1Button(3)) {
		}

		SmartDashboard.putString("DB/String 1", "Press yellow button");
		while (!Controller.getInstance().getJoy1Button(4)) {
		}

		SmartDashboard.putString("DB/String 1", "Press left bumper");
		while (!Controller.getInstance().getJoy1Button(5)) {
		}
		SmartDashboard.putString("DB/String 1", "Press right bumper");
		while (!Controller.getInstance().getJoy1Button(6)) {
		}

		SmartDashboard.putString("DB/String 1", "Move left joystick up");

		while (Controller.getInstance().getYL() < 0.9) {
		}

		SmartDashboard.putString("DB/String 1", "Move right joystick up");

		while (Controller.getInstance().getYR() < 0.9) {
		}

		SmartDashboard.putString("DB/String 1", "Press left trigger");
		while (Controller.getInstance().getLeftTrigger() < 0.9) {
		}

		SmartDashboard.putString("DB/String 1", "Press arm button");
		while (!Controller.getInstance().getJoy2Button(1)) {
		}

		SmartDashboard.putString("DB/String 1", "Press winch button");
		while (!Controller.getInstance().getJoy2Button(2)) {
		}

		SmartDashboard.putString("DB/String 1", "Press foot pedal");
		while (!Controller.getInstance().getJoy2Button(3)) {
		}

		SmartDashboard.putString("DB/String 1", "All controllers working");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;

	}

	// Testing switches
	// Human interaction for testing switches:
	public boolean switchesTest() {
		SmartDashboard.putString("DB/String 1", "Turn fourDial to 0");
		while (!(Switches.getInstance().getFourDial() == 0)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn eightDial to 0");
		while (!(Switches.getInstance().getEightDial() == 0)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn all switches to off");
		while (!(Switches.getInstance().getTripleSwitch() == -1)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn the third triple switch on");
		while (!(Switches.getInstance().getTripleSwitch() == 3)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn second triple switch on");
		while (!(Switches.getInstance().getTripleSwitch() == 2)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn the first triple switch on");
		while (!(Switches.getInstance().getTripleSwitch() == 1)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn fourDial to 3");
		while (!(Switches.getInstance().getFourDial() == 3)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn fourDial to 2");
		while (!(Switches.getInstance().getFourDial() == 2)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn fourDial to 1");
		while (!(Switches.getInstance().getFourDial() == 1)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn eightDial to 1");
		while (!(Switches.getInstance().getEightDial() == 1)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn eightDial to 2");
		while (!(Switches.getInstance().getEightDial() == 2)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn eightDial to 3");
		while (!(Switches.getInstance().getEightDial() == 3)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn eightDial to 4");
		while (!(Switches.getInstance().getEightDial() == 4)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn eightDial to 5");
		while (!(Switches.getInstance().getEightDial() == 5)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn eightDial to 6");
		while (!(Switches.getInstance().getEightDial() == 6)) {
		}

		SmartDashboard.putString("DB/String 1", "Turn eightDial to 7");
		while (!(Switches.getInstance().getEightDial() == 7)) {
		}

		SmartDashboard.putString("DB/String 1", "All switches working");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// just for convenience
		return true;
	}

	// testing rightside/leftside motors
	public boolean driveTest() {
		SmartDashboard.putString("DB/String 1", "Testing drive motors");
		SmartDashboard.putString("DB/String 2", "Put robot on blocks and");
		SmartDashboard.putString("DB/String 3", "press the green button");
		while (!Controller.getInstance().getJoy1Button(1)) {
		}
		
		SmartDashboard.putString("DB/String 2", "");
		SmartDashboard.putString("DB/String 3", "");
		
		Motor.getInstance().setLeftSideMotorsMode(Motor.MODE_POWER);
		Motor.getInstance().setRightSideMotorsMode(Motor.MODE_POWER);
		
		Motor.getInstance().resetDriveEncoders();
		
		Motor.getInstance().frontLeft.set(-.2);
		Motor.getInstance().frontRight.set(.2);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Motor.getInstance().frontLeft.set(0);
		Motor.getInstance().frontRight.set(0);
		
		if(!(Motor.getInstance().getEncFL() > 1.0)){
			SmartDashboard.putString("DB/String 1", "Front left motor or");
			SmartDashboard.putString("DB/String 2", "encoder is broken");
			SmartDashboard.putString("DB/String 3", "");
			System.exit(0);
		}
		
		if(!(Motor.getInstance().getEncFR() < -1.0)){
			SmartDashboard.putString("DB/String 1", "Front right motor or");
			SmartDashboard.putString("DB/String 2", "encoder is broken");
			SmartDashboard.putString("DB/String 3", "");
			System.exit(0);
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Motor.getInstance().resetDriveEncoders();
		
		Motor.getInstance().backLeft.set(-.2);
		Motor.getInstance().backRight.set(.2);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!(Motor.getInstance().getEncFL() > 1.0)){
			SmartDashboard.putString("DB/String 1", "Back left motor or");
			SmartDashboard.putString("DB/String 2", "encoder is broken");
			SmartDashboard.putString("DB/String 3", "");
			System.exit(0);
		}
		
		if(!(Motor.getInstance().getEncFR() < -1.0)){
			SmartDashboard.putString("DB/String 1", "Back right motor or");
			SmartDashboard.putString("DB/String 2", "encoder is broken");
			SmartDashboard.putString("DB/String 3", "");
			System.exit(0);
		}
		
		Motor.getInstance().backLeft.set(0);
		Motor.getInstance().backRight.set(0);
		
		SmartDashboard.putString("DB/String 1", "Driver motors are");
		SmartDashboard.putString("DB/String 2", "working");
		SmartDashboard.putString("DB/String 3", "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	// testing breakbeam
	// Human interaction for testing breakbeam:
	public boolean breakBreamTest() {
		SmartDashboard.putString("DB/String 1", "Press green button to start BreakBeam test");
		while (!Controller.getInstance().getJoy1Button(1)) {}
		SmartDashboard.putString("DB/String 1", "Place hand in front ");
		SmartDashboard.putString("DB/String 2", "of breakbeam");
		while (Telemetry.getInstance().getBreakBeam()){}
		SmartDashboard.putString("DB/String 1", "Now move hand out of breakbeam");
		while (!Telemetry.getInstance().getBreakBeam()){}
		
		
		SmartDashboard.putString("DB/String 1", "Breakbeam tests completed");

		return true;
	}

	// testing grappler
	public boolean grapplerTest() {
		SmartDashboard.putString("DB/String 1", "Press green button to start grappler Test");
		while (!Controller.getInstance().getJoy1Button(1)) {}
		SmartDashboard.putString("DB/String 1", "The grappler arm is going to move pointing level");
		Motor.getInstance().moveGrapplerArmMotor(0);
		SmartDashboard.putString("DB/String 1", "Press green button if grappler arm is pointing level");
		while (!Controller.getInstance().getJoy1Button(1)) {}
		
		Motor.getInstance().moveGrapplerArmMotor(259);
		SmartDashboard.putString("DB/String 1", "Press green button if grappler arm is pointing up");
		while (!Controller.getInstance().getJoy1Button(1)) {}
		
		SmartDashboard.putString("DB/String 1", "Grappler Test Finished");
		
		return true;
	}

	// testing gyroscope
	// Human interaction for testing gryoscope:
	//not used
	/*public boolean gyroTest() {
		return true;
	}*/

	// testing ultrasonic
	public boolean ultrasonicTest() {
		int rangeError = 3;
		SmartDashboard.putString("DB/String 1", "Press green button to start ultrasonic test");
		while (!Controller.getInstance().getJoy1Button(1)) {}
		
		SmartDashboard.putString("DB/String 1", "Move about 2 feet in front of right ultrasonic sensor");
		while (Telemetry.getInstance().getUltrasonicRight() >= (24 - rangeError) && Telemetry.getInstance().getUltrasonicRight() <= (24 + rangeError)) {}
		
		SmartDashboard.putString("DB/String 1", "Move about 6 feet in front of right ultrasonic sensor");
		while (Telemetry.getInstance().getUltrasonicRight() >= (72 - rangeError) && Telemetry.getInstance().getUltrasonicRight() <= (72 + rangeError)) {}
		
		
		SmartDashboard.putString("DB/String 1", "Move about 12 feet in front of right ultrasonic sensor");
		while (Telemetry.getInstance().getUltrasonicRight() >= (144 - rangeError) && Telemetry.getInstance().getUltrasonicRight() <= (144 + rangeError)) {}
		
		SmartDashboard.putString("DB/String 1", "Ultrasonic test finished");
		return true;
	}

	// testing cameraFeed
	public void cameraTest() {
		SmartDashboard.putString("DB/String 1", "Press green button");
		SmartDashboard.putString("DB/String 2", "to start camera test");
		while (!Controller.getInstance().getJoy1Button(1)) {}
		
		Vision.getInstance().updateDashboard();
		SmartDashboard.putString("DB/String 1", "Now the front camera");
		SmartDashboard.putString("DB/String 2", "is going to show");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SmartDashboard.putString("DB/String 1", "Now the camera feed");
		SmartDashboard.putString("DB/String 2", "is going to reverse");
		TeleOp.reverse = true;
		Vision.getInstance().updateDashboard();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SmartDashboard.putString("DB/String 1", "Now the climbing");
		SmartDashboard.putString("DB/String 2", "camera is showing");
		TeleOp.reverse = false;
		Vision.getInstance().climbing = true;
		Vision.getInstance().updateDashboard();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SmartDashboard.putString("DB/String 1", "Camera Tests Done");
		SmartDashboard.putString("DB/String 2", "");
		
		
		
		
		
	}
}
