package org.usfirst.frc.team5414.robot;




import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	Joystick stick;
	Joystick joystick1;
	Joystick joystick2;
	Trigger trigger1; 
	Command autonomousCommand;
	SendableChooser autoChooser;
	int autoLoopCounter;
	JoystickButton aBtn;
	JoystickButton bBtn;
	JoystickButton xBtn;
	JoystickButton yBtn;
	JoystickButton LB;
	JoystickButton RB;
	Compressor myCompressor;	
	DoubleSolenoid DoubleSolenoid1;
	SpeedController driveRightFrontOrange;
	SpeedController driveLeftRearWhite;
	SpeedController driveRightRearBlue;
	SpeedController driveLeftFrontBrown;
	Encoder encoder;
	Command auton1;
	Command auton2;
	CameraServer server;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		driveLeftFrontBrown = new Talon(4);
		//LiveWindow.addActuator("Drive", "LeftFrontBrown", (Talon) driveLeftFrontBrown);
	        
	        driveLeftRearWhite = new Talon(1);
		//LiveWindow.addActuator("Drive", "LeftRearWhite", (Talon) driveLeftRearWhite);
	        
	        driveRightRearBlue = new Talon(3);
	    //LiveWindow.addActuator("Drive", "RightRearBlue", (Talon) driveRightRearBlue);
	        
	        driveRightFrontOrange = new Talon(2);
		//LiveWindow.addActuator("Drive", "RightFrontOrange", (Talon) driveRightFrontOrange);
	        
		myRobot = new RobotDrive(driveLeftFrontBrown,driveLeftRearWhite,driveRightFrontOrange,driveRightRearBlue);
		stick = new Joystick(0);
		joystick1 = new Joystick(1);
		joystick2 = new Joystick(2);
		encoder = new Encoder(1, 2, true, EncodingType.k4X);
		autoChooser = new SendableChooser();
		auton1=new Command()
		{

			@Override
			protected void initialize() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void execute() {
				// TODO Auto-generated method stub
				if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
				{
					encoder.setDistancePerPulse(.5);
					myRobot.drive(-.75, 0.0); 	// drive forwards half speed
					autoLoopCounter++;
				} else {
					myRobot.drive(0.0, 0.0); 	// stop robot
				}
			}

			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected void end() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void interrupted() {
				// TODO Auto-generated method stub
				
			}
		};
		auton2=new Command()
		{

			@Override
			protected void initialize() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void execute() {
				// TODO Auto-generated method stub
				if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
				{
					encoder.setDistancePerPulse(.5);
					myRobot.drive(-.5, 0.5); 	// drive forwards half speed
					autoLoopCounter++;
				} else {
					myRobot.drive(0.0, 0.0); 	// stop robot
				}
			}

			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected void end() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void interrupted() {
				// TODO Auto-generated method stub
				
			}
		};
		autoChooser.addDefault("Default program", auton1);
		autoChooser.addObject("Experimental auto", auton2);
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
		aBtn= new JoystickButton(stick, 1);
		bBtn = new JoystickButton(stick, 2);
		xBtn = new JoystickButton(stick, 3);
		yBtn = new JoystickButton(stick, 4);
		LB = new JoystickButton(stick, 5);
		RB = new JoystickButton(stick, 6);

		myCompressor = new Compressor(0);
		DoubleSolenoid1 = new DoubleSolenoid(0,1);
		server = CameraServer.getInstance();
		server.setSize(1);
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
		
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		autoLoopCounter = 0;
		autonomousCommand = (Command) autoChooser.getSelected();
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		
			
	
	}

	/**
	 * This function is called once each time the robot enters tele-operated mode
	 */
	public void teleopInit(){
	//	myCompressor.setClosedLoopControl(true);
		DoubleSolenoid1 = new DoubleSolenoid(0,1);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		myRobot.tankDrive(stick.getRawAxis(1), stick.getRawAxis(5));
		
		//myRobot.tankDrive(stick.getRawAxis(1), stick.getRawAxis(5));
		//c.start();
		//c.setClosedLoopControl(true);

		//    	boolean enabled = c.enabled();
		//    	boolean pressureSwitch = c.getPressureSwitchValue();
		//    	float current = c.getCompressorCurrent();
		//    	DoubleSolenoid1.set(DoubleSolenoid.Value.kOff);
		if(LB.get())
		{
//			System.out.println("Solenoid forward");
			DoubleSolenoid1.set(DoubleSolenoid.Value.kForward);
		}
		else if(RB.get())
		{
			DoubleSolenoid1.set(DoubleSolenoid.Value.kReverse);
//			System.out.println("Solenoid reverse");
		}
		else
		{
			DoubleSolenoid1.set(DoubleSolenoid.Value.kOff);
			// Prints here are not a good idea because they flood the console
		}

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

}
