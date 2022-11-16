package frc.robot.auto.actions;

import frc.robot.subsystems.Drive;

public class Turn {
    Drive mAutoDrive = new Drive();

    //1 para girar a la derecha, -1 para girar a la derecha
    public void turnAction(int turnDirection){
    
        mAutoDrive.outMotoresAuto(turnDirection * -0.3, turnDirection * -0.3, turnDirection * -0.3, turnDirection * -0.3); 

    }
    
    
}
