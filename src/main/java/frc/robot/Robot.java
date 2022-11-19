// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.auto.actions.GetTimeAction;
import frc.robot.auto.actions.MoveForwardAction;
import frc.robot.auto.actions.StopAction;
import frc.robot.auto.actions.Turn;
import frc.robot.auto.modes.Test1;
import frc.robot.subsystems.ControlBoard;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Piston;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  //Subsistemas
  ControlBoard mControlBoard = new ControlBoard();
  Drive mDrive = new Drive();
  Intake mIntake = new Intake();
  Piston mPiston = new Piston();
  private RobotContainer m_robotContainer;

 //Autonomo
  GetTimeAction mAutoTimer = new GetTimeAction();
  MoveForwardAction mForwardAction = new MoveForwardAction();
  StopAction mStopAction = new StopAction();
  Test1 mTest1Mode = new Test1();
  Turn mTurn = new Turn();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

   // schedule the autonomous command (example)
   /*  if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }  */

    mAutoTimer.autoRelativeTimeControl(); //inicializar el timeStap relativo a auto

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
     mAutoTimer.autoAbsoluteTimeControl(); //inicializa el timeStap absoluto
    if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<3){
      mForwardAction.finalMoveForwardACtion();
    }
    else if (mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<3*2) {
      mTurn.turnAction(-1); //esto va pa la izquierda 
    }
    mStopAction.finalStopAction(); 
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    mDrive.mainDrive(mControlBoard.getYDrive(), mControlBoard.getXDrive(), mControlBoard.getTriggers(), mControlBoard.getXButtonDrive());
    mIntake.mainIntake(mControlBoard.getAButtonDrive());
    mPiston.mainPiston(mControlBoard.getBButtonDrive());

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
