package frc.robot.auto.actions;

import frc.robot.subsystems.Hopper;

public class HopperAction{
  Hopper mAutoHopper = new Hopper();
  
  public void autoCajaAction(double speed){ 
    mAutoHopper.autoHopper(speed);
  }
}