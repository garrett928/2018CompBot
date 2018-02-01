
package org.usfirst.frc.team5188.robot;

import org.usfirst.frc.team5188.robot.commands.Baseline;
import org.usfirst.frc.team5188.robot.commands.Switch;
import org.usfirst.frc.team5188.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	public static OI oi;
	public static DriveTrain driveTrain;

	//init auto choosers 
	SendableChooser<Command> leftChooser = new SendableChooser<>();
	SendableChooser<Command> rightChooser = new SendableChooser<>();

	final String TIMERBOX = "Timer Box";
	String gameData;
	
	double delayLength;
	
	Command selectedAuto;
	
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		
		//add left chooser box
		leftChooser.addDefault("Baseline", new Baseline());
		leftChooser.addObject("Switch", new Switch());
		SmartDashboard.putData("Left Auto", leftChooser);
		
		//add right chooser box
		rightChooser.addDefault("Baseline", new Baseline());
		rightChooser.addObject("Switch", new Switch());
		SmartDashboard.putData("Right Auto", rightChooser);
		
		SmartDashboard.putNumber(TIMERBOX, 0.0);
		
		// OI must be initialized after all subsystems
		oi = new OI();

		}

	
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		smartDashboard();
		Scheduler.getInstance().run();
	}

	
	@Override
	public void autonomousInit() {
		gameData =  DriverStation.getInstance().getGameSpecificMessage();
		delayLength = SmartDashboard.getNumber(TIMERBOX, 0);
		
		
		// schedule the autonomous command (example)
//		if (autonomousCommand != null)
//			autonomousCommand.start();
	}

	
	@Override
	public void autonomousPeriodic() {
		if(gameData.charAt(0) == 'L') {
			Timer.delay(delayLength);
			
			selectedAuto = leftChooser.getSelected();
			if(selectedAuto != null) selectedAuto.start();
			
		} else {
			Timer.delay(delayLength);

			selectedAuto = rightChooser.getSelected();
			if(selectedAuto != null) selectedAuto.start();
		}
		smartDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
//		if (autonomousCommand != null)
//			autonomousCommand.cancel();
	}

	
	@Override
	public void teleopPeriodic() {
		smartDashboard();
		Scheduler.getInstance().run();
	}

	
	@Override
	public void testPeriodic() {
		smartDashboard();
		LiveWindow.run();
	}

	public void smartDashboard() {
		//driveTrain.smartDashboard();
	}

	/** Prevent button creep */
	public static double deadband(double d) {
		final double band = 0.05;
		if (d > -band && d < band) {
			return 0;
		} else {
			return d;
		}
	}
}
