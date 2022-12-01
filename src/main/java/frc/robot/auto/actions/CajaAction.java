package frc.robot.auto.actions;

import frc.robot.subsystems.Caja;

public class CajaAction{
  Caja mAutoCaja = new Caja();
  
  public void autoCajaAction(double speed){ 
    mAutoCaja.autoCaja(speed);
  }
}