package org.usfirst.frc.team2473.robot;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.FlowControl;

/**
 * Wrapper class for angles recieved from Lidar. Contains degree, distance, as
 * well as solidity as reported by Lidar. Also includes methods for calculating
 * velocity of object relative to velocity as calculated by lidar motor.
 * 
 * @author Rehan S. Durrani
 */

public class LiDar {
	public static void main(String[] args){
		Reader r = new Reader();
		r.run();
	}
}

class DM extends Thread {

	public static ArrayList<Angle> angles = new ArrayList<Angle>();
	public static ArrayList<Angle> readA = new ArrayList<Angle>();
	public static ArrayList<Wall> walls = new ArrayList<Wall>();
	public static ArrayList<Wall> prevIter = new ArrayList<Wall>();
	private static InputStream in = null;
	private static boolean poolNoodle = false;

	public DM() {
		super();
	}

	public void run() {
		for (int i = 2; i < angles.size() - 2; i++) {
			if (stackoverflow(i)) {
				int abber = 0;
				int r = 1;
				boolean continu = true;
				while (continu) {
					continu = false;
					if (i + r < angles.size() && i - r > 0) {
						double dist1 = Math.round(angles.get(i + r).dist() * 10000) / 10000.0;
						double dist2 = Math.round(angles.get(i - r).dist() * 10000) / 10000.0;
						if (dist1 == dist2) {
							continu = true;
							r++;
						} else if (i + r + 1 < angles.size() && i - r - 1 > 0) {
							double dist3 = Math.round(angles.get(i + r + 1).dist() * 10000) / 10000.0;
							double dist4 = Math.round(angles.get(i - r - 1).dist() * 10000) / 10000.0;
							if (dist3 == dist4) {
								continu = true;
								abber++;
								r++;
							}
						} else {
							continu = false;
						}
					}
				}
				if (--r > 2) {
					walls.add(new Wall(angles.get(i).dist(), r, angles.get(i).rad(), (abber > 0)));
					if (abber > 0) {
						walls.get(walls.size() - 1).setA(abber);
					}
				}
				i += r + 3;
			}
		}
	}

	public static boolean stackoverflow(int a) {
		double dist1 = Math.round(angles.get(a - 1).dist() * 1000) / 1000.0;
		double dist2 = Math.round(angles.get(a + 1).dist() * 1000) / 1000.0;
		double dist3 = Math.round(angles.get(a - 2).dist() * 1000) / 1000.0;
		double dist4 = Math.round(angles.get(a + 2).dist() * 1000) / 1000.0;
		double dist0 = Math.round(angles.get(a).dist() * 1000) / 1000.0;
		return (dist1 == dist2) && (dist3 == dist4) && (dist1 != dist0 && dist3 != dist0);
	}

	public static void refresh(ArrayList<Byte> bstream) {

	}

	public static void walls() {

	}
}

class Angle {
	private double rad;
	private double dist;
	private String sol;
	boolean flag;

	public Angle(double rad, double dist, String sol) {
		this.rad = rad;
		this.dist = dist;
		this.sol = sol;
	}

	public boolean updated() {
		return flag;
	}

	public double rad() {
		return rad;
	}

	public double dist() {
		return dist;
	}

	public String sol() {
		return sol;
	}

	public String toString() {
		return "Angle: " + rad + "\nDistance: " + dist + "\n\n\n\n";
	}

}

class Wall {
	private double dist;
	private double dur;
	private double ang;
	private boolean abFlag;
	private int abberations;

	public Wall(double dist, double dure, double centAngle, boolean aFlag) {
		this.dist = dist;
		dur = dure;
		ang = centAngle;
		abFlag = aFlag;
	}

	public boolean setA(int a) {
		abberations = a;
		return abberations == a;
	}

	public int ab() {
		return (abFlag) ? abberations : 0;
	}

	public double dist() {
		return dist;
	}

	public double dur() {
		return dur;
	}

	public double Angle() {
		return ang;
	}

	public String toString() {
		return "Central Angle (Closest distance): " + ang + "; Distance: " + dist + "; Duration (In Angles): " + dur
				+ ((abFlag) ? ("; Abberations: " + abberations + "; Percent: "
						+ (double) (Math.round(abberations / (2 * dur) * 100000) / 1000.0) + "%") : "");
	}
}

class Reader extends Thread {

	public static ArrayList<Angle> readA = new ArrayList<Angle>();
	private static boolean poolNoodle = false;
	SerialPort sp = new SerialPort(115200, SerialPort.Port.kOnboard, 8, SerialPort.Parity.kNone,
			SerialPort.StopBits.kOne);
	static final byte[] START = {0,1,0,1,0,0,1,1,0,1,0,0,0,1,0,0,0,0,0,0,1,1,0,1};
	static final byte[] STOP =  {0,1,0,1,0,0,1,1,0,1,0,1,1,0,0,0,0,0,0,0,1,1,0,1};
	public Reader() {
		super();
	}

	public void run() {
		sp.setFlowControl(FlowControl.kNone);
		fillArray();
	}

	public void fillArray() {
		int i;
		boolean canRead = false;
		ByteBuffer bf = null;
		int ang;
		double dist;
		byte[] b;
		int i = 0;
		while (i < 30) {
			sp.write(START, START.length);
			sp.read(1);
			b = sp.read(2);
			sp.write(STOP, STOP.length);
			bf = ByteBuffer.wrap(b);
			ang = bf.getShort();
			sp.write(START, START.length);
			b = sp.read(2);
			sp.read(2);
			sp.write(STOP, STOP.length);
			double temp1 = (0XFF & (int)b[0]);
		    double temp = temp1/256.0;
		    double temp2 = (0XFF & (byte)b[1]);
			dist = temp2 + temp;
			System.out.println("Angle: " + ang + " Distance: " + dist);
			i++;
			if (readA.size() == 360) {
				canRead = true;
			}
			if(poolNoodle && canRead){
				while(poolNoodle);
			}
		}
	}

	public ArrayList<Angle> getA(ArrayList<Angle> reada) {
		poolNoodle = true;
		ArrayList<Angle> vSatish = new ArrayList<Angle>();
		for (int i = 0; i < reada.size(); i++) {
			vSatish.add(new Angle(reada.get(i).rad(), reada.get(i).dist(), reada.get(i).sol()));
		}
		poolNoodle = false;
		return vSatish;
	}
}
// class WallFinder extends Thread {
// public double rad;
// public double adist;
// private int i;
// public boolean wall;
//
// public WallFinder(double angle, int i) {
// this.setPriority(MIN_PRIORITY);
// rad = angle;
// this.i = i;
// }
//
// public boolean runO() {
// run();
// return wall;
// }
//
// public void run() {
// int r = 1;
// boolean continu = true;
// while (continu) {
// continu = false;
// if (i + r < LDM.angles.size() && i - r > 0) {
// double dist1 = Math.round(LDM.angles.get(i + r).dist() * 10000) / 10000.0;
// double dist2 = Math.round(LDM.angles.get(i - r).dist() * 10000) / 10000.0;
// if (dist1 == dist2) {
// continu = true;
// r++;
// } else {
// continu = false;
// }
// }
// }
// adist = --r;
// wall = (adist > 2);
// }
// }