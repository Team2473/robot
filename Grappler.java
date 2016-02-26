package org.usfirst.frc.team2473.robot;

public class Grappler {

	private static Grappler grappler = null;

	private Grappler() {
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
			Motor.getInstance().moveLeftSideMotors(.25);
			Motor.getInstance().moveRightSideMotors(.25);

			try {
				//Pause for 3 seconds
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}

			Motor.getInstance().moveLeftSideMotors(0);
			//Accounting for the left side motors moving slightly faster than the right side motors
			Motor.getInstance().moveRightSideMotors(-0.1);

			try {
				//Pause for 1.1 seconds
				Thread.sleep(1100);
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
            //Slowing down the grappler (so it doesnt break)
			for (int i = 0; i < 100; i++) {
				Motor.getInstance().moveGrapplerArmMotor(260);
				try {
					//pausing for 25 ms
					Thread.sleep(25);
				} catch (InterruptedException e) {
				}
			}
            //Move forward to catch the bar
			Motor.getInstance().moveLeftSideMotors(.25);
			Motor.getInstance().moveRightSideMotors(.25);

			try {
				//Pause for 1 second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
             
			Motor.getInstance().moveLeftSideMotors(0.09);
			Motor.getInstance().moveRightSideMotors(0.09);
            //pause teleOP methods
			TeleOp.waiting = true;
		}
        //running the winch
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

			while (true) {
				Motor.getInstance().moveWinchMotors(8000);
				Motor.getInstance().moveGrapplerArmMotor(-260);
			}
		}
	}
}
