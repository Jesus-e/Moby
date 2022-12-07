package frc.robot.auto.modes;

import frc.robot.auto.actions.CajaAction;
import frc.robot.auto.actions.GetTimeAction;
import frc.robot.auto.actions.HopperAction;
import frc.robot.auto.actions.IntakeAction;
import frc.robot.auto.actions.MoveAction;
import frc.robot.auto.actions.StopAction;
import frc.robot.auto.actions.TurnAction;
import frc.robot.subsystems.Drive;

public class PruebaGiro{
  Drive mAutoDrive = new Drive();
  MoveAction mMoveAction = new MoveAction();
  StopAction mStopAction = new StopAction();
  CajaAction mCajaAction = new CajaAction();
  TurnAction mTurnAction = new TurnAction();
  GetTimeAction mAutoTimer = new GetTimeAction();
  IntakeAction mIntakeAction = new IntakeAction();
  HopperAction mHopperAction = new HopperAction();
  
  public void pruebaGiro(){
    if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<2.2){
        mMoveAction.finalMoveAction(-1, 0.3);
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<2.3){
    mMoveAction.finalMoveAction(-1, 0.3);
    mIntakeAction.autoIntakeAction(0.4);
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<2.5){ 
    mStopAction.finalStopAction(); 
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<3){
        mTurnAction.turnAction(-1, 0.3);
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<3.2){ 
        mStopAction.finalStopAction(); 
        mCajaAction.autoCajaAction(0.4);
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<4){ 
      mMoveAction.finalMoveAction(1, 0.3);
      mCajaAction.autoCajaAction(0.4);
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<4.2){ 
      mStopAction.finalStopAction();
      mCajaAction.autoCajaAction(0.4);
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<4.9){ 
      mTurnAction.turnAction(1, 0.3);
      mCajaAction.autoCajaAction(0.4);
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<5.1){ 
        mStopAction.finalStopAction();
        mCajaAction.autoCajaAction(0.4);
      }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<5.6){ 
        mMoveAction.finalMoveAction(1, 0.3);
        mCajaAction.autoCajaAction(0.4);
    }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<6){ 
        mStopAction.finalStopAction();
        mHopperAction.autoHopperAction(0.3);
      }
    else if(mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer()<6.3){ 
        mHopperAction.autoHopperAction(0);
        mMoveAction.finalMoveAction(-1, 0.3);
        mCajaAction.autoCajaAction(-0.4);
      }
    else{
      mStopAction.finalStopAction();
      mCajaAction.autoCajaAction(0);
    }
}
}