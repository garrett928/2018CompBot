package org.usfirst.frc.team5188.robot.commands;

import org.usfirst.frc.team5188.robot.OI;
import org.usfirst.frc.team5188.robot.Robot;
import org.usfirst.frc.team5188.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//garrett is dumb
public class Drive extends Command {
	public Drive() {
		requires(Robot.driveTrain);
	}

	public void execute() {
		
		//sets joystick values
		double strafe = Robot.oi.drive.getAxis(OI.Axis.LX);
		double throttle = Robot.oi.drive.getAxis(OI.Axis.LY);
		double turn = Robot.oi.drive.getAxis(OI.Axis.RX);
		
		//checks if shifter is pressed
		double shifter = Robot.oi.drive.getRawButton(OI.Buttons.R) == false ? 1 : .5;
		
		//init drive variables
		double lDrive;
		double rDrive;
	
		if(Math.abs(strafe) < .1) {
			strafe = 0;
		}
		
		//if no throttle, then quick turn
		if (Math.abs(throttle) < 0.05) {
			//quick turn
			lDrive = -turn * shifter * 0.60;
			rDrive = turn * shifter * 0.60;
		//drive in arcade
		}else {
			lDrive = shifter * throttle  * (1 + Math.min(0, turn));
			rDrive = shifter * throttle  * (1 - Math.max(0, turn));
		}

		//ACTUAL DRIVE COMMAND
		Robot.driveTrain.drive(lDrive, rDrive, strafe * .8);
	}

	public void end() {
		Robot.driveTrain.stop();
	}

	protected boolean isFinished() {
		return false;
	}
}
