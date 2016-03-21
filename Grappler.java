package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grappler {

	private static Grappler grappler = null;

	private Grappler() {
		Motor.getInstance();
	}
    //Gets a new instance of the grappler object (returns grappler object)
	public static Grappler getInstance() {
		if (grappler == null) {
			grappler = new Grappler();
		}
		return grappler;

	}
     
	//Autonomous code for scaling the robot up the tower
	public void runScaleTower() {
        //Pressing button 1
		
		if (Controller.getInstance().getJoy2Button(1)) {
            
			Motor.getInstance().setLeftSideMotorsMode(Motor.MODE_POWER);
			Motor.getInstance().setRightSideMotorsMode(Motor.MODE_POWER);
            //run at 25% of power
			Motor.getInstance().moveLeftSideMotors(.27);
			Motor.getInstance().moveRightSideMotors(.25);

			try {
				//Pause for 3 seconds
				Thread.sleep(2500);
			} catch (InterruptedException e) {
			}

			Motor.getInstance().resetDriveEncoders();
			Motor.getInstance().moveLeftSideMotors(-.1);
			Motor.getInstance().moveRightSideMotors(-.1);
			
			//(18 Mar 2016: increased encoder value from 1000
			while(Motor.getInstance().getEncFL() > -1500 && Motor.getInstance().getEncFR() < 1500){
				if(Motor.getInstance().getEncFL() < -1500){
					Motor.getInstance().moveLeftSideMotors(.09);
				}
				if(Motor.getInstance().getEncFR() > 1500){
					Motor.getInstance().moveLeftSideMotors(.09);
				}
				SmartDashboard.putString("DB/String 0", "FL: " + Motor.getInstance().getEncFL());
				SmartDashboard.putString("DB/String 1", "FR: " + Motor.getInstance().getEncFR());
			}
			
			try {
				//Pause for .2 seconds
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
            //move slowly up the ramp
			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);

			try {
				//Pause for 0.4 seconds
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
//            Slowing down the grappler (so it doesnt break)
			for (int i = 0; i < 150; i++) {
				Motor.getInstance().moveGrapplerArmMotor(250);
				try {
					//pausing for 25 ms
					Thread.sleep(15);
				} catch (InterruptedException e) {
				}
			}
            
			try {
				//Pause for 0.4 seconds
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
            //Move forward to catch the bar
			
			Motor.getInstance().resetDriveEncoders();
			
			Motor.getInstance().moveLeftSideMotors(.28);
			Motor.getInstance().moveRightSideMotors(.25);
			
			while(Motor.getInstance().getEncFL() < 1200 && Motor.getInstance().getEncFR() > -1200){
				if(Motor.getInstance().getEncFL() > 1200){
					Motor.getInstance().moveLeftSideMotors(.09);
				}
				if(Motor.getInstance().getEncFR() < -1200){
					Motor.getInstance().moveLeftSideMotors(.09);
				}
				SmartDashboard.putString("DB/String 0", "FL: " + Motor.getInstance().getEncFL());
				SmartDashboard.putString("DB/String 1", "FR: " + Motor.getInstance().getEncFR());
			}
			
			
			try {
				//Pause for 1 second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
             
			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);
//            pause teleOP methods
			TeleOp.waiting = true;
		}

		if (Controller.getInstance().getJoy2Button(2)) {
			for (int i = 0; i < 75; i++) {
				Motor.getInstance().moveGrapplerArmMotor(0);
				if (i > 20) {
					Motor.getInstance().moveWinchMotors(8000);
				}
				try {
					//Pause for 25 ms
					Thread.sleep(25);
				} catch (InterruptedException e) {
				}
			}
            //Stop running wheel motors
			Motor.getInstance().moveLeftSideMotors(0);
			Motor.getInstance().moveRightSideMotors(0);
			//failsafe to ensure other methods do not run, as this is the last action in the game

			while (true) {
				Motor.getInstance().moveWinchMotors(8000);
//				Motor.getInstance().moveGrapplerArmMotor(-260);
			}
		}
	}
}
