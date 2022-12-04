package frc.robot.auto.actions;

import frc.robot.subsystems.Drive;

public class MoveAction{
  Drive mAutoDrive = new Drive();
  
  public void finalMoveAction(int moveDirection, double speed){ //1 para caja, -1 para pelota
    mAutoDrive.outMotoresAuto(moveDirection * speed, moveDirection * speed, moveDirection * -speed, moveDirection * -speed);
  }
}