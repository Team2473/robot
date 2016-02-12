package org.usfirst.frc.team2473.robot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

//random change
public class Vision {
	static int session1;
	static int session2;
    static Image frame1;
    static Image frame2;
    static NIVision.Rect rect;

    public static void visionInit() {
        frame1 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        
        frame2 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session1 = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        
        session2 = NIVision.IMAQdxOpenCamera("cam1",
                NIVision.IMAQdxCameraControlMode.CameraControlModeListener);
        
        NIVision.IMAQdxConfigureGrab(session1);
        
        NIVision.IMAQdxConfigureGrab(session2);
        
        //create rectangle
//        rect = new NIVision.Rect(ypos, xpos, height, width); x, y top left
        rect = new NIVision.Rect(50, 100, 100, 200);
        
        //start accquisition
        NIVision.IMAQdxStartAcquisition(session1);
        
        NIVision.IMAQdxStartAcquisition(session2);
    }

    public static void updateDashboard() {
        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */

            NIVision.IMAQdxGrab(session1, frame1, 1);
            
            NIVision.IMAQdxGrab(session2, frame2, 1);
            
            NIVision.imaqDrawShapeOnImage(frame1, frame1, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 25.0f);
            
            if (Controller.getInstance().getButton(0))
            	CameraServer.getInstance().setImage(frame1);
            
            if (Controller.getInstance().getButton(1))
            	CameraServer.getInstance().setImage(frame2);
    }
}

