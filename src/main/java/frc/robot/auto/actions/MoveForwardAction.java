package frc.robot.auto.actions;

import frc.robot.subsystems.Drive;

public class MoveForwardAction{
  Drive mAutoDrive = new Drive();
  
  public void finalMoveForwardACtion(){
    mAutoDrive.outMotoresAuto(0.3, 0.3, -0.3, -0.3);
  }
}