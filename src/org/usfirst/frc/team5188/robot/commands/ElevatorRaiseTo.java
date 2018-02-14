package org.usfirst.frc.team5188.robot.commands;

import org.usfirst.frc.team5188.robot.OI;
import org.usfirst.frc.team5188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class ElevatorRaiseTo extends PIDCommand {
	boolean isFinished;
	double setPoint;
	static double p = .5;
	static double i = 0;
	static double d = 0;

	
	public ElevatorRaiseTo(double inches) {
		super(p, i, d);
		this.setPoint = inches;
		this.requires(Robot.elevator);
		this.isFinished = false;
	}
	
	public void initialize() {
		this.getPIDController().setPID(p, i, d);
		this.setSetpoint(setPoint);
		this.getPIDController().enable();
	}
	
	public void execute() {
		
	}
	
	public void interrupted() {
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.getPIDController().onTarget() || Math.abs(Robot.oi.operator.getAxis(OI.Axis.LY)) > 0.05;
	}

	@Override
	protected double returnPIDInput() {
		
		return Robot.elevator.getElevatorEncoder();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (Robot.elevator.validMove(output)) {
			Robot.elevator.move(output);
		}
		
	}


}
