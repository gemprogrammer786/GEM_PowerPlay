/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/
package org.firstinspires.ftc.teamcode.strategy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.functions.PG$ClawOperator;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator;
import org.firstinspires.ftc.teamcode.functions.PG$RobotTeleOpDrive;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;


@TeleOp(name = "PG$TeleOpt_V1")

//@Disabled
public class PG$TeleOpt_V1 extends LinearOpMode {

    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    PG$RobotTeleOpDrive wheels;
    PG$ClawOperator claw;
    PG$LinearOperator lift;
    double lefty = 0.0;
    double leftx = 0.0;
    double righty = 0.0;
    double rightx = 0.0;
    @Override
    public void runOpMode() throws InterruptedException {

        wheels = new PG$RobotTeleOpDrive(hardwareMap,telemetry);
        wheels.parent = this;
        claw= new PG$ClawOperator(hardwareMap,telemetry);
        claw.parent = this;
        lift = new PG$LinearOperator(hardwareMap,telemetry);
        lift.parent = this;
        //telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$TeleGEMPrototype_V1 - runOpMode");
        telemetry.update();

        waitForStart();
        int afterGrabLevel=1;
        while (opModeIsActive()) {

            lefty = -gamepad1.left_stick_y ;
            leftx = gamepad1.left_stick_x * 1.1;
            righty = gamepad1.right_stick_y;
            rightx = gamepad1.right_stick_x;
            float gm1_lt = gamepad1.left_trigger;
            float gm1_rt = gamepad1.right_trigger;
            wheels.moveNoIMU(lefty,leftx,rightx,gm1_rt);

            boolean dpad_left = gamepad2.dpad_left;
            boolean dpad_right = gamepad2.dpad_right;
            boolean dpad_up = gamepad2.dpad_up;
            boolean dpad_down = gamepad2.dpad_down;
            boolean a2 = gamepad2.a;
            boolean b2 = gamepad2.b;
            boolean x2 = gamepad2.x;
            boolean y2 = gamepad2.y;
            boolean x1 = gamepad1.x;
            boolean gm1_lb = gamepad1.left_bumper;
            boolean gm1_rb = gamepad1.right_bumper;


            if(dpad_left)
                lift.runViperMotor(1,1,"lifLevelTicks",1);
            else if(dpad_up)
                lift.runViperMotor(1,2,"lifLevelTicks",1);
            else if(dpad_right)
                lift.runViperMotor(1,3,"lifLevelTicks",1);
            else if(dpad_down)
                lift.runViperMotor(1,0,"lifLevelTicks",1);
            else if(gm1_lb) {
                lift.runViperMotor(1, 0, "coneLevelTicks", 1);
                sleep(250);
                claw.release();
            }
            else if(gm1_rb) {
                claw.grab();
                sleep(250);
                lift.runViperMotor(1, afterGrabLevel,"coneLevelTicks", 1);
                afterGrabLevel=1;
            }
            else if(x1)
                lift.runViperMotor(1, 1,"coneLevelTicks", 1);
            else if(x2) {
                lift.runViperMotor(1, 3, "coneLevelTicks", 1);
                afterGrabLevel=6;
            }
            else if(y2) {
                lift.runViperMotor(1, 4, "coneLevelTicks", 1);
                afterGrabLevel=6;
            }
            else if(b2) {
                lift.runViperMotor(1, 5, "coneLevelTicks", 1);
                afterGrabLevel=6;
            }
            else if(a2) {
                lift.runViperMotor(1, 2, "coneLevelTicks", 1);
                afterGrabLevel=6;
            }
        }
    }
}


