package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

// Inserting some junk text
public class Robot extends IterativeRobot {
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	EncoderDrive encDrive;
	TCPSocket socket;
	TankDrive test;
	
    public void robotInit() {
        //encDrive = new EncoderDrive();
    	socket = new TCPSocket();
    	//test = new TankDrive();
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
    	//encDrive.teleopInit();
    	socket.teleopInit();
    	//test.teleopInit();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//test.teleop();
    	//encDrive.teleop();
    	socket.teleop();
    	//SmartDashboard.putString("DB/String 8", "r " + Math.random());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
}
