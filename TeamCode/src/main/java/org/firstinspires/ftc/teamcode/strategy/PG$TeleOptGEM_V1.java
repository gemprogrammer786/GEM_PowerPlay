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
import org.firstinspires.ftc.teamcode.functions.PG$TurnTableOperation;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;


@TeleOp(name = "PG$TeleOptGEM_V1")

//@Disabled
public class PG$TeleOptGEM_V1 extends LinearOpMode {

    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    PG$RobotTeleOpDrive wheels;
    PG$ClawOperator claw;
    PG$LinearOperator lift;
    PG$TurnTableOperation turnTable;



    double lefty = 0.0;
    double leftx = 0.0;
    double righty = 0.0;
    double rightx = 0.0;





    @Override
    public void runOpMode() throws InterruptedException {

        wheels = new PG$RobotTeleOpDrive(hardwareMap,telemetry);
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

            lefty = - gamepad1.left_stick_y ;
            leftx =  gamepad1.left_stick_x;
            righty = gamepad1.right_stick_y;
            rightx = gamepad1.right_stick_x;

            boolean dpad_left = gamepad2.dpad_left;
            boolean dpad_right = gamepad2.dpad_right;
            boolean dpad_up= gamepad2.dpad_up;
            boolean dpad_down= gamepad2.dpad_down;
            boolean b = gamepad1.b;
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;
            boolean a = gamepad1.a;
            boolean gm1_lb =gamepad1.left_bumper;
            boolean gm1_rb =gamepad1.right_bumper;
            float gm1_lt =gamepad2.left_trigger*newGlobalConfig.turnTablePowerFactor;
            float gm1_rt =-gamepad2.right_trigger*newGlobalConfig.turnTablePowerFactor;


            if(dpad_left) {
                lift.runViperMotor(1,1,1);
                telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,1,1)");
                telemetry.addData("---------------","--");
                lift.parent.sleep(100);
            }
            else if(dpad_up) {
                lift.runViperMotor(1,2,1);
                telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,2,1)");
                telemetry.addData("---------------","--");
                lift.parent.sleep(100);
            }else if(dpad_right) {
                lift.runViperMotor(1,3,1);
                telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,3,1)");
                telemetry.addData("---------------","--");
                lift.parent.sleep(100);
            }else if(dpad_down) {
                lift.runViperMotor(1,0,1);
                telemetryDebug.setValue("PG$LinearPrototype1 - lift.runViperMotor(0.5,0,1)");
                telemetry.addData("---------------","--");
                lift.parent.sleep(100);
            }else if(gm1_lb) {
                claw.release();
                telemetryDebug.setValue("Claw Released");
                telemetry.addData("---------------","--");
                claw.parent.sleep(500);;
            }else if(gm1_rb) {
                claw.grab();
                telemetryDebug.setValue("Claw Grabbed");
                telemetry.addData("---------------","--");
                claw.parent.sleep(500);
            }
            else if(y)
                newGlobalConfig.teleOpdrivePowerfactor=0.6;
            else if(a)
                newGlobalConfig.teleOpdrivePowerfactor=0.25;

            else{
               //wheels.move(lefty,righty,leftx,rightx);
                wheels.moveNoIMU(lefty,leftx,rightx);
                turnTable.turn(gm1_lt,gm1_rt);
            }

        }
    }
}


