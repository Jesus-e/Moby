package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Piston extends SubsystemBase {
  //Hardware ----------------------------------------------------------------->
  public Solenoid Solenoid1 = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.kPistonId);

  public Piston() {} //constructor del subsistema

  //------------------// Funciones del subsistema //-------------------------------//

  public void mainPiston(boolean active){ 
    if(active){
      Solenoid1.set(true);
    }
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
