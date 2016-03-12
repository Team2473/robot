package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
	private enum State {
		COLLAPSED, EXTENDING, EXTENDED, COLLAPSING, RAISING, RAISED, LOWERING, FIRING
	};
	Telemetry myTelemetry;
	
	private String stateString(State state) {
		switch (state) {
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

	private State currentState = State.COLLAPSED;

	private static Shooter shooter = null;

	// Initialization
	private Shooter() {
		myTelemetry = Telemetry.getInstance();
		pot.enableBrakeMode(true);
		pot.changeControlMode(Motor.MODE_POWER);
		shootR.changeControlMode(Motor.MODE_POWER);
		shootL.changeControlMode(Motor.MODE_POWER);
	}

	public static Shooter getInstance() {
		if (shooter == null) {
			shooter = new Shooter();
		}
		return shooter;

	}

	// Constants
	public int fwdPotMax = 505;
	public int backPotMax = 420;

	// Joystick Mapping
	public int loadButton = 4;
	public int unloadButton = 5;
	public boolean abortShoot = false;

	// Input
	public CANTalon pot = new CANTalon(6);
	public CANTalon shootR = new CANTalon(9);
	public CANTalon shootL = new CANTalon(10);

	// Pot reading for positioning
	public void testPot() {
		SmartDashboard.putString("DB/String 1", "" + pot.getAnalogInRaw());
		if (Controller.getInstance().getJoy1Button(2)) {
			moveBackward();
		} else if (Controller.getInstance().getJoy1Button(3)) {
			moveForward();
		} else {
			stop();
		}
	}

	// Basic power instructions

	public void moveForward() {
		pot.set(0.15);
	}

	public void moveBackward() { // 365 pot
		pot.set(-0.15);
	}

	public void stop() {
		pot.set(0);
	}

	// Calibration: Finding max pot values

	public void calibration() {
		while (pot.isFwdLimitSwitchClosed()) { // when forward is not pressed
			moveForward();
		}
		try {
			Thread.sleep(1000); // CHANGE
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fwdPotMax = pot.getAnalogInRaw();
		while (pot.isRevLimitSwitchClosed()) { // when backward is not pressed
			moveBackward();
		}
		try {
			Thread.sleep(1000); // CHANGE
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backPotMax = pot.getAnalogInRaw();
	}

	// Test method
	public void test() {
		setPosition(90);
		setPosition(0);
	}

	// Loading and Unloading
	public boolean fire() {
		if (currentState == State.RAISED || currentState == State.RAISING) {
			currentState = State.LOWERING;
		}
		return true;
	}

	// Get to extend state
	public boolean extend() {
		if (currentState == State.COLLAPSED || currentState == State.COLLAPSING) {
			currentState = State.EXTENDING;
		}
		return true;
	}

	public boolean collapse() {
		if (currentState == State.EXTENDED || currentState == State.EXTENDING) {
			currentState = State.COLLAPSING;
		}
		return true;
	}

	private boolean hasBall() {
		return !myTelemetry.getBreakBeam();
	}

	private void intakeBall() {
		shootR.set(-0.2);
		shootL.set(0.2);
	}

	private void fireBall() {
		shootR.set(0.6);
		shootL.set(-0.6);
		// Need to allow the ball to be fired
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void holdBall() {
		shootR.set(0);
		shootL.set(0);
	}

	private void updateControlState() {
		// get status of extend button
		if (Controller.getInstance().getJoy1Button(5)) {
			extend();
		} else {
			collapse();
		}
		// get status of fire button
		if (Controller.getInstance().getLeftTrigger() == 1) {
			fire();
		}

		// safety abort
		if (Controller.getInstance().getJoy1Button(2)) {
			if (currentState == State.LOWERING)
				abortShoot = true;
		}

	}

	public void runLoop() {
		// Get transitions + calculate state change if needed
		updateControlState();

		// Calculate outputs
		if (currentState == State.COLLAPSING) {
			holdBall();
			if (isCollapsed()) {
				currentState = State.COLLAPSED;
			} else {
				setPosition(0);
			}
		} else if (currentState == State.EXTENDING) {
			if (isExtended()) {
				currentState = State.EXTENDED;
			} else {
				setPosition(180);
			}
		} else if (currentState == State.EXTENDED) {
			SmartDashboard.putString("DB/String 3", "State Extended");
			if (hasBall()) {
				currentState = State.RAISING;
			} else {
				SmartDashboard.putString("DB/String 8", "Spinning Wheels");
				intakeBall();
			}
		} else if (currentState == State.RAISING) {
			if (isRaised()) {
				currentState = State.RAISED;
			} else {
				holdBall();
				setPosition(90);
			}
		} else if (currentState == State.RAISED) {
			pot.set(0); // This is temporary, need to fine tune table values
		}
		// firing, abort shoot
		else if (currentState == State.LOWERING) {
			if (isExtended() || abortShoot) {
				currentState = State.FIRING;
			} else {
				setPosition(180);
			}
		} else if (currentState == State.FIRING) {
			if (!hasBall()) {
				currentState = State.EXTENDED;
			} else {
				fireBall();
				abortShoot = false;
			}
		}
	}

	public boolean isCollapsed() {
		return (Math.abs(pot.getAnalogInRaw() - backPotMax) < 5);
	}

	public boolean isExtended() {
		return Math.abs(pot.getAnalogInRaw() - fwdPotMax) < 5;
	}

	public boolean isRaised() {
		return getPosition() <= 90;
	}

	// DO NOT USE. Saving for use in test program.
	public void load() {
		boolean continueMethod = true;
		boolean atNinety = false;
		SmartDashboard.putString("DB/String 4", "breakBeam.get: "
				+ myTelemetry.getBreakBeam());

		if (Controller.getInstance().getJoy1Button(loadButton)) {

			if (!myTelemetry.getBreakBeam()) {
				// Stop spinning shooters
				shootR.set(0);
				shootL.set(0);

				// Move into ball holding position
				if (continueMethod) {
					SmartDashboard.putString("DB/String 0", "got here");
					setPosition(90);
					shootR.set(0);
					shootL.set(0);
					atNinety = true;
				}
			}
			// Move arm all the way forward
			if (!atNinety) { // if it's not at 90
				setPosition(180);
			}

			// Wait for beam to break
			while (myTelemetry.getBreakBeam()) {

				// Start spinning shooter
				shootR.set(-0.2);
				shootL.set(0.2);

				// Failsafe: Button released without ball in place
				if (!Controller.getInstance().getJoy1Button(loadButton)
						&& !atNinety) {
					shootR.set(0);
					shootL.set(0);
					setPosition(0);
					continueMethod = false;
					atNinety = false;
					if (Math.abs(pot.getAnalogInRaw() - backPotMax) < 5) {
						continueMethod = false;
						break;
					}
				}
			}
		}
	}

	public void unload() {

		if (Controller.getInstance().getJoy1Button(unloadButton)) {

			// Move arm forward
			setPosition(180);

			// Waiting for ball to be unloaded
			while (!myTelemetry.getBreakBeam()) {
				shootR.set(0.2);
				shootL.set(-0.2);
			}

			// Reset arm
			setPosition(0);
		}
	}

	// Motor Control
	// TODO: This should be using the motor class

	public double[] lookupTable = { 0.34, 0.32, 0.32, 0.30, 0.28, 0.26, 0.26,
			0.26, 0.24, 0.24, 0.22, 0.22, 0.20, 0.19, 0.16, 0.14, 0.14, 0.12,
			0.10, 0.02 };

	public double getPosition() {
		double diff = fwdPotMax - backPotMax;
		return (pot.getAnalogInRaw() - backPotMax) * 180 / diff;
	}

	public void setPosition(int degrees) {

		int index = 0;
		int direction = 1;
		double diff = fwdPotMax - backPotMax;
		double desiredPosition = (degrees * diff) / 180 + backPotMax;

		// if already at currentPosition, do nothing
		if (Math.abs(pot.getAnalogInRaw() - desiredPosition) < 5) {
			return;
		}
		// We will use direction as a multiplier, this will change the direction
		// of setpot
		if (pot.getAnalogInRaw() > desiredPosition)
			direction = -1;

		// Determine ideal motor speed & set power to motor
		double toDegrees = (pot.getAnalogInRaw() - backPotMax) * 180 / diff;
		// 9 is 180/20 (because 20 buckets)
		index = (int) toDegrees / 9;
		if (direction == -1) {
			index = Math.abs(index - 20);
		}

		// Prevent motor from going outside safe zone
		if (index > 19 || index < 0) {
			return;
		}

		pot.set(lookupTable[index] * direction);
	}
}
