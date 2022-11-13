package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.PG$ClawServo;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$ClawOperator extends PG$ClawServo {

    HardwareMap hardwareMap;
    Telemetry telemetry;
    private ElapsedTime runtime = new ElapsedTime();
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();

    public PG$ClawOperator(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
        this.hardwareMap=hardwareMap;
        this.telemetry=telemetry;
    }

    public void initiateClaw() {

    }

    public void pick()
    {
        clawGrabber.setPosition(newGlobalConfig.clawMax);
    }

    public void grab()
    {
        telemetry.addData("Postion Claw 1:%d", clawGrabber.getPosition());
        telemetry.update();
        clawGrabber.setPosition(newGlobalConfig.clawMax);
        telemetry.addData("Postion Claw 1:%d", clawGrabber.getPosition());
        telemetry.update();
    }

    public void release() {
        clawGrabber.setPosition(newGlobalConfig.clawMin);
        telemetry.addData("Postion Claw 1:%d", clawGrabber.getPosition());
        telemetry.update();
        //parent.sleep(5000);
    }



}
