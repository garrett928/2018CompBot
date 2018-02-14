package org.usfirst.frc.team5188.robot.subsystems;

import org.usfirst.frc.team5188.robot.RobotMap;
import org.usfirst.frc.team5188.robot.commands.ElevatorRaiseLower;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	VictorSP leftMotor;
	VictorSP rightMotor;
	
	DigitalInput bottomHalleffect;
	DigitalInput topHalleffect;
	Encoder elevatorEncoder;

	
	public Elevator() {
		leftMotor = new VictorSP(RobotMap.leftElevator);
		rightMotor = new VictorSP(RobotMap.rightElevator);
		
		bottomHalleffect =  new DigitalInput(RobotMap.bottomHalleffect);
		topHalleffect =  new DigitalInput(RobotMap.topHalleffect);

		elevatorEncoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
	}
	
	public void stop() {
		move(0);
	}
	
	public boolean validMove(double power) {
		boolean isValid = false;
		if(power < 0 && bottomHalleffect.get()) {
			isValid = false;
		}
		else if(power > 0 && topHalleffect.get()){
			isValid = false;
		}
		else {
			isValid = true;
		}
		
		return isValid;
	}
	
	public double getElevatorEncoder() {
		return elevatorEncoder.getDistance();
	}
	
	public void resetElevatorEnocder() {
		elevatorEncoder.reset();
	}
	
	public void move(double speed) {
		if(!this.validMove(speed)) speed = 0;
		leftMotor.set(speed);
		rightMotor.set(-speed);//may need flipped

	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorRaiseLower());

	}

}
