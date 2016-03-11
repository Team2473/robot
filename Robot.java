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
//    	Vision.getInstance().visionInit();
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
//    	SmartDashboard.putString("DB/String 4", "Running AutoAttack");
    	AutoAttack.run();
//    	Telemetry.getInstance().updateGyroValue();
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
//    	Shooter.calibration();
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
//    	Shooter.testPotentiometer();
//    	Shooter.checkLS();
//    	Shooter.joyControlled(); 
    	
//    	Shooter.load();
//    	Shooter.setPosition(0);
    	
//    	Shooter.unload();
//    	Shooter.test();
//    	if(Vision.reverse){
//    		TeleOp.runPowerReverse();
//    	}else{
//    		TeleOp.runPower();
//    	}
//    	Motor.getInstance().moveForwardPowerPrintEncoders(.3);
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
}
