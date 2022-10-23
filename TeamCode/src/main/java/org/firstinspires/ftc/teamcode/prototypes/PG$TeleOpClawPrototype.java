/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/
package org.firstinspires.ftc.teamcode.prototypes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.functions.PG$ClawOperator;


@TeleOp(name = "PG$TeleOpClawPrototype")
@Disabled
public class PG$TeleOpClawPrototype extends LinearOpMode {

    PG$ClawOperator claw;


    @Override
    public void runOpMode() throws InterruptedException {
        //Hardware Mapping
        claw= new PG$ClawOperator(hardwareMap,telemetry);


        claw.parent = this;
        telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$ClawOperator - runOpMode");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {



            boolean dpad_left = gamepad1.dpad_left;
            boolean dpad_up= gamepad1.dpad_up;


            if(dpad_left) {
                claw.release();
                telemetryDebug.setValue("Claw Released");
                telemetry.addData("---------------","--");
                claw.parent.sleep(1000);
            }
            else if(dpad_up) {
                claw.grab();
                telemetryDebug.setValue("Claw Grabbed");
                telemetry.addData("---------------","--");
                claw.parent.sleep(1000);
            }


        }
    }
}


