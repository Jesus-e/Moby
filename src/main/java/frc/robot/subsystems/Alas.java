package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Alas extends SubsystemBase {
  //Hardware ----------------------------------------------------------------->
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
  }

  public void alaDerecha(boolean bajar){
    if(bajar){
      SolenoidRight.set(true);
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
