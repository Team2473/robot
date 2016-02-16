package org.usfirst.frc.team2473.robot;
import edu.wpi.first.wpilibj.DigitalInput;
public class Switches {
	
	DigitalInput ioSwitch1; 
	DigitalInput ioSwitch2;
	DigitalInput ioSwitch3;
	DigitalInput ioSwitch4;
	DigitalInput ioSwitch5;
	DigitalInput ioSwitch6;
	DigitalInput ioSwitch7;
	DigitalInput ioSwitch8; 
    public Switches{
    	//switch 1
    	ioSwitch1 = new DigitalInput(1);
    	ioSwitch2 = new DigitalInput(2);
    	ioSwitch3 = new DigitalInput(3);
    	//switch 2
    	ioSwitch4 = new DigitalInput(4);
    	ioSwitch5 = new DigitalInput(5);
    	ioSwitch6 = new DigitalInput(6);
    	//switch3
    	ioSwitch7 = new DigitalInput(7);
    	ioSwitch8 = new DigitalInput(8);

    	
    	
    }
    public static void printDigitalInputs(){
		SmartDashboard.putString("DB/String 10", ioSwitch1.get());
		SmartDashboard.putString("DB/String 10", ioSwitch2.get());
		SmartDashboard.putString("DB/String 10", ioSwitch3.get());
		SmartDashboard.putString("DB/String 10", ioSwitch4.get());
		SmartDashboard.putString("DB/String 10", ioSwitch5.get());
		SmartDashboard.putString("DB/String 10", ioSwitch6.get());
		SmartDashboard.putString("DB/String 10", ioSwitch7.get());
		SmartDashboard.putString("DB/String 10", ioSwitch8.get());

		
    	
    }
    //
    
    
    public int getSwitch1Value(){
    	int total = 0;
    	if(ioSwitch1.get()){
    		total += Math.pow(2, 0);
    		
    	}
    	
    	return total;
    	
    	
    }
    
    public int getSwitch2Value(){
    	int total = 0;
    	if(ioSwitch2.get()){
    		total += Math.pow(2, 0);
    		
    	}
    	
    	return total;
    	
    }
    
    public int getSwitch3Value(){
    	int total = 0;
    	if(ioSwitch3.get()){
    		total += Math.pow(2, 0);
    		
    	}
    	
    	return total;
    }
    public int getSwitch4Value(){
    	int total = 0;
    	if(ioSwitch4.get()){
    		total += Math.pow(2, 0);
    		
    	}
    	if(ioSwitch5.get()){
    		total += Math.pow(2, 1);
    		
    	}
    	if(ioSwitch6.get()){
    		total += Math.pow(2, 2);
    		
    	}
    	return total;
    	
    	
    }
    
    
    public void setSwitch5(int num){
    	int total = 0;
    	if(ioSwitch7.get()){
    		total += Math.pow(2, 0);
    		
    	}
    	if(ioSwitch8.get()){
    		total += Math.pow(2, 1);
    		
    	}
    	
    	return total;
	
	
    }
}
