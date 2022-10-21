package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$MecanumDriveFourWheels {

    public boolean IsAutonomous = false;
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    public LinearOpMode parent;
    public Telemetry telemetry;


    //Configuration used: 4wheelConfig
    public DcMotorEx frontright;
    public DcMotorEx frontleft;
    public DcMotorEx backright;
    public DcMotorEx backleft;
    public BNO055IMU imu;

    //public DcMotorEx xRail;
    public HardwareMap hardwareMap;




    public PG$MecanumDriveFourWheels( HardwareMap hardwareMap,Telemetry telemetry) {

        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;

        frontright = hardwareMap.get(DcMotorEx.class,newGlobalConfig.frontRightWheel);
        frontleft = hardwareMap.get(DcMotorEx.class,newGlobalConfig.frontLeftWheel);
        backright = hardwareMap.get(DcMotorEx.class, newGlobalConfig.backRightWheel);
        backleft = hardwareMap.get(DcMotorEx.class, newGlobalConfig.backLeftWheel);
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        double reset = 0;
        frontright.setPower(reset);
        frontleft.setPower(reset);
        backleft.setPower(reset);
        backright.setPower(reset);
        backright.setDirection(DcMotorSimple.Direction.REVERSE);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);
        backleft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontleft.setDirection(DcMotorSimple.Direction.FORWARD);


        if(IsAutonomous)
        {

//            backright.setDirection(DcMotorSimple.Direction.REVERSE);
//            frontright.setDirection(DcMotorSimple.Direction.FORWARD);
//            backleft.setDirection(DcMotorSimple.Direction.FORWARD);
//            frontleft.setDirection(DcMotorSimple.Direction.FORWARD);

            frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else
        {

            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            // Technically this is the default, however specifying it is clearer
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            // Without this, data retrieving from the IMU throws an exception
            imu.initialize(parameters);

            // Send telemetry message to signify robot waiting;
            // telemetry.addData(">", "Robot Ready.  Press Play.");    //
            // telemetry.update();

        }


    }


}
