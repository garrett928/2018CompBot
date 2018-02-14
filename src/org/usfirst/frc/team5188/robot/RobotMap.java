/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5188.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int frontLeft = 0;
	public static final int frontRight = 2;
	public static final int backLeft = 1;
	public static final int backRight = 3;
	public static final int hWheel = 4;
	public static final int leftIntake = 5;
	public static final int rightIntake = 6;
	public static final int leftElevator = 7;
	public static final int rightElevator = 8;
	
	public static final int bottomHalleffect = 0;
	public static final int topHalleffect = 1;
	public static final int elevatorEncoderA = 2;
	public static final int elevatorEncoderB = 3;



	



	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
