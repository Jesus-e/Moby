package frc.robot.auto.actions;

import frc.robot.subsystems.Drive;

public class TurnAction {
    Drive mAutoDrive = new Drive();

    //1 para girar a la derecha, -1 para girar a la derecha
    public void turnAction(int turnDirection, double speed){
        mAutoDrive.outMotoresAuto(turnDirection * -speed, turnDirection * -speed, turnDirection * -speed, turnDirection * -speed); 
    }
    
    
}
