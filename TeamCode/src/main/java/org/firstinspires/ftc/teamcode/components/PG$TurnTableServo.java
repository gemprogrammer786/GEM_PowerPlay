package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$TurnTableServo {

    public boolean IsAutonomous = false;
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    public LinearOpMode parent;
    public Telemetry telemetry;
    public CRServo turnTable;
    public HardwareMap hardwareMap;

    public PG$TurnTableServo(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;
        turnTable = hardwareMap.get(CRServo.class,newGlobalConfig.turnTableServoName);
        //turnTable.setPosition(newGlobalConfig.turnTableCenter);

    }
}
