package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$MecanumDriveFourWheels {

    public boolean IsAutonomous = false;
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    public LinearOpMode parent;
    public Telemetry telemetry;
    public DcMotor frontleft ;
    public DcMotor backleft ;
    public DcMotor frontright ;
    public DcMotor backright ;
    public BNO055IMU imu;
    public HardwareMap hardwareMap;

    public PG$MecanumDriveFourWheels( HardwareMap hardwareMap,Telemetry telemetry) {
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;

        frontright = hardwareMap.get(DcMotorEx.class,newGlobalConfig.frontRightWheel);
        frontleft = hardwareMap.get(DcMotorEx.class,newGlobalConfig.frontLeftWheel);
        backright = hardwareMap.get(DcMotorEx.class, newGlobalConfig.backRightWheel);
        backleft = hardwareMap.get(DcMotorEx.class, newGlobalConfig.backLeftWheel);
        frontright.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontleft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        double reset = 0;
        frontright.setPower(reset);
        frontleft.setPower(reset);
        backleft.setPower(reset);
        backright.setPower(reset);
        backright.setDirection(DcMotorSimple.Direction.REVERSE);
        frontright.setDirection(DcMotorSimple.Direction.REVERSE);
        if(IsAutonomous)
        {
            PIDFCoefficients newPIDF = new PIDFCoefficients(10.0,  3.0,   0.0,  12.0);
            frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        else
        {
            imu = hardwareMap.get(BNO055IMU.class, "imu");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            imu.initialize(parameters);
        }


    }




}
