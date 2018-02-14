package org.usfirst.frc.team5188.robot;



import org.usfirst.frc.team5188.robot.commands.ElevatorRaiseTo;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static class Controller {
		public static final int DRIVE = 0, OPERATOR = 1;
	}
	
	//buttons of controller 
	public static class Buttons {
		public static int
		A = 1,
		B = 2,
		X = 3,
		Y = 4,
		L = 5,
		R = 6,
		BACK = 7,
		START = 8,
		L_STICK = 9,
		R_STICK = 10,
		TOTAL_BUTTONS = 10;
	}
	
	//controller axis  (including rTrigger and lTrigger) 
	public static class Axis {
		public static int
		LX = 0,
		LY = 1,
		LTrigger = 2,
		RTrigger = 3,
		RX = 4,
		RY = 5,
		AXIS_TOTAL = 6;
	}
	
	//p i and d variables
	public static double
	TURN_TO_DEGREE_P,
	TURN_TO_DEGREE_I,
	TURN_TO_DEGREE_D,
	TURN_TO_DEGREE_ANGLE,
	DRIVE_STAIGHT_P,
	DRIVE_STAIGHT_I,
	DRIVE_STAIGHT_D;
	
	//controllers
	public SuperJoystickPlus drive;
	public SuperJoystickPlus operator;
	
	private Preferences pref;
	
	public OI() {
		//create controllers
		drive = new SuperJoystickPlus(Controller.DRIVE);
		operator = new SuperJoystickPlus(Controller.OPERATOR);
		
		JoystickButton goToSwitch = new JoystickButton(drive, OI.Buttons.A);
		goToSwitch.whenPressed(new ElevatorRaiseTo(36));

		

		SmartDashboard.putData(Scheduler.getInstance());
		
		pref = Preferences.getInstance();
		//rereadPreferences();
	

	}
	
	
	//we tune our pid by using the robot preferences, this method updates those values
//	public void rereadPreferences() {
//		TURN_TO_DEGREE_P = pref.getDouble("TurnToDegree P", Double.POSITIVE_INFINITY);
//		TURN_TO_DEGREE_I = pref.getDouble("TurnToDegree I", Double.POSITIVE_INFINITY);
//		TURN_TO_DEGREE_D = pref.getDouble("TurnToDegree D", Double.POSITIVE_INFINITY);
//		TURN_TO_DEGREE_ANGLE = pref.getDouble("TurnToDegree Angle", Double.POSITIVE_INFINITY);
//		DRIVE_STAIGHT_P = pref.getDouble("DriveStaight P", Double.POSITIVE_INFINITY);
//		DRIVE_STAIGHT_I = pref.getDouble("DriveStaight I", Double.POSITIVE_INFINITY);
//		DRIVE_STAIGHT_D = pref.getDouble("DriveStaight D", Double.POSITIVE_INFINITY);
//
//		// if these are not provided
//		if (TURN_TO_DEGREE_P == Double.POSITIVE_INFINITY) {
//			pref.putDouble("TurnToDegree P", 0);
//			pref.putDouble("TurnToDegree I", 0);
//			pref.putDouble("TurnToDegree D", 0);
//			pref.putDouble("TurnToDegree Angle", 0);
//
//			TURN_TO_DEGREE_P = 0;
//			TURN_TO_DEGREE_I = 0;
//			TURN_TO_DEGREE_D = 0;
//			TURN_TO_DEGREE_ANGLE = 0;
//		}
//	}
}
