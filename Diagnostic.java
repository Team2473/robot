package org.usfirst.frc.team2473.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
public class Diagnostic {
     public Diagnostic() {
    	 
     }
     public void testEverything(){
    	 controllerWorking();
    	 
    	 
    	 
    	 
     }
     
     //testing the controller
     public boolean controllerWorking(){
    	 SmartDashboard.putString("DB/String 1", "Press green button");
    	 while(!Controller.getInstance().getJoy1Button(1)){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Press red button");
    	 while(!Controller.getInstance().getJoy1Button(2)){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Press blue button");
    	 while(!Controller.getInstance().getJoy1Button(3)){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Press yellow button");
    	 while(!Controller.getInstance().getJoy1Button(4)){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Press left bumper");
    	 while(!Controller.getInstance().getJoy1Button(5)){}
    	 SmartDashboard.putString("DB/String 1", "Press right bumper");
    	 while(!Controller.getInstance().getJoy1Button(6)){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Move left joystick up");
    	 
    	 while(Controller.getInstance().getYL() < 0.9){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Move right joystick up");
    	 
    	 while(Controller.getInstance().getYR() < 0.9){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Press left trigger");
    	 while(Controller.getInstance().getLeftTrigger() < 0.9){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Press arm button");
    	 while(!Controller.getInstance().getJoy2Button(1)){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Press winch button");
    	 while(!Controller.getInstance().getJoy2Button(2)){}
    	 
    	 SmartDashboard.putString("DB/String 1", "Press foot pedal");
    	 while(!Controller.getInstance().getJoy2Button(3)){}
    	 
    	 return true;
    	 
     }
     
     
     //Testing switches
     //Human interaction for testing switches:
     public boolean switchesWorking(){
    	
    	 
    	 
    	 return true;
     }
     
     //testing rightside/leftside motors
     public boolean testMotors(){
    	 return true;
     }
     //testing breakbeam
     //Human interaction for testing breakbeam:  
     public boolean breakBreamTest(){
   
 		SmartDashboard.putString("DB/String 1", "Place hand in front ");
		SmartDashboard.putString("DB/String 2", "of breakbeam");
    	 
    	 return true;
     }
     
     //testing grappler
     public boolean grapplerTest(){
    	 
    	 return true;
     }
     
     
    
     //testing gyroscope
     //Human interaction for testing gryoscope:
     public boolean gyroTest(){
    	 return true;
     }
     
     //testing ultrasonic
     public boolean ultrasonicTest(){
    	 
    	 
    	 return true;
     }
     //testing cameraFeed
     public void cameraTest(){
    	 
    	 

     }
}
