package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$LinearMontion2 {

    public boolean IsAutonomous = false;
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    public LinearOpMode parent;
    public Telemetry telemetry;
    public DcMotorEx linearLift;
    public HardwareMap hardwareMap;

    public PG$LinearMontion2( HardwareMap hardwareMap,Telemetry telemetry) {
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;
        linearLift = hardwareMap.get(DcMotorEx.class,newGlobalConfig.viperMotorName);
        linearLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearLift.setTargetPosition(0);
        linearLift.setPower(0.2);

    }


}
