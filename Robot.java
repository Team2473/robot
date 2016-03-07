package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	public void robotInit() {
		Vision.getInstance();
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	public void teleopInit() {
		Shooter.getInstance().calibration();
//		Diagnostic d = new Diagnostic();
//		d.testEverything();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Shooter.getInstance().runLoop();
		Vision.getInstance().updateDashboard();
		if (TeleOp.reverse) {
			TeleOp.runPowerReverse();
		} else {
			TeleOp.runPower();
		}
		TeleOp.runUtilities();

		// Grappler
		Grappler.getInstance().runScaleTower();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
