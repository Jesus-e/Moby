package frc.robot.auto.modes;

import frc.robot.auto.actions.GetTimeAction;
import frc.robot.auto.actions.MoveForwardAction;
import frc.robot.auto.actions.StopAction;
import frc.robot.subsystems.Drive;

public class Test1{
  Drive mAutoDrive = new Drive();
  MoveForwardAction mForwardAction = new MoveForwardAction();
  StopAction mStopAction = new StopAction();
  GetTimeAction mGetTimeAction = new GetTimeAction();
  
  public void move3Secs(){
    if(mGetTimeAction.getAbsoluteTimer()-mGetTimeAction.getRelativeTimer()<3){
        mForwardAction.finalMoveForwardACtion();
      }
      else mStopAction.finalStopAction();
  }
}