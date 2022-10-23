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
    public double leftWheelErrorAdjustment = 0.45;
    public double rightWheelErrorAdjustment = 0.0;
    public double mecanumWheelCircumference = 11.87; //inches
    public double teleOpdrivePowerfactor = 0.6;// the value should be between 0 to 1
    public double autodrivePowerfactor = 0.8;// the value should be between 0 to 1

    public float turnTablePowerFactor = .5f;

    public String clawServoName = "claw";
    public double clawMin = 0.3;
    public double clawMax = 0.75;

    public String viperMotorName = "viperMotor";


    public String turnTableServoName = "turnTable";
    public double turnTableMin = 0.0;
    public double turnTableCenter = 0.5;
    public double turnTableMax = 1.0;

    public int[] lifLevel = {0, 700, 1300, 2000};
    public int liftLevelMin = 0;
    public int liftLevelMax = 4400;

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
