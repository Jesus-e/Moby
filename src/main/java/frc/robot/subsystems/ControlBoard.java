package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlBoard extends SubsystemBase {

  //Hardware ----------------------------------------------------------------->
  public final XboxController mController1 = new XboxController(Constants.Control1Id); //manejador declaración del control dentro del puerto
  public final XboxController mController2 = new XboxController(Constants.Control2Id); //mecanismos

  //INPUTS ------------------------------------------------------------------>

  //OUTPUTS ----------------------------------------------------------------->

  //Logic ----------------------------------------------------------------->
  int Y_LeftAxis = 1, X_LeftAxis = 2, A_Button = 1;
  
  public ControlBoard(){} //Constructor del subsistema para todas las variables

  //------------------// Funciones del subsistema //-------------------------------//

  //funcion principal de Drive con argumentos de entrada de controles

  //driver driver
  public double getYDrive(){
      return mController1.getRawAxis(Constants.LeftYAxis); 
  }

  public double getXLeftDrive(){
    return mController1.getRawAxis(Constants.LeftXAxis); 
}

  public double getXDrive(){
    return mController1.getRawAxis(Constants.RightXAxis); 
  }

  public double getTriggersAtom(){
    return mController1.getRawAxis(Constants.RightTrigger) - mController1.getRawAxis(Constants.LeftTrigger); 
}

public boolean getRBDrive(){
  return mController1.getRawButton(Constants.RB);
}

  public double getTriggers(){
        return mController1.getRawAxis(Constants.LeftTrigger) - mController1.getRawAxis(Constants.RightTrigger); 
  }

  public boolean getXButtonDrive(){
    return mController1.getRawButton(Constants.XButton);
  }

  public boolean getAButtonDrive(){
    return mController1.getRawButton(Constants.AButton);
  }

  public boolean getBButtonDrive(){
    return mController1.getRawButton(Constants.BButton);
  }

  //mecanismos
  public double getYLeftMecanismos(){
    return mController2.getRawAxis(Constants.LeftYAxis); 
}

public double getYRightMecanismos(){
  return mController2.getRawAxis(Constants.RightYAxis); 
}

public double getRightTriggerMecanismos(){
  return mController2.getRawAxis(Constants.RightTrigger); 
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}