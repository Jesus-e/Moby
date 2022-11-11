package frc.robot.subsystems;

public class Constants {

    //Controles
    public static final int Control1Id = 0;
    public static final int Control2Id = 1;
    //Checar los numeros de los ejes y botones del control
    public static final int LeftXAxis = 0;
    public static final int LeftYAxis = 1; 
    public static final int RightXAxis = 2;
    public static final int RightYAxis = 3;
    //Botones
    public static final int AButton = 1;
    public static final int BButton = 2;
    public static final int XButton = 3;
    public static final int YButton = 4;



    //Talons
    public static final int Talon0 = 1;
    public static final int Talon1 = 6;
    public static final int Talon2 = 8;
    public static final int Talon3 = 9;
    public static final int Talon4 = 10;

    //Drive
    public static final double kDriveRampDeltaSpeed = 0.1;
    public static final double kDriveSensitivity = 0.8;
    public static final int kDriveRightFrontId = Talon0;
    public static final int kDriveLeftFrontId = Talon1;
    public static final int kDriveRightBackId = Talon2;
    public static final int kDriveLeftBackId = Talon3;

    //Intake
    public static final int kIntakeId = Talon4;
    public static final double kIntakeDemand = 0.3;

}
