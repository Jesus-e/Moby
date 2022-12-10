package frc.robot.subsystems;

public class Constants {

    //Controles
    public static final int Control1Id = 0;
    public static final int Control2Id = 1;
    //Checar los numeros de los ejes y botones del control
    public static final int LeftXAxis = 0;
    public static final int LeftYAxis = 1;
    public static final int LeftTrigger = 2;
    public static final int RightTrigger = 3;
    public static final int RightXAxis = 4;
    public static final int RightYAxis = 5;
    public static final int RB = 6;
    //Botones
    public static final int AButton = 1;
    public static final int BButton = 2;
    public static final int XButton = 3;
    public static final int YButton = 4;



    //Talons
    //1, 6, 8, 9, 2, 3, 4, 5
    public static final int Talon1 = 1;
    public static final int Talon2 = 2;
    public static final int Talon3 = 3;
    public static final int Talon4 = 4;
    public static final int Talon5 = 5;
    public static final int Talon6 = 6;
    //public static final int Talon7 = 7;
    public static final int Victor7 = 7; 
    public static final int Talon8 = 8;
    public static final int Talon9 = 7; 

    //Drive
    public static final double kDriveRampDeltaSpeed = 0.2;
    public static final double kDriveSensitivity = 0.7;
    public static final int kDriveRightFrontId = Talon3;
    public static final int kDriveLeftFrontId = Talon1;
    public static final int kDriveRightBackId = Talon4;
    public static final int kDriveLeftBackId = Talon2;
    //Pruebas
    public static double gain = 0.7;
    public static double another_gain = 0.7;

    //Alas
    public static final int kMotorAla = Victor7;

    //Intake
    public static final int kIntakeId = Talon5;
    public static final double kIntakeDemand = 0.65;

    //Piston
    public static final int kPiston1ForwardId = 0;
    public static final int kPiston1ReverseId = 1;
    public static final int kPiston2ForwardId = 6;
    public static final int kPiston2ReverseId = 7;
    public static final int kAlaLeft = 3;
    public static final int kAlaRight = 4;
    public static final int kAlaRight2 = 4;

    //Caja
    public static final int kMotorCajaRightId = Talon8;
    public static final int kMotorCajaLeftId = Talon9;
    public static final double kCajaSensitivity = 0.5;

    //Hopper
    public static final int kHopper1Id = Talon6;
}
