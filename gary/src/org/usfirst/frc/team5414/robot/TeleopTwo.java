package org.usfirst.frc.team5414.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class TeleopTwo extends Command{
	
	SpeedController leftSide;
	SpeedController rightSide;
	RobotDrive Brodin;
	Encoder leftEncoder;
	Encoder rightEncoder;
	Joystick cont;
	JoystickButton LBump;
	JoystickButton RBump;
	PowerDistributionPanel powerDist;
	Gyro gyrate;
	CANTalon manipulate;

	public TeleopTwo(SpeedController leftCon, SpeedController rightCon, RobotDrive drive, Encoder leftEnc,
			Encoder rightEnc, Joystick controller, JoystickButton LBumper, JoystickButton RBumper, 
			PowerDistributionPanel power, Gyro gyrator, CANTalon manipulating)
	{
		leftSide = leftCon;
		rightSide = rightCon;
		Brodin = drive;
		leftEncoder = leftEnc;
		rightEncoder = rightEnc;
		cont = controller;
		LBump = LBumper;
		RBump = RBumper;
		powerDist = power;
		gyrate = gyrator;
		manipulate = manipulating;
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

		leftEncoder.reset();
		rightEncoder.reset();
	}
		@Override
		protected void execute() {
			// TODO Auto-generated method stub

		Brodin.tankDrive(cont.getRawAxis(1)*.7, cont.getRawAxis(5)*.7);
		SmartDashboard.putNumber("front left current", powerDist.getCurrent(15));
		SmartDashboard.putNumber("front right current", powerDist.getCurrent(1));
		SmartDashboard.putNumber("back left current", powerDist.getCurrent(14));
		SmartDashboard.putNumber("back right current", powerDist.getCurrent(2));
	    SmartDashboard.putNumber("gyro rate", gyrate.getRate());
	    SmartDashboard.putNumber("gyro angle", gyrate.getAngle());
	    SmartDashboard.putNumber("left encoder counts", leftEncoder.get());
	    SmartDashboard.putNumber("right encoder counts", rightEncoder.get());
	    SmartDashboard.putNumber("left encoder distance", leftEncoder.getDistance());
	    SmartDashboard.putNumber("right encoder distance", rightEncoder.getDistance());
		if(LBump.get())
		{
			manipulate.set(-1);
		}
		else if(RBump.get())
		{
			manipulate.set(1);
		}
		else
		{
			manipulate.set(0);
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
	

}
