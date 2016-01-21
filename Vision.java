package org.usfirst.frc.team2473.robot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

//random change
public class Vision {
	
	static CameraServer server;
	
	public static void cameraInit() {
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam3");
   }
	
	int session;
    Image frame;

    public void robotInit() {

        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
    }

    public void operatorControl() {
        NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

        while (isOperatorControl() && isEnabled()) {

            NIVision.IMAQdxGrab(session, frame, 1);
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
            
            CameraServer.getInstance().setImage(frame);

            /** robot code here! **/
            Timer.delay(0.005);		// wait for a motor update time
        }
        NIVision.IMAQdxStopAcquisition(session);
    }

    public void test() {
    }
	
	
	
}

class TCPSocket {
	ServerSocket welcomeSocket;
//	String clientSentence;
//  String capitalizedSentence;
	String dataToClient;
    Socket connectionSocket;
    DataOutputStream outToClient;
    DataInputStream inToClient;
    Joystick joy1;
    int counter = 0;
    
    boolean listening = false;
	
	public TCPSocket() {
	    try {
			welcomeSocket = new ServerSocket(5801);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    joy1 = new Joystick(0);
		
	}
	
	public void teleopInit(){
		SmartDashboard.putString("DB/String 0", "Socket is running!");
		try{
        connectionSocket = welcomeSocket.accept();
//        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        inToClient = new DataInputStream(connectionSocket.getInputStream());
		}catch(Exception e){
			
		}
	}
	
	public void teleop () {
		try {
				//print something to test if socket is running
//	            clientSentence = inFromClient.readLine();
//	            capitalizedSentence = clientSentence.toUpperCase() + '\n';
//	            if(joy1.getRawButton(6)){
//	            	//dataToClient = " Hello World! It's dangerous to go alone, take this! You'll need it to screw everyone over!";
//	            	dataToClient = "6";
//	            	outToClient.writeBytes(dataToClient);
//	            	listening = true;
//	            }
//	            if(listening){
//		            byte[] b = new byte[1000];
//		            inToClient.read(b);
//		            
//		            String s = "";
//		            for(int i=0; i<b.length; i++){
//		            	s += (char) (b[i]);
//		            }
//		            
////		            byte[] c = new byte[1000];
////		            inToClient.read(c);
//
////		            String s2 = "";
////		            for(int i = 0; i < c.length; i++){
////		            	s2 += (char) (c[i]);
////		            }
//		            
//		            SmartDashboard.putString("DB/String 9", "tcp " + Math.random());
//		            SmartDashboard.putString("DB/String 1", "" + s);
//		            s = "";
////		            SmartDashboard.putString("DB/String 2", s2);
//		            listening = false;
//	            }
//	            SmartDashboard.putString("DB/String 2", "" + listening);
				
			counter++;
			if(counter > 0){
				dataToClient = "Data request";
            	outToClient.writeBytes(dataToClient);
            	byte[] b = new byte[1000];
	            inToClient.read(b);
	            String s = "";
	            for(int i=0; i<b.length; i++){
	            	if(b[i] == 0)
	            		break;
	            	s += (char) (b[i]);
	            }
	            JSONObject json = null;
	            try {
					json = new JSONObject(s);
					SmartDashboard.putString("DB/String 1", json.getString("bluePercentage"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            s = "";
            	counter = 0;
			}
			
			
				
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		SmartDashboard.putString("DB/String 3", "" + joy1.getX());
	}

}
