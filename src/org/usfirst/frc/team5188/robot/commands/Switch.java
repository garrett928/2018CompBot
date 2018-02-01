package org.usfirst.frc.team5188.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Switch extends Command {
	
	
	public void execute() {
		System.out.println("Switch");
	}
	
	//this command does nothing
	protected boolean isFinished() {
		return true;
	}
}
