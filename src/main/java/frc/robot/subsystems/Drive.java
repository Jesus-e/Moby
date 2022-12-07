package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  //Hardware ----------------------------------------------------------------->
  public final TalonSRX mMotor1FrontRight = new TalonSRX(Constants.kDriveRightFrontId); 
  public final TalonSRX mMotor2BackRight = new TalonSRX(Constants.kDriveRightBackId);
  public final TalonSRX mMotor3FrontLeft = new TalonSRX(Constants.kDriveLeftFrontId);
  public final TalonSRX mMotor4BackLeft = new TalonSRX(Constants.kDriveLeftBackId);

  //INPUTS ------------------------------------------------------------------>
  double absMove = 0;  

  double throttle = 0;
  double turn = 0;
  double giroMismoEje = 0;

  double temp_leftPwm = 0;
  double temp_rightPwm = 0;
    
  //OUTPUTS ----------------------------------------------------------------->
  double final_left_front_demand = 0;
  double final_right_front_demand = 0;
  double final_left_back_demand = 0;
  double final_right_back_demand = 0;
    
  //Logic ----------------------------------------------------------------->
  boolean rampActive = true;
  double leftPwm = 0;
  double rightPwm = 0;

  int direction;
  boolean toggleOn = false;
  boolean togglePressed = false;
    
  public Drive() {} //constructor del subsistema

  //------------------// Funciones del subsistema //-------------------------------//
  
  //funcion para restar del que se pasa de 1
  double skim(double v) {
    // gain determines how much to skim off the top
    if (v > 1.0)
      return -((v - 1.0) * Constants.gain);
    else if (v < -1.0)
      return -((v + 1.0) * Constants.gain);
    return 0;
  }

  //el bueno :)
  //fe
  public void mainDrive(double throttle, double turn, boolean turnButton, double inDirectThrottle, boolean frontChange){
    updateToggle(frontChange);

    if(toggleOn){
        direction = -1;
    }else{
        direction = 1;
    }
    
    //si no estas picando el boton de girar en tu eje, multiplica el giro por la velocidad para ajustarlo
    //si si lo estas picando no lo hace porque al multiplicar por 0 no giraría en su lugar
    temp_leftPwm = direction * (throttle - turn);
    temp_rightPwm = direction * (throttle + turn);
    absMove = direction * (inDirectThrottle);
    
    if (!turnButton){
    turn = turn * (Constants.another_gain * Math.abs(throttle));
  }
    
    if(absMove != 0){ //funcion que implementa la rampa
      final_right_front_demand = speedTramp(absMove, final_right_front_demand);
      final_right_back_demand = speedTramp(absMove, final_right_back_demand);
      final_left_front_demand = speedTramp(-absMove, final_left_front_demand);
      final_left_back_demand = speedTramp(-absMove, final_left_back_demand);     
    }
    else{
      leftPwm = temp_leftPwm + skim(temp_rightPwm);
      rightPwm = temp_rightPwm + skim(temp_leftPwm);

      //rampa de velocidad
      final_right_front_demand = speedTramp(rightPwm, final_right_front_demand);
      final_right_back_demand = speedTramp(rightPwm, final_right_back_demand);
      final_left_front_demand = speedTramp(-leftPwm, final_left_front_demand);
      final_left_back_demand = speedTramp(-leftPwm, final_left_back_demand); 
    }  

    outMotores();
  }
  

  //Funcion que le da salida de motores
  private void outMotores(){
    mMotor1FrontRight.set(ControlMode.PercentOutput, final_right_front_demand);
    mMotor2BackRight.set(ControlMode.PercentOutput, final_right_back_demand);
    mMotor3FrontLeft.set(ControlMode.PercentOutput, final_left_front_demand);
    mMotor4BackLeft.set(ControlMode.PercentOutput, final_left_back_demand);
  }

  //Funcion para la rampa de velocidad que toma argumentos de velocidad actual y la velocidad que da el control
  private double speedTramp( double targetSpeed, double currentSpeed ){
    if( Math.abs( (Math.abs(targetSpeed) - Math.abs(currentSpeed) ) ) < Constants.kDriveRampDeltaSpeed) return targetSpeed;
    if( currentSpeed < targetSpeed ) return currentSpeed + Constants.kDriveRampDeltaSpeed;
    else if( currentSpeed > targetSpeed ) return currentSpeed - Constants.kDriveRampDeltaSpeed;
    return 0;
  } 
   
  //Funcion para poner salidas a SmartDashBoard 
  public void DriveLogsOutput(){
    SmartDashboard.putNumber("Direct Throttle", absMove);
  }

  //Funcion para actualizar el toggle
  public void updateToggle(boolean joystick)
  {
      if(joystick){
          if(!togglePressed){
              toggleOn = !toggleOn;
              togglePressed = true;
          }
      }else{
          togglePressed = false;
      }
  }

  //Funcion mover motores en autonomo
  public void outMotoresAuto(double v1, double v2, double v3, double v4){
    mMotor1FrontRight.set(ControlMode.PercentOutput, v1);
    mMotor2BackRight.set(ControlMode.PercentOutput, v2);
    mMotor3FrontLeft.set(ControlMode.PercentOutput, v3);
    mMotor4BackLeft.set(ControlMode.PercentOutput, v4);
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
