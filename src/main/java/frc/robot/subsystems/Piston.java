package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Piston extends SubsystemBase {
  //Hardware ----------------------------------------------------------------->
  public DoubleSolenoid Solenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.kPiston1ForwardId, Constants.kPiston1ReverseId);
  public DoubleSolenoid Solenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.kPiston2ForwardId, Constants.kPiston2ReverseId);

  public Piston() {} //constructor del subsistema

  //------------------// Funciones del subsistema //-------------------------------//

  public void mainPiston(boolean active){ 
    if(active){
      Solenoid1.set(Value.kForward);
      Solenoid2.set(Value.kForward);
    }
    else{
      Solenoid1.set(Value.kReverse);
      Solenoid2.set(Value.kReverse);
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
