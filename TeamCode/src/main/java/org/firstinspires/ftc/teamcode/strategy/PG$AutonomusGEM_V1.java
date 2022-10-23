/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/
package org.firstinspires.ftc.teamcode.strategy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.functions.PG$ClawOperator;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator;
import org.firstinspires.ftc.teamcode.functions.PG$RobotAutoDrive;
import org.firstinspires.ftc.teamcode.functions.PG$TurnTableOperation;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;


@Autonomous(name="PG$AutonomusGEM_V1")

//@Disabled
public class PG$AutonomusGEM_V1 extends LinearOpMode {

    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    PG$RobotAutoDrive wheels;
    PG$ClawOperator claw;
    PG$LinearOperator lift;
    PG$TurnTableOperation turnTable;


    @Override
    public void runOpMode() throws InterruptedException {

        wheels = new PG$RobotAutoDrive(hardwareMap,telemetry);
        wheels.parent = this;

        turnTable= new PG$TurnTableOperation(hardwareMap,telemetry);
        turnTable.parent = this;

        claw= new PG$ClawOperator(hardwareMap,telemetry);
        claw.parent = this;

        lift= new PG$LinearOperator(hardwareMap,telemetry);
        lift.parent = this;

        //telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$TeleGEMPrototype_V1 - runOpMode");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            wheels.encoderDrive(.5,200.0,200.0,200.0,200.0,2.0);
            lift.runViperMotor(1,1,1);
            lift.runViperMotor(1,0,1);
            claw.release();
            claw.grab();
            turnTable.turn(0.2f,0);
        }
    }
}


