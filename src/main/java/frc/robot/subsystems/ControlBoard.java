package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlBoard extends SubsystemBase {

  //Hardware ----------------------------------------------------------------->
  public final XboxController mController1 = new XboxController(Constants.Control1Id); //declaración del control dentro del puerto
  public final XboxController mController2 = new XboxController(Constants.Control2Id); 

  //INPUTS ------------------------------------------------------------------>

  //OUTPUTS ----------------------------------------------------------------->

  //Logic ----------------------------------------------------------------->
  int Y_LeftAxis = 1, X_LeftAxis = 2, A_Button = 1;
  
  public ControlBoard(){} //Constructor del subsistema para todas las variables

  //------------------// Funciones del subsistema //-------------------------------//

  //funcion principal de Drive con argumentos de entrada de controles

  public double getYDrive(){
      return mController1.getRawAxis(Constants.LeftYAxis); 
  }

  public double getXDrive(){
    return mController1.getRawAxis(Constants.RightXAxis); 
  }

  public double getTriggers(){
        return mController1.getRightTriggerAxis() - mController1.getLeftTriggerAxis(); 
  }

  public boolean getXButtonDrive(){
    return mController1.getRawButton(Constants.XButton);
  }

  public boolean getAButtonDrive(){
    return mController1.getRawButton(Constants.AButton);
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