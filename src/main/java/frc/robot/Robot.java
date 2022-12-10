// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.auto.actions.CajaAction;
import frc.robot.auto.actions.GetTimeAction;
import frc.robot.auto.actions.HopperAction;
import frc.robot.auto.actions.IntakeAction;
import frc.robot.auto.actions.MoveAction;
import frc.robot.auto.actions.StopAction;
import frc.robot.auto.actions.TurnAction;
import frc.robot.auto.modes.Test1;
import frc.robot.subsystems.Alas;
import frc.robot.subsystems.ControlBoard;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Piston;
import frc.robot.subsystems.Caja;
import frc.robot.subsystems.Hopper;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  //Subsistemas
  Compressor mCompressor = new Compressor(PneumaticsModuleType.CTREPCM);
  ControlBoard mControlBoard = new ControlBoard();
  Drive mDrive = new Drive();
  Intake mIntake = new Intake();
  Piston mPiston = new Piston();
  Alas mAlas = new Alas();
  Caja mCaja = new Caja();
  Hopper mHopper = new Hopper();
  private RobotContainer m_robotContainer;

 //Autonomo
 SendableChooser<String> auto = new SendableChooser<String>();
 String autoMode = auto.getSelected();
  GetTimeAction mAutoTimer = new GetTimeAction();
  MoveAction mMoveAction = new MoveAction();
  StopAction mStopAction = new StopAction();
  Test1 mTest1Mode = new Test1();
  TurnAction mTurnAction = new TurnAction();
  CajaAction mCajaAction = new CajaAction();
  IntakeAction mIntakeAction = new IntakeAction();
  HopperAction mHopperAction = new HopperAction();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    auto.addOption("autoAzul", "autoAzul");
    auto.addOption("autoAmarillo", "autoAmarillo");
    auto.addOption("auto3/5_Azul", "auto3/5_Azul");
    auto.addOption("auto3/5_Amarillo", "auto3/5_Amarillo");
    auto.addOption("soloSal", "soloSal");
    SmartDashboard.putData("Auto Mode", auto);

    m_robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture();
    mPiston.Solenoid1.set(Value.kReverse);
    mPiston.Solenoid2.set(Value.kReverse);

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
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

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
    double diferencia = mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer();
  
  switch (autoMode){
    case "autoAzul":
    if(diferencia<2.2){ //avanza
      mMoveAction.finalMoveAction(-1, 0.3);
    }
    else if(diferencia>2.2 && diferencia<2.6){ //come la pelota
    mMoveAction.finalMoveAction(-1, 0.3);
    mIntakeAction.autoIntakeAction(1);
    }
    else if(diferencia>2.6 && diferencia<2.7){ 
    mStopAction.finalStopAction(); 
    }
    else if(diferencia>2.7 && diferencia<3.7){ //gira 180
        mTurnAction.turnAction(-1, 0.5);
    }
    else if(diferencia>3.7 && diferencia<3.9){ 
        mStopAction.finalStopAction(); 
        mCajaAction.autoCajaAction(0.4);
    }
    else if(diferencia>3.9 && diferencia<4.8){ //avanza hasta la caja (ayer estaba en 4.5s por si hay que bajarle)
      mMoveAction.finalMoveAction(1, 0.3);
      mCajaAction.autoCajaAction(0.4);
    }
    else if(diferencia>4.8 && diferencia<4.9){ 
      mStopAction.finalStopAction();
      mCajaAction.autoCajaAction(0.4);
    }
    else if(diferencia>4.9 && diferencia<5.4){ //gira 90 hacia la zona de carga
      mTurnAction.turnAction(-1, 0.5);
      mCajaAction.autoCajaAction(0.4);
    }
    else if(diferencia>5.4 && diferencia<5.5){ 
        mStopAction.finalStopAction();
        mCajaAction.autoCajaAction(0.4);
      }
    else if(diferencia>5.5 && diferencia<8.1){  //avanza hasta la zona de carga (ayer estaba en 6s)
        mMoveAction.finalMoveAction(1, 0.3);
        mCajaAction.autoCajaAction(0.4);
    }
    //hasta aqui funcionaba ayer
    else if(diferencia>8.1 && diferencia<9.1){ //llega y activa el hopper
        mStopAction.finalStopAction();
        mHopperAction.autoHopperAction(1);
      }
    else if(diferencia>9.1 && diferencia<9.3){  //saca la caja por si de milagro la agarro bien
        mCajaAction.autoCajaAction(-0.4);
        mHopperAction.autoHopperAction(0);
      }
    //hasta aqui medianamente probable que funcione, muy poco probable que mate a alguien
    else if (diferencia>9.3 && diferencia<9.5){ //retrocede poquito pa no pegarle a la caja
      mMoveAction.finalMoveAction(-1, 0.3);
     }
    else if (diferencia>9.5 && diferencia<9.6){ 
      mStopAction.finalStopAction();
    }
    else if (diferencia>9.5 && diferencia<10.1){ //gira 90 para quedar viendo hacia las pelotas
      mTurnAction.turnAction(1, 0.3);
    }
    else if (diferencia>10.1 && diferencia<12.2){ //avanza hacia las pelotas
      mMoveAction.finalMoveAction(-1, 0.3);
     }
    else if (diferencia>12.2 && diferencia<12.6){ //activa el intake mientras llega a las pelotas
      mMoveAction.finalMoveAction(-1, 0.3);
      mIntakeAction.autoIntakeAction(1);   }
    else if (diferencia>12.6 && diferencia<12.7){
      mStopAction.finalStopAction();
      mIntakeAction.autoIntakeAction(0);
    }
    else if (diferencia>12.7 && diferencia<14.9){ //se va pa tras porque confio mucho en lo que se desvia avanzando y va soltando la pelota porque el hopper es bien lento
      mMoveAction.finalMoveAction(1, 0.41);
      mHopperAction.autoHopperAction(1);   
    }
    else{ //se apaga todo pa no matar gente
      mStopAction.finalStopAction();
      mIntakeAction.autoIntakeAction(0); 
      mHopperAction.autoHopperAction(0); 
    }
    break;
  
  case "autoAmarillo":
  if(diferencia<2.4){ //avanza
    mMoveAction.finalMoveAction(-1, 0.3);
  }
  else if(diferencia>2.4 && diferencia<2.85){ //come la pelota
  mMoveAction.finalMoveAction(-1, 0.3);
  mIntakeAction.autoIntakeAction(1);
  }
  else if(diferencia>2.85 && diferencia<2.9){ 
  mStopAction.finalStopAction(); 
  }
  else if(diferencia>2.9 && diferencia<3.9){ //gira 180
      mTurnAction.turnAction(-1, 0.5);
  }
  else if(diferencia>3.9 && diferencia<4){ 
      mStopAction.finalStopAction(); 
      mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>4 && diferencia<4.9){ //avanza hasta la caja (ayer estaba en 4.5s por si hay que bajarle)
    mMoveAction.finalMoveAction(1, 0.3);
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>4.9 && diferencia<5){ 
    mStopAction.finalStopAction();
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>5 && diferencia<5.5){ //gira 90 hacia la zona de carga
    mTurnAction.turnAction(-1, 0.5);
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>5.5 && diferencia<5.6){ 
      mStopAction.finalStopAction();
      mCajaAction.autoCajaAction(0.4);
    }
  else if(diferencia>5.6 && diferencia<8.1){  //avanza hasta la zona de carga (ayer estaba en 6s)
      mMoveAction.finalMoveAction(1, 0.3);
      mCajaAction.autoCajaAction(0.4);
  }
  //hasta aqui funcionaba ayer
  else if(diferencia>8.1 && diferencia<9.1){ //llega y activa el hopper
      mStopAction.finalStopAction();
      mHopperAction.autoHopperAction(1);
    }
  else if(diferencia>9.1 && diferencia<9.3){  //saca la caja por si de milagro la agarro bien
      mCajaAction.autoCajaAction(-0.4);
      mHopperAction.autoHopperAction(0);
    }
  //hasta aqui medianamente probable que funcione, muy poco probable que mate a alguien
  else if (diferencia>9.3 && diferencia<9.5){ //retrocede poquito pa no pegarle a la caja
    mMoveAction.finalMoveAction(-1, 0.3);
   }
  else if (diferencia>9.5 && diferencia<9.6){ 
    mStopAction.finalStopAction();
  }
  else if (diferencia>9.5 && diferencia<10.1){ //gira 90 para quedar viendo hacia las pelotas
    mTurnAction.turnAction(1, 0.3);
  }
  else if (diferencia>10.1 && diferencia<12.2){ //avanza hacia las pelotas
    mMoveAction.finalMoveAction(-1, 0.3);
   }
  else if (diferencia>12.2 && diferencia<12.6){ //activa el intake mientras llega a las pelotas
    mMoveAction.finalMoveAction(-1, 0.3);
    mIntakeAction.autoIntakeAction(1);   }
  else if (diferencia>12.6 && diferencia<12.7){
    mStopAction.finalStopAction();
    mIntakeAction.autoIntakeAction(0);
  }
  else if (diferencia>12.7 && diferencia<14.9){ //se va pa tras porque confio mucho en lo que se desvia avanzando y va soltando la pelota porque el hopper es bien lento
    mMoveAction.finalMoveAction(1, 0.41);
    mHopperAction.autoHopperAction(1);   
  }
  else{ //se apaga todo pa no matar gente
    mStopAction.finalStopAction();
    mIntakeAction.autoIntakeAction(0); 
    mHopperAction.autoHopperAction(0); 
  }
  break;

  case "soloSal":
  if(diferencia<1.6){ //avanza
    mMoveAction.finalMoveAction(-1, 0.3);
  }
  else{
    mStopAction.finalStopAction();
  }
  break;
 
  case "auto3/5_Azul":
  if(diferencia<2.2){ //avanza
    mMoveAction.finalMoveAction(-1, 0.3);
  }
  else if(diferencia>2.2 && diferencia<2.6){ //come la pelota
  mMoveAction.finalMoveAction(-1, 0.3);
  mIntakeAction.autoIntakeAction(1);
  }
  else if(diferencia>2.6 && diferencia<2.7){ 
  mStopAction.finalStopAction(); 
  }
  else if(diferencia>2.7 && diferencia<3.7){ //gira 180
      mTurnAction.turnAction(-1, 0.5);
  }
  else if(diferencia>3.7 && diferencia<3.9){ 
      mStopAction.finalStopAction(); 
      mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>3.9 && diferencia<4.8){ //avanza hasta la caja (ayer estaba en 4.5s por si hay que bajarle)
    mMoveAction.finalMoveAction(1, 0.3);
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>4.8 && diferencia<4.9){ 
    mStopAction.finalStopAction();
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>4.9 && diferencia<5.4){ //gira 90 hacia la zona de carga
    mTurnAction.turnAction(-1, 0.5);
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>5.4 && diferencia<5.5){ 
      mStopAction.finalStopAction();
      mCajaAction.autoCajaAction(0.4);
    }
  else if(diferencia>5.5 && diferencia<8.1){  //avanza hasta la zona de carga (ayer estaba en 6s)
      mMoveAction.finalMoveAction(1, 0.3);
      mCajaAction.autoCajaAction(0.4);
  }
  //hasta aqui funcionaba ayer
  else if(diferencia>8.1 && diferencia<9.1){ //llega y activa el hopper
      mStopAction.finalStopAction();
      mHopperAction.autoHopperAction(1);
    }
  else if(diferencia>9.1 && diferencia<9.3){  //saca la caja por si de milagro la agarro bien
      mCajaAction.autoCajaAction(-0.4);
      mHopperAction.autoHopperAction(0);
    }
  //hasta aqui medianamente probable que funcione, muy poco probable que mate a alguien
  else if (diferencia>9.3 && diferencia<9.5){ //retrocede poquito pa no pegarle a la caja
    mMoveAction.finalMoveAction(-1, 0.3);
   }
  else{ 
    mStopAction.finalStopAction();
  }
  break;

  case "auto3/5_Amarillo":
  if(diferencia<2.4){ //avanza
    mMoveAction.finalMoveAction(-1, 0.3);
  }
  else if(diferencia>2.4 && diferencia<2.85){ //come la pelota
  mMoveAction.finalMoveAction(-1, 0.3);
  mIntakeAction.autoIntakeAction(1);
  }
  else if(diferencia>2.85 && diferencia<2.9){ 
  mStopAction.finalStopAction(); 
  }
  else if(diferencia>2.9 && diferencia<3.9){ //gira 180
      mTurnAction.turnAction(-1, 0.5);
  }
  else if(diferencia>3.9 && diferencia<4){ 
      mStopAction.finalStopAction(); 
      mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>4 && diferencia<4.9){ //avanza hasta la caja (ayer estaba en 4.5s por si hay que bajarle)
    mMoveAction.finalMoveAction(1, 0.3);
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>4.9 && diferencia<5){ 
    mStopAction.finalStopAction();
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>5 && diferencia<5.5){ //gira 90 hacia la zona de carga
    mTurnAction.turnAction(-1, 0.5);
    mCajaAction.autoCajaAction(0.4);
  }
  else if(diferencia>5.5 && diferencia<5.6){ 
      mStopAction.finalStopAction();
      mCajaAction.autoCajaAction(0.4);
    }
  else if(diferencia>5.6 && diferencia<8.1){  //avanza hasta la zona de carga (ayer estaba en 6s)
      mMoveAction.finalMoveAction(1, 0.3);
      mCajaAction.autoCajaAction(0.4);
  }
  //hasta aqui funcionaba ayer
  else if(diferencia>8.1 && diferencia<9.1){ //llega y activa el hopper
      mStopAction.finalStopAction();
      mHopperAction.autoHopperAction(1);
    }
  else if(diferencia>9.1 && diferencia<9.3){  //saca la caja por si de milagro la agarro bien
      mCajaAction.autoCajaAction(-0.4);
      mHopperAction.autoHopperAction(0);
    }
  //hasta aqui medianamente probable que funcione, muy poco probable que mate a alguien
  else if (diferencia>9.3 && diferencia<9.5){ //retrocede poquito pa no pegarle a la caja
    mMoveAction.finalMoveAction(-1, 0.3);
   }
  else{ 
    mStopAction.finalStopAction();
  }
  break;
  }
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
    mCompressor.enableDigital();
    boolean enabled = mCompressor.enabled(); //revisar estado de compresor
    boolean pressureSwitch = mCompressor.getPressureSwitchValue(); 

    //probar funciones de drive, comentar las que no se esten probando
    mDrive.mainDrive(-mControlBoard.getYDrive(), mControlBoard.getXDrive(), mControlBoard.getRBDrive(), mControlBoard.getTriggersAtom(), mControlBoard.getXButtonDrive()); //avanzas y giras con los sticks, si quieres girar en tu eje pica A
    mDrive.DriveLogsOutput();
    
    mIntake.mainIntake(mControlBoard.getTriggersMecanismos());
    //mAlas.bajarAlas(mControlBoard.getBButtonDrive());
    mCaja.mainCaja(mControlBoard.getYLeftMecanismos());
    mPiston.mainPiston(mControlBoard.getXButtonMecanismos());
    mAlas.alaIzquierda(mControlBoard.getBButtonMecanismos());
    mAlas.alaDerecha(mControlBoard.getYButtonMecanismos());
    mAlas.subirAlas(mControlBoard.getLeftTriggerMecanismos());
    //mCajaAction.autoCajaAction(0.3);
    mHopper.mainHopper(mControlBoard.getYRightMecanismos());

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
