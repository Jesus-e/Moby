package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {
  //Hardware ----------------------------------------------------------------->
  public final TalonSRX motor1 = new TalonSRX(Constants.kHopper1Id); 
  public final TalonSRX motor2 = new TalonSRX(Constants.kHopper2Id); 
    
  public Hopper() {} //constructor del subsistema

  //------------------// Funciones del subsistema //-------------------------------//

  public void mainHopper(double potencia){ 
    motor1.set(ControlMode.PercentOutput, potencia);
    motor2.set(ControlMode.PercentOutput, -potencia);
  }

  public void autoHopper(double autoSpeed){
    motor1.set(ControlMode.PercentOutput, autoSpeed);
    motor2.set(ControlMode.PercentOutput, -autoSpeed);
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