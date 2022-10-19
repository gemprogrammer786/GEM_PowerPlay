package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator1;

@Autonomous(name="GEM_Linear_Prototype1")

//@Disabled
public class PG$LinearPrototype1 extends LinearOpMode {
    //Configuration used: 6wheelConfig
    private ElapsedTime runtime = new ElapsedTime();
    PG$LinearOperator1 lift = new PG$LinearOperator1(hardwareMap,telemetry);

    @Override
    public void runOpMode() throws InterruptedException {
        // Setup Telemetry, will not clear after cycle, setup reusable items for output
        telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$LinearPrototype1 - runOpMode");
        telemetry.update();
        sleep(50);
        boolean IsAutonomous = false;
        lift.parent = this;
        waitForStart();

        lift.runViperMotor(0.2,0,1);
        telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,0,60)");
        telemetry.addData("---------------","--");
        lift.parent.sleep(1000);

        lift.runViperMotor(0.2,1,1);
        telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.1,0,60)");
        telemetry.addData("---------------","--");
        lift.parent.sleep(1000);

        lift.runViperMotor(0.2,3,1);
        telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,2,60)");
        telemetry.addData("---------------","--");
        lift.parent.sleep(1000);

        lift.runViperMotor(0.2,0,1);
        telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,2,60)");
        telemetry.addData("---------------","--");
        lift.parent.sleep(1000);

    }

}