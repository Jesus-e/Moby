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
    //Botones
    public static final int AButton = 1;
    public static final int BButton = 2;
    public static final int XButton = 3;
    public static final int YButton = 4;



    //Talons
    public static final int Talon0 = 10; //1, 6, 8, 9, 10, 2, 3, 4, 5
    public static final int Talon1 = 1;
    public static final int Talon2 = 2;
    public static final int Talon3 = 3;
    public static final int Talon4 = 4;
    public static final int Talon5 = 5;
    public static final int Talon6 = 6;
    public static final int Talon7 = 8;
    public static final int Talon8 = 9;

    //Drive
    public static final double kDriveRampDeltaSpeed = 0.1;
    public static final double kDriveSensitivity = 0.5;
    public static final int kDriveRightFrontId = Talon0;
    public static final int kDriveLeftFrontId = Talon1;
    public static final int kDriveRightBackId = Talon2;
    public static final int kDriveLeftBackId = Talon3;

    //Intake
    public static final int kIntakeId = Talon4;
    public static final double kIntakeDemand = 0.3;

}
