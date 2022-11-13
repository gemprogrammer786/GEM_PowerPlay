package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$ClawServo {

    public boolean IsAutonomous = false;
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    public LinearOpMode parent;
    public Telemetry telemetry;
    public Servo clawGrabber;
    public HardwareMap hardwareMap;

    public PG$ClawServo( HardwareMap hardwareMap,Telemetry telemetry) {
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;
        clawGrabber = hardwareMap.get(Servo.class,newGlobalConfig.clawServoName);
        clawGrabber.setPosition(newGlobalConfig.clawMax);
        telemetry.addData("PG$ClawServo : ","Initialized");

    }
}
