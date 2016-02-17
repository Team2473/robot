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
	static int session3;
	static Image frame1;
	static Image frame2;
	static Image frame3;
	static NIVision.Rect rect;
	static boolean session1NotStarted = true;
	static boolean session2NotStarted = true;
	static boolean session3NotStarted = true;
	
	static boolean button1Pressed = false;
	static boolean button2Pressed = false;
	static boolean button3Pressed = false;


	public static void visionInit() {
		frame1 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		frame2 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		frame3 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		// the camera name (ex "cam0") can be found through the roborio web
		// interface
		session1 = NIVision.IMAQdxOpenCamera("cam0",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);

		session2 = NIVision.IMAQdxOpenCamera("cam1",
				NIVision.IMAQdxCameraControlMode.CameraControlModeListener);
		
//		session3 = NIVision.IMAQdxOpenCamera("cam2",
//				NIVision.IMAQdxCameraControlMode.CameraControlModeListener);

		// create rectangle
		// rect = new NIVision.Rect(ypos, xpos, height, width); x, y top left
		rect = new NIVision.Rect(50, 100, 100, 200);

	}

	public static void updateDashboard() {
		
		
		//session 1
		if (Controller.getInstance().getButton(1)) {
			button1Pressed = true;
			button2Pressed = false;
			button3Pressed = false;
		
			
			
		}else if(Controller.getInstance().getButton(2)){
			button1Pressed = false;
			button2Pressed = true;
			button3Pressed = false;
		
			
		}else if(Controller.getInstance().getButton(3)){
			button1Pressed = false;
			button2Pressed = false;
			button3Pressed = true;
			
		}
		
		if(button1Pressed && !Controller.getInstance().getButton(2) && !Controller.getInstance().getButton(3)){
			// sessions 2 & 3 is no longer started
						session2NotStarted = true;
						session3NotStarted = true;

						// configure if session not started
						if (session1NotStarted) {
							// End previous sessions
							NIVision.IMAQdxUnconfigureAcquisition(session2);
//							NIVision.IMAQdxUnconfigureAcquisition(session3);

							// Configure Grab
							NIVision.IMAQdxConfigureGrab(session1);

							// start acquisition
							NIVision.IMAQdxStartAcquisition(session1);

							// session 1 is started
							session1NotStarted = false;
						}
						
						// grab image
						NIVision.IMAQdxGrab(session1, frame1, 1);

						// draw on image
						NIVision.imaqDrawShapeOnImage(frame1, frame1, rect,
								DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 25.0f);

						// send image to dashboard
						CameraServer.getInstance().setImage(frame1);
			
		}else if (button2Pressed && !Controller.getInstance().getButton(1) && !Controller.getInstance().getButton(3)) {
			// sessions 1 & 3 is no longer started
			session1NotStarted = true;
			session3NotStarted = true;

			// configure if session not started
			if (session2NotStarted) {
				// End previous sessions
				NIVision.IMAQdxUnconfigureAcquisition(session1);
//				NIVision.IMAQdxUnconfigureAcquisition(session3);

				// Configure Grab
				NIVision.IMAQdxConfigureGrab(session2);

				// start acquisition
				NIVision.IMAQdxStartAcquisition(session2);

				// session 2 is started
				session2NotStarted = false;
			}

			// grab image
			NIVision.IMAQdxGrab(session2, frame2, 1);

			// draw on image
			NIVision.imaqDrawShapeOnImage(frame2, frame2, rect,
					DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 25.0f);

			// send image to dashboard
			CameraServer.getInstance().setImage(frame2);
		}
//		else if (button3Pressed && !Controller.getInstance().getButton(2) && !Controller.getInstance().getButton(1)) {
//			// sessions 1 & 2 is no longer started
//			session1NotStarted = true;
//			session2NotStarted = true;
//
//			// configure if session not started
//			if (session3NotStarted) {
//				// End previous sessions
//				NIVision.IMAQdxUnconfigureAcquisition(session1);
//				NIVision.IMAQdxUnconfigureAcquisition(session2);
//
//				// Configure Grab
//				NIVision.IMAQdxConfigureGrab(session3);
//
//				// start acquisition
//				NIVision.IMAQdxStartAcquisition(session3);
//
//				// session 3 is started
//				session3NotStarted = false;
//			}
//
//			// grab image
//			NIVision.IMAQdxGrab(session3, frame3, 1);
//
//			// draw on image
//			NIVision.imaqDrawShapeOnImage(frame3, frame3, rect,
//					DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 25.0f);
//
//			// send image to dashboard
//			CameraServer.getInstance().setImage(frame3);
//		}
		
		
	}
}
