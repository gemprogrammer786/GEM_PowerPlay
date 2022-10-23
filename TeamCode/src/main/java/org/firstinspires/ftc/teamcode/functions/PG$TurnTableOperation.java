package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.PG$TurnTableServo;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator;

public class PG$TurnTableOperation extends PG$TurnTableServo {

    HardwareMap hardwareMap;
    Telemetry telemetry;
    double  contPower;
    private ElapsedTime runtime = new ElapsedTime();
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();

    public PG$TurnTableOperation(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
        this.hardwareMap=hardwareMap;
        this.telemetry=telemetry;
    }

    public void turn(float ltPower, float rtPower ) {

        turnTable.setPower(ltPower + rtPower);
    }



}
