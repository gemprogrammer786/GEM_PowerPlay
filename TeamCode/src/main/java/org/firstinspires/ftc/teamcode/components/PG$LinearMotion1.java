package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$LinearMotion1 {
    //Configuration used: 6wheelConfig
    public DcMotorEx linearLift;

    public LinearOpMode parent;

    public int velocity = 200;

    private ElapsedTime runtime = new ElapsedTime();
    public Telemetry telemetry;
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();

    public PG$LinearMotion1(HardwareMap hardwareMap,Telemetry telemetry) {
        telemetry.addData("PG$LinearMotion", "Initialization Started");
        telemetry.update();
        linearLift = hardwareMap.get(DcMotorEx.class,newGlobalConfig.viperMotorName);
        linearLift.setPower(0);
        linearLift.setDirection(DcMotorSimple.Direction.FORWARD);
        linearLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      //  linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //linearLift.setTargetPosition(0);
        //linearLift.setPower(0.2);

        telemetry.addData("PG$LinearMotion", "Initialization Completed");
        telemetry.update();
    }



//    //initialize for TeleOp
//    public void initialize() {
//        double reset = 0;
//        linearLift.setPower(reset);
//        linearLift.setDirection(DcMotorSimple.Direction.REVERSE);
//        linearLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        linearLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        linearLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        linearLift.setTargetPosition(0);
//        linearLift.setPower(0.2);
//    }



}
