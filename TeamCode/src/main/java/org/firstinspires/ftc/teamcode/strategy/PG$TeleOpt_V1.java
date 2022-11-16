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
        lift= new PG$LinearOperator(hardwareMap,telemetry);
        lift.parent = this;
        //telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$TeleGEMPrototype_V1 - runOpMode");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            lefty = - gamepad1.left_stick_y ;
            leftx =  gamepad1.left_stick_x * 1.1;
            righty = gamepad1.right_stick_y;
            rightx = gamepad1.right_stick_x;

            boolean dpad_left = gamepad2.dpad_left;
            boolean dpad_right = gamepad2.dpad_right;
            boolean dpad_up= gamepad2.dpad_up;
            boolean dpad_down= gamepad2.dpad_down;
            boolean a = gamepad2.a;
            boolean b= gamepad2.b;
            boolean x = gamepad2.x;
            boolean y = gamepad2.y;
            boolean gm1_lb =gamepad1.left_bumper;
            boolean gm1_rb =gamepad1.right_bumper;
            float gm1_lt =gamepad2.left_trigger*newGlobalConfig.turnTablePowerFactor;
            float gm1_rt =-gamepad2.right_trigger*newGlobalConfig.turnTablePowerFactor;

            if(dpad_left)
                lift.runViperMotor(1,1,"lifLevelTicks",1);
            else if(dpad_up)
                lift.runViperMotor(1,2,"lifLevelTicks",1);
            else if(dpad_right)
                lift.runViperMotor(1,3,"lifLevelTicks",1);
            else if(dpad_down)
                lift.runViperMotor(1,0,"lifLevelTicks",1);
            else if(gm1_lb)
                claw.release();
            else if(gm1_rb)
                claw.grab();
            else if(a)
                lift.runViperMotor(1, 1,"coneLevelTicks", 1);
            else if(b)
                lift.runViperMotor(1, 2,"coneLevelTicks", 1);
            else if(x)
                lift.runViperMotor(1, 3,"coneLevelTicks", 1);
            else if(y)
                lift.runViperMotor(1, 4,"coneLevelTicks", 1);
            else
                wheels.moveNoIMU(lefty,leftx,rightx);
        }
    }
}


