package org.usfirst.frc.team2473.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//random change
public class Vision {
	private static Vision vision = null;

	int session1;
	int session2;
	int session3;

	Image frame1;
	Image frame2;
	Image frame3;

	NIVision.Rect rect;

	boolean climbing = false;

	boolean session1NotStarted = true;
	boolean session2NotStarted = true;
	boolean session3NotStarted = true;

	public static Vision getInstance() {
		if (vision == null) {
			vision = new Vision();
		}
		return vision;
	}

	private Vision() {
		frame1 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		frame2 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		frame3 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		session1 = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);

		session2 = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeListener);

		session3 = NIVision.IMAQdxOpenCamera("cam2", NIVision.IMAQdxCameraControlMode.CameraControlModeListener);

		// create rectangle
		rect = new NIVision.Rect(50, 100, 100, 200);

	}

	public void updateDashboard() {
		if(Controller.getInstance().getJoy2Button(3)) {
			climbing = true;
		}

		if (!TeleOp.reverse) {
			// sessions 2 & 3 is no longer started
			session2NotStarted = true;
			session3NotStarted = true;

			// configure if session not started
			if (session1NotStarted) {
				// End previous sessions
				NIVision.IMAQdxUnconfigureAcquisition(session2);
				NIVision.IMAQdxUnconfigureAcquisition(session3);

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
			NIVision.imaqDrawShapeOnImage(frame1, frame1, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 25.0f);

			// send image to dashboard
			CameraServer.getInstance().setImage(frame1);

		} else if (climbing) {
			// sessions 1 & 2 is no longer started
			session1NotStarted = true;
			session2NotStarted = true;

			// configure if session not started
			if (session3NotStarted) {
				// End previous sessions
				NIVision.IMAQdxUnconfigureAcquisition(session1);
				NIVision.IMAQdxUnconfigureAcquisition(session2);

				// Configure Grab
				NIVision.IMAQdxConfigureGrab(session3);

				// start acquisition
				NIVision.IMAQdxStartAcquisition(session3);

				// session 1 is started
				session3NotStarted = false;
			}

			// grab image
			NIVision.IMAQdxGrab(session3, frame3, 1);

			// send image to dashboard
			CameraServer.getInstance().setImage(frame3);
		} else {
			// sessions 1 & 3 is no longer started
			session1NotStarted = true;
			session3NotStarted = true;

			// configure if session not started
			if (session2NotStarted) {
				// End previous sessions
				NIVision.IMAQdxUnconfigureAcquisition(session1);
				NIVision.IMAQdxUnconfigureAcquisition(session3);

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
			NIVision.imaqDrawShapeOnImage(frame2, frame2, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 25.0f);

			// send image to dashboard
			CameraServer.getInstance().setImage(frame2);
		}
	}
}
