package frc.robot.auto.actions;

import frc.robot.subsystems.Intake;

public class IntakeAction{
  Intake mAutoIntake = new Intake();
  
  public void autoIntakeAction(double speed){ 
    mAutoIntake.autoIntake(speed);
  }
}