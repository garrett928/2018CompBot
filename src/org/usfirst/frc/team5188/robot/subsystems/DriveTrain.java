package org.usfirst.frc.team5188.robot.subsystems;

import org.usfirst.frc.team5188.robot.RobotMap;
import org.usfirst.frc.team5188.robot.commands.Drive;


import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	private VictorSP leftDrive1;
	private VictorSP rightDrive1;
	private VictorSP leftDrive2;
	private VictorSP rightDrive2;
	private VictorSP strafe;


	public DriveTrain() {
		leftDrive1 = new VictorSP(RobotMap.frontLeft);
		leftDrive2 = new VictorSP(RobotMap.backLeft);

		rightDrive1 = new VictorSP(RobotMap.frontRight);
		rightDrive2 = new VictorSP(RobotMap.backRight);
		
		strafe = new VictorSP(RobotMap.hWheel);

	}

	/** 
	 * This is used to allow autonomous to get through acceleration and
	 * velocity controls we may place and instead use PID.
	 */
	public void driveRaw(double left, double right, double strafe) {
		leftDrive1.set(-left);
		leftDrive2.set(-left);
		rightDrive1.set(right);
		rightDrive2.set(right);
		this.strafe.set(strafe);
	}
	
	/**
	 * Drive in teleop.
	 */
	public void drive(double left, double right, double strafe) {
		driveRaw(left, right, strafe);
		smartDashboard();
		}

	public void stop() {
		drive(0, 0, 0);
	}


	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
	public void smartDashboard() {
//		SmartDashboard.putNumber("DriveTrain Left", leftDrive1.get());
//		SmartDashboard.putNumber("DriveTrain Right", -rightDrive1.get());
//		SmartDashboard.putNumber("DriveTrain Gyro", RobotMap.gyro.getAngle());
	}
}
