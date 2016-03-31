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
    	//Setting log level to debug
    	Logger.getInstance().logLevel = LogLevel.Debug;

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
//    	Shooter.printValues();
    	myShooter.init();
    	myShooter.calibration();
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
//    	SmartDashboard.putString("DB/String 0", "BR:" + msot.getEncBR());
//    	SmartDashboard.putString("DB/String 1", "BL:" + mot.getEncBL());
//    	SmartDashboard.putString("DB/String 6", "Winch:" + mot.getEncWinch());
    	
    	Shooter.runLoop();
    	
//    	Shooter.printValues();
//    	Shooter.testPot();
    	
//    	SemiAuto.testUS();
//    	SemiAuto.autoLoop();
    	
//    	myShooter.testShooter();
    	
//    	Vision.getInstance().updateDashboard();
////    	if (!Shooter.inAuto){
//    	if(TeleOp.reverse) {
//    		TeleOp.runPowerReverse();
//    	}
//    	else {
//    		TeleOp.runPower();
//    	}
//    	}
    		
//    	TeleOp.testArm();
    	
//    	SmartDashboard.putString("DB/String 0",
//				"FR Volt: " + mot.frontRight.getOutputCurrent());
//    	SmartDashboard.putString("DB/String 1",
//				"FL Volt: " + mot.frontLeft.getOutputCurrent());
//    	SmartDashboard.putString("DB/String 4",
//				"BR Volt: " + mot.backRight.getOutputCurrent());
//    	SmartDashboard.putString("DB/String 8",
//				"BL Volt: " + mot.backLeft.getOutputCurrent());
    	
    	//Grappler
    	TeleOp.runUtilities();

    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
}