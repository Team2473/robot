package org.usfirst.frc.team2473.robot;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import gpdraw.*;
/////////////////////////////////////////////////////////
//TO USE
//
//Create a LidarPlot class
//Call import Array with a 360 size double array of "distances accessed with degrees"
//Set Speed to no delay in gpdraw window
//
//CONTROLS
//
//"w" to zoom in
//"s" to zoom out
//"a" to rotate counter-clockwise
//"s" to rotate clockwise

public class LidarPlot implements KeyListener{
	SketchPad paper;
	DrawingTool pen;
	boolean drawLines;
	double[] plot;
	
	public LidarPlot(){
		paper = new SketchPad(1000,1000);
		pen = new DrawingTool(paper);
		drawLines = true;
		paper.addKeyListener(this);
	}
	
	public void importArray(double[] plot){
		this.plot = plot;
		this.updatePlot();
	}
	
	public void toggleLines(boolean b){
		drawLines = b;
		this.updatePlot();
	}
	
	private void updatePlot(){
		pen.home();
		pen.down();
		pen.setColor(Color.WHITE);
		pen.fillRect(1000, 1000);
		pen.setColor(Color.BLACK);
		pen.fillCircle(5);
		if(drawLines){
			pen.home();
			pen.turnRight();
			teleport(pen,plot[0]);
			for(int i = 0; i < 360; i++){
				pen.fillCircle(2);
				pen.move(Math.cos(Math.toRadians(i))*plot[i],Math.sin(Math.toRadians(i))*plot[i]);
			}
			pen.fillCircle(2);
			pen.move(Math.cos(Math.toRadians(0))*plot[0],Math.sin(Math.toRadians(0))*plot[0]);
		}else{
			for(int i = 0; i < 360; i++){
				pen.home();
				pen.turn(i);
				teleport(pen, plot[i]);
				pen.fillCircle(2);
				pen.up();
			}
		}
	}
	
	
	private void teleport(DrawingTool p, double distance){
		p.up();
		p.move(distance);
		p.down();
	}
	
	private void teleport(DrawingTool p, double x, double y){
		p.up();
		p.move(x,y);
		p.down();
	}
	
	public static void main(String[] arguments){
		LidarPlot window = new LidarPlot();
		double[] a = new double[360];
		for(int i = 0; i < 360; i++){
			a[i] = (new Random()).nextInt(50) + 300;
		}
		window.importArray(a);
		window.toggleLines(true);
	}

	public void keyTyped(KeyEvent e) {
		  int id = e.getID();
	        String keyString;
	        if (id == KeyEvent.KEY_TYPED) {
	            char c = e.getKeyChar();
	            keyString = "key character = '" + c + "'";
	        } else {
	            int keyCode = e.getKeyCode();
	            keyString = "key code = " + keyCode
	                    + " ("
	                    + KeyEvent.getKeyText(keyCode)
	                    + ")";
	        }
	        System.out.println(keyString);
	        if(e.getKeyChar() == 'w'){
	        	for(int i = 0; i < 360; i++){
	        		plot[i] = plot[i]*1.02;
	        	}
	        }
	        else if(e.getKeyChar() == 's'){
	        	for(int i = 0; i < 360; i++){
	        		plot[i] = plot[i]*.98;
	        	}
	        }else if(e.getKeyChar() == 'd'){
	        	double temp = plot[0];
	        	for(int i = 0; i < 359; i++){
	        		plot[i] = plot[i+1];
	        	}
	        	plot[359] = temp;
	        }else if(e.getKeyChar() == 'a'){
	        	double temp = plot[359];
	        	for(int i = 359; i > 0; i--){
	        		plot[i] = plot[i-1];
	        	}
	        	plot[0] = temp;
	        }
	        this.updatePlot();
	}
	
	public void keyPressed(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {}
}
