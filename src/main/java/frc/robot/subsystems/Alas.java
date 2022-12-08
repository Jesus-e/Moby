package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Alas extends SubsystemBase {
  //Hardware ----------------------------------------------------------------->
  public VictorSPX motorRegreso = new VictorSPX(Constants.kMotorAla);
  public Solenoid SolenoidLeft = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.kAlaLeft);
  public Solenoid SolenoidRight = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.kAlaRight);

  //Logic -------------------------------------------------------------------------
  public boolean desplegado = false;

  public Alas() {} //constructor del subsistema

  //------------------// Funciones del subsistema //-------------------------------//

  public void bajarAlas(boolean desplegar){ 
    if(desplegar){
        desplegado = true;
    }
    if (desplegado){
        SolenoidLeft.set(true);
        SolenoidRight.set(true);
    }
  }

  public void alaIzquierda(boolean bajar){
    if(bajar){
      SolenoidLeft.set(true);
    }
    else     
      SolenoidLeft.set(false);
  }

  public void alaDerecha(boolean bajar){
    if(bajar){
      SolenoidRight.set(true);
    }
    else
      SolenoidRight.set(false);
  }

  public void subirAlas(double potencia){
    motorRegreso.set(VictorSPXControlMode.PercentOutput, potencia);
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
