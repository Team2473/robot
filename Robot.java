package org.usfirst.frc.team2473.robot;

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
    	
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
 
    	
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putString("DB/String 6", "XL; " + Controller.getInstance().getXL() );
    	SmartDashboard.putString("DB/String 7", "XR; " + Controller.getInstance().getXR() );
    	SmartDashboard.putString("DB/String 8", "YL; " + Controller.getInstance().getYL() );
    	SmartDashboard.putString("DB/String 9", "YR; " + Controller.getInstance().getYR() );
    	SmartDashboard.putString("DB/String 1", "b12 " + Controller.getInstance().getButton(12) );
    	SmartDashboard.putString("DB/String 2", "b11 " + Controller.getInstance().getButton(11) );
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
}
