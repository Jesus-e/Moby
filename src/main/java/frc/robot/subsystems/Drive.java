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
  double xSpeed = 0;
  double ySpeed = 0;
  double absMove = 0;  
    
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

  //funcion principal de Drive con argumentos de entrada de controles
  public void mainDrive(double xInSpeed, double yInSpeed, double inDirectThrottle, int direction, boolean frontChange){

    updateToggle(frontChange);

    if(toggleOn){
        direction = -1;
    }else{
        direction = 1;
    }

    xSpeed = xInSpeed;
    ySpeed = yInSpeed;
    absMove = inDirectThrottle*Constants.kDriveSensitivity; //valor de absMove con sensibilidad del control

    if(xSpeed>=0){
      leftPwm = ((xSpeed) - ySpeed)*Constants.kDriveSensitivity; //sensibilidad del control agregada
      rightPwm = ((xSpeed) + ySpeed)*Constants.kDriveSensitivity;
    }
    else{
      leftPwm = ((xSpeed) + ySpeed)*Constants.kDriveSensitivity;
      rightPwm = ((xSpeed) - ySpeed)*Constants.kDriveSensitivity;
    }

    if(absMove != 0){ //funcion que implementa la rampa
      final_right_front_demand = speedTramp(absMove, final_right_front_demand);
      final_right_back_demand = speedTramp(absMove, final_right_back_demand);
      final_left_front_demand = speedTramp(-absMove, final_left_front_demand);
      final_left_back_demand = speedTramp(-absMove, final_left_back_demand);     
    }
    else{
      final_right_front_demand = direction * speedTramp(rightPwm, final_right_front_demand);
      final_right_back_demand = direction * speedTramp(rightPwm, final_right_back_demand);
      final_left_front_demand = direction * speedTramp(-leftPwm, final_left_front_demand);
      final_left_back_demand = direction * speedTramp(-leftPwm, final_left_back_demand);
    }

    outMotores(); //llamado de la funcion de salida de motores
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

  //----------------------------
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


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
