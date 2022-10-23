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
import org.firstinspires.ftc.teamcode.functions.PG$TurnTableOperation;


@TeleOp(name = "PG$TeleTurnTablePrototype")

@Disabled
public class PG$TeleTurnTablePrototype extends LinearOpMode {

    PG$TurnTableOperation turnTable;


    @Override
    public void runOpMode() throws InterruptedException {
        //Hardware Mapping
        turnTable= new PG$TurnTableOperation(hardwareMap,telemetry);


        turnTable.parent = this;
        telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$TeleTurnTablePrototype - runOpMode");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {



//turnTable.turntable();

        }
    }
}


