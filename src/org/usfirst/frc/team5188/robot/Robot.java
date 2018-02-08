
package org.usfirst.frc.team5188.robot;

import org.usfirst.frc.team5188.robot.commands.Baseline;
import org.usfirst.frc.team5188.robot.commands.NullCommand;
import org.usfirst.frc.team5188.robot.commands.Switch;
import org.usfirst.frc.team5188.robot.commands.AutoPaths.Base1;
import org.usfirst.frc.team5188.robot.commands.AutoPaths.CLSW;
import org.usfirst.frc.team5188.robot.commands.AutoPaths.CRSW;
import org.usfirst.frc.team5188.robot.commands.AutoPaths.SCL;
import org.usfirst.frc.team5188.robot.commands.AutoPaths.SCR;
import org.usfirst.frc.team5188.robot.commands.AutoPaths.SWL;
import org.usfirst.frc.team5188.robot.commands.AutoPaths.SWR;
import org.usfirst.frc.team5188.robot.commands.DriverStations.DS1;
import org.usfirst.frc.team5188.robot.commands.DriverStations.DS2;
import org.usfirst.frc.team5188.robot.commands.DriverStations.DS3;
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
	SendableChooser<Command> driverStations = new SendableChooser<>();
	SendableChooser<Command> autoOptions = new SendableChooser<>();

	
	final String TIMERBOX = "Timer Box";
	final String DS1 = "ds1";
	final String DS2 = "ds2";
	final String DS3 = "ds3";
	final String BASE1 = "Base1";
	final String SWR = "SWR";
	final String SWL = "SWL";
	final String SCR = "SCR";
	final String SCL = "SCL";
	final String CRSW = "CRSW";
	final String CLSW = "CLSW";

	String gameData;
	String driverStationPos;	
	boolean ds1, ds2, ds3;
	
	double delayLength;
	
	Command selectedAuto;
	Command driverStationSelected;
	
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		
		
		SmartDashboard.putNumber(TIMERBOX, 0.0);
		SmartDashboard.putBoolean(DS1, false);
		SmartDashboard.putBoolean(DS2, false);
		SmartDashboard.putBoolean(DS3, false);
		
		SmartDashboard.putBoolean(BASE1, false);
		SmartDashboard.putBoolean(SWR, false);
		SmartDashboard.putBoolean(SWL, false);
		SmartDashboard.putBoolean(SCR, false);
		SmartDashboard.putBoolean(SCL, false);
		SmartDashboard.putBoolean(CRSW, false);
		SmartDashboard.putBoolean(CLSW, false);

		

		
		autoOptions.addDefault("Null", new NullCommand());
		autoOptions.addObject("Baseline1", new Base1());
		autoOptions.addObject("Right Switch", new SWR());
		autoOptions.addObject("Left Switch", new SWL());
		autoOptions.addObject("Center Right Switch", new CRSW());
		autoOptions.addObject("Center Left Switch", new CLSW());
		autoOptions.addObject("Right Scale", new SCR());
		autoOptions.addObject("Left Scale", new SCL());

		
		driverStations.addDefault("Null", new NullCommand());
		driverStations.addObject("DriverStation 1", new DS1());
		driverStations.addObject("DriverStation 2", new DS2());
		driverStations.addObject("DriverStation 3", new DS3());

		SmartDashboard.putData("Auto Options", autoOptions);
		SmartDashboard.putData("Driver Stations", driverStations);

		
		// OI must be initialized after all subsystems
		oi = new OI();

		}

	
	@Override
	public void disabledInit() {
		
	}

	@Override
	public void disabledPeriodic() {
		driverStationPos = driverStations.getSelected().getName();
		selectedAuto = autoOptions.getSelected(); 
		SmartDashboard.putBoolean(BASE1, false);
		SmartDashboard.putBoolean(SWR, false);
		SmartDashboard.putBoolean(SWL, false);
		SmartDashboard.putBoolean(SCR, false);
		SmartDashboard.putBoolean(SCL, false);
		SmartDashboard.putBoolean(CRSW, false);
		SmartDashboard.putBoolean(CLSW, false);

		
		if(selectedAuto.getName().equals(BASE1)) SmartDashboard.putBoolean(BASE1, true);
		if(selectedAuto.getName().equals(SWR)) SmartDashboard.putBoolean(SWR, true);
		if(selectedAuto.getName().equals(SWL)) SmartDashboard.putBoolean(SWL, true);
		if(selectedAuto.getName().equals(SCR)) SmartDashboard.putBoolean(SCR, true);
		if(selectedAuto.getName().equals(SCL)) SmartDashboard.putBoolean(SCL, true);
		if(selectedAuto.getName().equals(CLSW)) SmartDashboard.putBoolean(CLSW, true);
		if(selectedAuto.getName().equals(CRSW)) SmartDashboard.putBoolean(CRSW, true);


		SmartDashboard.putBoolean(DS1, false);
		SmartDashboard.putBoolean(DS2, false);
		SmartDashboard.putBoolean(DS3, false);
		
		System.out.println(driverStationPos.equals("DS3"));
		if(driverStationPos.equals("DS1")) SmartDashboard.putBoolean(DS1, true);
		if(driverStationPos.equals("DS2")) SmartDashboard.putBoolean(DS2, true);
		if(driverStationPos.equals("DS3")) SmartDashboard.putBoolean(DS3, true);
		
		Scheduler.getInstance().run();
	}

	
	@Override
	public void autonomousInit() {
		gameData =  DriverStation.getInstance().getGameSpecificMessage();
		delayLength = SmartDashboard.getNumber(TIMERBOX, 0);
		
		driverStationPos = driverStations.getSelected().getName();


		// schedule the autonomous command (example)
//		if (autonomousCommand != null)
//			autonomousCommand.start();
	}

	
	@Override
	public void autonomousPeriodic() {
		if(gameData.charAt(0) == 'L') {
			Timer.delay(delayLength);
		
			
		} else {
			Timer.delay(delayLength);

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
