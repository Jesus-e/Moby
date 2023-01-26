// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  Compressor compresor = new Compressor(1, PneumaticsModuleType.REVPH);
  XboxController controld = new XboxController(0);
  TalonFX Motor1 = new TalonFX(1);
  TalonFX Motor2 = new TalonFX(2);
  TalonFX Motor3 = new TalonFX(3);
  TalonFX Motor4 = new TalonFX(4);
  TalonFX Motor5 = new TalonFX(5);
  TalonFX Motor6 = new TalonFX(6);
  TalonFX Motor7 = new TalonFX(7);
  TalonFX Motor8 = new TalonFX(8);
  CANSparkMax Neo1= new CANSparkMax(12, MotorType.kBrushless);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    compresor.disable();
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

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
    Motor1.set(ControlMode.PercentOutput, controld.getRawAxis(0));
    Motor2.set(ControlMode.PercentOutput, controld.getRawAxis(0));
    Motor3.set(ControlMode.PercentOutput, controld.getRawAxis(0));
    Motor4.set(ControlMode.PercentOutput, controld.getRawAxis(0));
    Motor5.set(ControlMode.PercentOutput, controld.getRawAxis(0));
    Motor6.set(ControlMode.PercentOutput, controld.getRawAxis(0));
    Motor7.set(ControlMode.PercentOutput, controld.getRawAxis(0));
    Motor8.set(ControlMode.PercentOutput, controld.getRawAxis(0));

    Neo1.set(controld.getRawAxis(2));


    TalonUtil.checkError(Motor1.configStatorCurrentLimit(
      new StatorCurrentLimitConfiguration(
        true, Constants.kCurrentlimit, Constants.ktriggerThresholdCurrent, Constants.ktriggerThresholdTime
      ), Constants.ktimeoutMs
      ), "could not set drive current limits");
    TalonUtil.checkError(Motor2.configStatorCurrentLimit(
      new StatorCurrentLimitConfiguration(
        true, Constants.kCurrentlimit, Constants.ktriggerThresholdCurrent, Constants.ktriggerThresholdTime
      ), Constants.ktimeoutMs
      ), "could not set drive current limits");
    TalonUtil.checkError(Motor3.configStatorCurrentLimit(
      new StatorCurrentLimitConfiguration(
        true, Constants.kCurrentlimit, Constants.ktriggerThresholdCurrent, Constants.ktriggerThresholdTime
      ), Constants.ktimeoutMs
      ), "could not set drive current limits");
    TalonUtil.checkError(Motor4.configStatorCurrentLimit(
      new StatorCurrentLimitConfiguration(
        true, Constants.kCurrentlimit, Constants.ktriggerThresholdCurrent, Constants.ktriggerThresholdTime
      ), Constants.ktimeoutMs
      ), "could not set drive current limits");
    TalonUtil.checkError(Motor5.configStatorCurrentLimit(
      new StatorCurrentLimitConfiguration(
        true, Constants.kCurrentlimit, Constants.ktriggerThresholdCurrent, Constants.ktriggerThresholdTime
      ), Constants.ktimeoutMs
      ), "could not set drive current limits");
    TalonUtil.checkError(Motor6.configStatorCurrentLimit(
        new StatorCurrentLimitConfiguration(
          true, Constants.kCurrentlimit, Constants.ktriggerThresholdCurrent, Constants.ktriggerThresholdTime
        ), Constants.ktimeoutMs
        ), "could not set drive current limits");
    TalonUtil.checkError(Motor7.configStatorCurrentLimit(
      new StatorCurrentLimitConfiguration(
        true, Constants.kCurrentlimit, Constants.ktriggerThresholdCurrent, Constants.ktriggerThresholdTime
      ), Constants.ktimeoutMs
      ), "could not set drive current limits");
      TalonUtil.checkError(Motor8.configStatorCurrentLimit(
        new StatorCurrentLimitConfiguration(
          true, Constants.kCurrentlimit, Constants.ktriggerThresholdCurrent, Constants.ktriggerThresholdTime
        ), Constants.ktimeoutMs
        ), "could not set drive current limits");

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
