package org.firstinspires.ftc.teamcode.global;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PG$GlobalConfig {

    public String frontRightWheel="frontRight";
    public String frontLeftWheel="frontLeft";
    public String backRightWheel="backRight";
    public String backLeftWheel="backLeft";
    public double leftWheelErrorAdjustment = 1.0;
    public double rightWheelErrorAdjustment = 1.0;
    public double mecanumWheelCircumference = 11.87; //inches
    public double robotAutonomusSpeedReducer = 1.0; // the value should be between 0 to 1
    public double robotTeleOpsSpeedReducer = 1.0; // the value should be between 0 to 1


    public String clawServoName = "claw";
    public double clawMin = 0.0;
    public double clawMax = 1.0;

    public String viperMotorName = "viperMotor";


    public String turnTableServoName = "turnTable";
    public double turnTableMin = 0.0;
    public double turnTableMax = 1.0;

    public int[] lifLevel = {0, 500, 1000, 1500};
    public int liftLevelMin = 0;
    public int liftLevelMax = 3000;

    //0 position
    public int liftMin = 5;
    //Level 1
    public int liftLevel1 = 5;
    //Level 2
    public int liftLevel2 = 17;
    //Level 3
    public int liftLevel3 = 10;

    public double liftPower = 0.5;


}
