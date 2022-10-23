package org.firstinspires.ftc.teamcode.prototypes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator;

@Autonomous(name="PG$AutoLinearPrototype")
@Disabled

public class PG$AutoLinearPrototype extends LinearOpMode {
    //Configuration used: 6wheelConfig
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        PG$LinearOperator lift = new PG$LinearOperator(hardwareMap,telemetry);

        // Setup Telemetry, will not clear after cycle, setup reusable items for output
        telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$LinearPrototype1 - runOpMode");
        telemetry.update();
        sleep(50);
        boolean IsAutonomous = false;
        lift.parent = this;
        waitForStart();

        lift.runViperMotor(0.5,0,120);
        telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,0,60)");
        telemetry.addData("---------------","--");
        lift.parent.sleep(1000);

        lift.runViperMotor(0.5,1,120);
        telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.1,0,60)");
        telemetry.addData("---------------","--");
        lift.parent.sleep(1000);

        lift.runViperMotor(0.5,3,120);
        telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,2,60)");
        telemetry.addData("---------------","--");
        lift.parent.sleep(1000);

        lift.runViperMotor(0.5,0,120);
        telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,2,60)");
        telemetry.addData("---------------","--");
        lift.parent.sleep(1000);

    }

}