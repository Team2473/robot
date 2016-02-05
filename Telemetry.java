package org.usfirst.frc.team2473.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
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

	private AnalogInput ultrasonic1;
	private AnalogInput ultrasonic2;
	
	private double[] distanceArray1;
	private double[] distanceArray2;
	private int posUltra1;
	private int posUltra2;
	
	
	private static Telemetry telemetry = null;

	private Telemetry() {
		ultrasonic1 = new AnalogInput(0);
		ultrasonic2 = new AnalogInput(1);
		
		distanceArray1 = new double[10];
		distanceArray2 = new double[10];
		posUltra1 = 0;
		posUltra2 = 0;
	}

	public static Telemetry getInstance() {
		if (telemetry == null) {
			telemetry = new Telemetry();
		}
		return telemetry;
	}
	
	//needs to run continuously for values to be correct
	public void updateUltrasonicValue() {
		distanceArray1[posUltra1] = (ultrasonic1.getValue() * 5) / 25.4;
		posUltra1 = (posUltra1+1)%10;
		
		distanceArray2[posUltra2] = (ultrasonic2.getValue() * 5) / 25.4;
		posUltra2 = (posUltra2+1)%10;
	}
	
	public double getLeftUltrasonicValue(){
		double value = 0;
		for(int i = 0; i < 10; i++){
			value +=distanceArray2[i];
		}
		value /= 10;
		value = ((int)((value - 3) * 100))/100.0;
		return value;
	}
	
	public double getRightUltrasonicValue(){
		double value = 0;
		for(int i = 0; i < 10; i++){
			value +=distanceArray1[i];
		}
		value /= 10;
		value = ((int)((value - 3) * 100))/100.0;
		return value;
	}
	

	//test code
	public static void uartTest(){	
		SerialPort uart = new SerialPort(115200,SerialPort.Port.kMXP);
		uart.reset();
		while(true) {
			byte[] message = uart.read(10);
			SmartDashboard.putString("DB/String 3", "Uart: " + Arrays.toString(message));
		}
	}
	
	//public static AnalogGyro gyro1;
	
	//public static void gryoTest(){
	//	gyro1 = new Gyro(2);
	//	SmartDashboard.putString("DB/String 3", "Gyro: " + gyro1.getAngle());
	//}
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