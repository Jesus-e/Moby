package frc.robot.auto.actions;

import frc.robot.subsystems.Drive;

public class MoveAction{
  Drive mAutoDrive = new Drive();
  
  public void finalMoveAction(int moveDirection, double speed){ //1 para enfrente, -1 para atras
    mAutoDrive.outMotoresAuto(moveDirection * speed, moveDirection * speed, moveDirection * -speed, moveDirection * -speed);
  }
}