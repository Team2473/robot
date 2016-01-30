package org.usfirst.frc.team2473.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import gpdraw.DrawingTool;
//import gpdraw.SketchPad;
//
//import java.awt.Color;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.util.Random;

public class Telemetry {
//	public static void main(String[] arguments) {
//		LidarPlot window = new LidarPlot();
//		double[] a = new double[360];
//		for (int i = 0; i < 360; i++) {
//			a[i] = (new Random()).nextInt(50) + 300;
//		}
//		window.importArray(a);
//		window.toggleLines(true);
//	}

	static AnalogInput ultrasonic1 = new AnalogInput(0);
	static AnalogInput gyro1 = new AnalogInput(1);
	static double[] distanceArray = new double[10];
	static int pos = 0;
	
	public static void ultrasonicValue() {
		distanceArray[pos] = (ultrasonic1.getValue() * 5) / 25.4;
		pos = (pos+1)%10;
		double value = 0;
		for(int i = 0; i < 10; i++){
			value +=distanceArray[i];
		}
		value /= 10;
		value = ((int)((value - 9) * 100))/100.0;
		SmartDashboard.putString("DB/String 0",
				"GetValue:" + ultrasonic1.getValue());
		SmartDashboard.putString("DB/String 1",
				"GetVoltage: " + ultrasonic1.getVoltage());
		SmartDashboard.putString("DB/String 2", "Distance: " + value);
	}
	

		
	public static void uartTest(){	
		SerialPort uart = new SerialPort(115200,SerialPort.Port.kMXP);
		uart.reset();
		while(true) {
			byte[] message = uart.read(10);
			SmartDashboard.putString("DB/String 3", "Uart: " + Arrays.toString(message));
		}
	}
	
}

//class LidarPlot implements KeyListener {
//	SketchPad paper;
//	DrawingTool pen;
//	boolean drawLines;
//	double[] plot;
//
//	double scale;
//	int angle;
//
//	public LidarPlot() {
//		paper = new SketchPad(1000, 1000);
//		pen = new DrawingTool(paper);
//		drawLines = true;
//		paper.addKeyListener(this);
//
//		scale = 1;
//		angle = 30;
//	}
//
//	public void importArray(double[] plot) {
//		this.plot = plot;
//		this.updatePlot();
//	}
//
//	public void toggleLines(boolean b) {
//		drawLines = b;
//		this.updatePlot();
//	}
//
//	private void updatePlot() {
//		pen.home();
//		pen.down();
//		pen.setColor(Color.WHITE);
//		pen.fillRect(1000, 1000);
//		pen.setColor(Color.BLACK);
//		pen.fillCircle(5);
//		pen.setWidth(2);
//		teleport(pen, -500, 0);
//		pen.move(500, 0);
//		teleport(pen, 0, -500);
//		pen.move(0, 500);
//		pen.setWidth(1);
//		teleport(pen, 0, 0);
//		pen.setDirection(0);
//
//		int digits = 0;
//		double tempScale = scale / 5;
//		if (tempScale >= 1) {
//			digits = (((int) tempScale) + "").length() - 1;
//		} else {
//			while (tempScale < 1) {
//				tempScale *= 10;
//				digits--;
//			}
//		}
//
//		for (int a = 0; a < 360; a += 90) {
//			teleport(pen, 0, 0);
//			pen.setDirection(a);
//			for (int i = 0; i <= (int) (50 * Math.pow(10, digits) / scale); i++) {
//
//				pen.drawString("" + (i * 10 * Math.pow(10, -digits)));
//				pen.forward(10 * scale * Math.pow(10, -digits));
//				pen.turnLeft();
//				pen.forward(500);
//				pen.forward(-1000);
//				pen.forward(500);
//				pen.turnRight();
//			}
//		}
//
//		if (drawLines) {
//			pen.up();
//			pen.move(Math.cos(Math.toRadians(0)) * plot[angle] * scale,
//					Math.sin(Math.toRadians(0)) * plot[angle] * scale);
//			pen.down();
//			for (int i = angle; i < 360 + angle; i++) {
//				pen.fillCircle(2);
//				pen.move(Math.cos(Math.toRadians(i - angle)) * plot[i % 360]
//						* scale, Math.sin(Math.toRadians(i - angle))
//						* plot[i % 360] * scale);
//			}
//			pen.fillCircle(2);
//			pen.move(Math.cos(Math.toRadians(0)) * plot[angle] * scale,
//					Math.sin(Math.toRadians(0)) * plot[angle] * scale);
//		} else {
//			for (int i = angle; i < 360 + angle; i++) {
//				pen.home();
//				pen.turnRight();
//				pen.turn(i - angle);
//				teleport(pen, plot[i % 360] * scale);
//				pen.fillCircle(2);
//				pen.up();
//			}
//		}
//	}
//
//	private void teleport(DrawingTool p, double distance) {
//		p.up();
//		p.move(distance);
//		p.down();
//	}
//
//	private void teleport(DrawingTool p, double x, double y) {
//		p.up();
//		p.move(x, y);
//		p.down();
//	}
//
//	public void keyTyped(KeyEvent e) {
//		int id = e.getID();
//		String keyString;
//		if (id == KeyEvent.KEY_TYPED) {
//			char c = e.getKeyChar();
//			keyString = "key character = '" + c + "'";
//		} else {
//			int keyCode = e.getKeyCode();
//			keyString = "key code = " + keyCode + " ("
//					+ KeyEvent.getKeyText(keyCode) + ")";
//		}
//		System.out.println(keyString);
//		if (e.getKeyChar() == 'w') {
//			scale *= 1.02;
//		} else if (e.getKeyChar() == 's') {
//			scale /= 1.02;
//		} else if (e.getKeyChar() == 'd') {
//			if (angle < 359) {
//				angle++;
//			} else {
//				angle = 0;
//			}
//		} else if (e.getKeyChar() == 'a') {
//
//			if (angle > 0) {
//				angle--;
//			} else {
//				angle = 359;
//			}
//		} else if (e.getKeyChar() == 't') {
//			drawLines = !drawLines;
//		}
//		this.updatePlot();
//	}
//
//	public void keyPressed(KeyEvent e) {
//	}
//
//	public void keyReleased(KeyEvent e) {
//	}
//}