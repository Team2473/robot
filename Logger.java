package org.usfirst.frc.team2473.robot;

public class Logger {

	public static void log(String string) {
		
		final Throwable t = new Throwable();
		final StackTraceElement methodCaller = t.getStackTrace()[1];
		System.out.println(methodCaller.getClassName() + " " + methodCaller.getMethodName() + " " + "[" + methodCaller.getLineNumber() + "]: " + string);
	
	}
	
}
