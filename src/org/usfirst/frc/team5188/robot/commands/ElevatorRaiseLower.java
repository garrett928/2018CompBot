package org.usfirst.frc.team5188.robot.commands;

import org.usfirst.frc.team5188.robot.OI;
import org.usfirst.frc.team5188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorRaiseLower extends Command {
	boolean isFinished;


	public ElevatorRaiseLower() {
		requires(Robot.elevator);
	}

	public void initialize() {
		isFinished = false;

	}

	public void execute() {
		double power = Robot.oi.operator.getAxis(OI.Axis.LY);


		Robot.elevator.move(power);
	}

	public void interrupted() {
		//isFinished = true;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
