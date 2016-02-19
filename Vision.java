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
	private static Vision vision = null;
	
	static int session1;
	static int session2;
	
	static Image frame1;
	static Image frame2;
	
	static NIVision.Rect rect;
	
	static boolean session1NotStarted = true;
	static boolean session2NotStarted = true;
	
	static boolean reverse = false;
	
	public static Vision getInstance() {
		if (vision == null) {
			vision = new Vision();
		}
		return vision;
	}

	public void visionInit() {
		frame1 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		frame2 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		

		session1 = NIVision.IMAQdxOpenCamera("cam0",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);

		session2 = NIVision.IMAQdxOpenCamera("cam1",
				NIVision.IMAQdxCameraControlMode.CameraControlModeListener);
		

		// create rectangle
		rect = new NIVision.Rect(50, 100, 100, 200);

	}

	public void updateDashboard() {
		
		if (Controller.getInstance().getJoy1Button(1)) {
			reverse = true;
		}
		else {
			reverse = false;
		}
		
		if(!reverse){
			// sessions 2 & 3 is no longer started
						session2NotStarted = true;

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
			
		}else {
			// sessions 1 & 3 is no longer started
			session1NotStarted = true;

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
	}
}
