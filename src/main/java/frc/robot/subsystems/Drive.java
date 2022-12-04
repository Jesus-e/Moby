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
  double leftPow = 0;
  double rightPow = 0;
  double max = 0;

  int direction;
  boolean toggleOn = false;
  boolean togglePressed = false;
    
  public Drive() {} //constructor del subsistema

  //------------------// Funciones del subsistema //-------------------------------//

  //funcion principal de Drive con argumentos de entrada de controles
  public void mainDrive(double xInSpeed, double yInSpeed, double inDirectThrottle, boolean frontChange){
    xSpeed = xInSpeed; //raw axis 4, el movimiento horizontal del joystick derecho
    ySpeed = -yInSpeed; //raw axis 1, el movimiento vertical del joystick izquierdo, invertirle el signo porque adelante en el control por alguna razón es -1
    absMove = inDirectThrottle*Constants.kDriveSensitivity; //diferencia de los triggers, derecho avanza, izquierdo retrocede
    
    //checar cual (si x o y) valor es mayor
    max = Math.abs(ySpeed);
    if(Math.abs(xSpeed) > max){
      max = Math.abs(xSpeed);
    }

    //actualizar el frente cada que le piques al boton
    updateToggle(frontChange);
    if(toggleOn){
        direction = -1;
    }else{
        direction = 1;
    }

    //actualizar la direccion de acuerdo a cual es el frente
    xSpeed = direction * xSpeed;
    ySpeed = direction * ySpeed;

    //fe
    //checa si la velocidad en y es mayor a 0, quieres ir "adelante"
    if(ySpeed >= 0){
      //checha si quieres girar "a la derecha", en sentido horario
      if(xSpeed >= 0){
        rightPow = ((ySpeed) - (xSpeed))*Constants.kDriveSensitivity;
        leftPow = max*Constants.kDriveSensitivity;
      }
      //entonces estas girando a la izquierda
      else{
      rightPow = max*Constants.kDriveSensitivity;
      leftPow = ((ySpeed) + (xSpeed))*Constants.kDriveSensitivity; 
      }
    }
    //entonces quieres ir "atras"
    else{
      if(xSpeed >= 0){
        rightPow = (-max)*Constants.kDriveSensitivity;
        leftPow = ((ySpeed) + (xSpeed))*Constants.kDriveSensitivity; 
      }
      rightPow = ((xSpeed) - (ySpeed))*Constants.kDriveSensitivity;
      leftPow = (-max)*Constants.kDriveSensitivity;
    }

    //checa si no le estas dando con los triggers y ponle la rampa de velocidad
    //A los motores de la izquieda invierteles el signo, porque todos los calculos los hiciste como si los motores tuvieran la misma orientacion
    if(absMove != 0){ 
      final_right_front_demand = speedTramp(absMove, final_right_front_demand);
      final_right_back_demand = speedTramp(absMove, final_right_back_demand);
      final_left_front_demand = speedTramp(-absMove, final_left_front_demand);
      final_left_back_demand = speedTramp(-absMove, final_left_back_demand);     
    }
    else{
      final_right_front_demand =  speedTramp(rightPow, final_right_front_demand);
      final_right_back_demand =  speedTramp(rightPow, final_right_back_demand);
      final_left_front_demand =  speedTramp(-leftPow, final_left_front_demand);
      final_left_back_demand =  speedTramp(-leftPow, final_left_back_demand);
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
