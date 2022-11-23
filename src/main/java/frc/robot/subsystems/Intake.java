package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  //Hardware ----------------------------------------------------------------->
  public final TalonSRX motorIntake = new TalonSRX(Constants.kIntakeId); 
    
  public Intake() {} //constructor del subsistema

  //------------------// Funciones del subsistema //-------------------------------//

  public void mainIntake(boolean active){ 
    if(active){ 
        motorIntake.set(ControlMode.PercentOutput, Constants.kIntakeDemand);
    }
    else{
        motorIntake.set(ControlMode.PercentOutput, 0);
    } 
  }

  public void autoIntake(double autoSpeed){
    motorIntake.set(ControlMode.PercentOutput, autoSpeed);
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