package org.usfirst.frc.team2473.robot;

import org.usfirst.frc.team2473.robot.Logger.LogLevel;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	Shooter myShooter = new Shooter();
	
    public void robotInit() {

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
//    	AutoAttack.run();
    	Motor.getInstance().moveForwardEncoders(10000);
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    }
    
    /**
     * This function is called periodically during operator control
     */
    /*
     * Note to Rucha: Uncomment calibration method and mapTable method to test.
     * Ekta: ^^ mapTable doesn't exist anymore ^^
     * 
     */
    public void teleopPeriodic() {
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
}
