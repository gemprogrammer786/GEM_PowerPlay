/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator1;
import org.firstinspires.ftc.teamcode.functions.PG$RobotTeleOpDrive;


@TeleOp(name = "Linear_TeleOp_Prototype")

//@Disabled
public class PG$LinearTeleOpPrototype extends LinearOpMode {

    PG$LinearOperator1 lift;


    @Override
    public void runOpMode() throws InterruptedException {
        //Hardware Mapping
        lift= new PG$LinearOperator1(hardwareMap,telemetry);
       // wheels = new PG$MecanumDriveFourWheels_BKP(hardwareMap,telemetry);


        lift.parent = this;
        telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$LinearPrototype1 - runOpMode");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {


            //lefty = gamepad2.left_stick_y;
            boolean dpad_left = gamepad1.dpad_left;
            boolean dpad_right = gamepad1.dpad_right;
            boolean dpad_up= gamepad1.dpad_up;
            boolean dpad_down= gamepad1.dpad_down;

            if(dpad_left) {
                lift.runViperMotor(0.2,1,1);
                telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,1,1)");
                telemetry.addData("---------------","--");
                lift.parent.sleep(1000);
            }
            else if(dpad_up) {
                lift.runViperMotor(0.2,2,1);
                telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,2,1)");
                telemetry.addData("---------------","--");
                lift.parent.sleep(1000);
            }else if(dpad_right) {
                lift.runViperMotor(0.2,3,1);
                telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,3,1)");
                telemetry.addData("---------------","--");
                lift.parent.sleep(1000);
            }else if(dpad_down) {
                lift.runViperMotor(0.2,0,1);
                telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,0,1)");
                telemetry.addData("---------------","--");
                lift.parent.sleep(1000);
            }


        }
    }
}


