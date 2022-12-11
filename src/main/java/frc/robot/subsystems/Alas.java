package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Alas extends SubsystemBase {
  //Hardware ----------------------------------------------------------------->
  public DoubleSolenoid SolenoidRight = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.kAlaRight, Constants.kAlaRight2);

  //Logic -------------------------------------------------------------------------
  public boolean desplegado = false;

  public Alas() {} //constructor del subsistema

  //------------------// Funciones del subsistema //-------------------------------//
 

  public void alaDerecha(boolean bajar){
    if(bajar){
      SolenoidRight.set(Value.kReverse);
    }
    else
      SolenoidRight.set(Value.kForward);
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
