package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

public class Grappler {

	private static Grappler grappler = null;

	private Grappler() {
	}

	public static Grappler getInstance() {
		if (grappler == null) {
			grappler = new Grappler();
		}
		return grappler;

	}

	public void runScaleTower() {

		if (Controller.getInstance().getJoy2Button(1)) {

			Motor.getInstance().setLeftSideMotorsMode(Motor.MODE_POWER);
			Motor.getInstance().setRightSideMotorsMode(Motor.MODE_POWER);

			Motor.getInstance().moveLeftSideMotors(.25);
			Motor.getInstance().moveRightSideMotors(.25);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}

			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(-0.1);

			try {
				Thread.sleep(1100);
			} catch (InterruptedException e) {
			}

			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}

			for (int i = 0; i < 100; i++) {
				Motor.getInstance().moveGrapplerArmMotor(260);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
				}
			}

			Motor.getInstance().moveLeftSideMotors(.25);
			Motor.getInstance().moveRightSideMotors(.25);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);

			TeleOp.waiting = true;
		}

		if (Controller.getInstance().getJoy2Button(2)) {
			for (int i = 0; i < 75; i++) {
				Motor.getInstance().moveGrapplerArmMotor(0);
				if (i > 20) {
					Motor.getInstance().moveWinchMotors(8000);
				}
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
				}
			}

			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(0);

			while (true) {
				Motor.getInstance().moveWinchMotors(8000);
				Motor.getInstance().moveGrapplerArmMotor(-260);
			}
		}
	}
}
