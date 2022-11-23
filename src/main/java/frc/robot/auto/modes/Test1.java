package frc.robot.auto.modes;

import frc.robot.auto.actions.GetTimeAction;
import frc.robot.auto.actions.MoveAction;
import frc.robot.auto.actions.StopAction;
import frc.robot.subsystems.Drive;

public class Test1{
  Drive mAutoDrive = new Drive();
  MoveAction mMoveAction = new MoveAction();
  StopAction mStopAction = new StopAction();
  GetTimeAction mGetTimeAction = new GetTimeAction();
  
  public void move3Secs(){
    if(mGetTimeAction.getAbsoluteTimer()-mGetTimeAction.getRelativeTimer()<3){
        mMoveAction.finalMoveAction(1, 0.3); //para adelante, 0.3 speed
      }
      else mStopAction.finalStopAction();
  }
}