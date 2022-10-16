/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.functions.PG$RobotTeleOpDrive;


@TeleOp(name = "Pawan_TeleOp_Prototype")

//@Disabled
public class PG$TeleOpPrototype extends LinearOpMode {

    PG$RobotTeleOpDrive wheels;
    //PG$MecanumDriveFourWheels_BKP wheels;
    //PG$MecanumDriveFourWheels wheels;
    double lefty = 0.0;
    double leftx = 0.0;
    double righty = 0.0;
    double rightx = 0.0;
    double liftPower = 0.0;
    double rotateArm = 0.0;
    double powerfactor = 0.6;

    @Override
    public void runOpMode() throws InterruptedException {
        //Hardware Mapping

       // wheels = new PG$MecanumDriveFourWheels_BKP(hardwareMap,telemetry);

        wheels = new PG$RobotTeleOpDrive(hardwareMap,telemetry);


        //wheels.rightErrorAdjustment = 0.93;//1;
        //wheels.telemetry = telemetry;
        wheels.parent = this;


        waitForStart();
        //claw.initiateLift();
        while (opModeIsActive()) {

//            //telemetry.addData("Finger 1 position", claw.clawFinger1.getPosition());
//            //telemetry.addData("Finger 2 position", claw.clawFinger2.getPosition());
//            //telemetry.update();
//            //mecanumWheels.initialize();
//
            lefty = -gamepad1.left_stick_y*powerfactor;
            leftx = gamepad1.left_stick_x*powerfactor;
            righty = gamepad1.right_stick_y*powerfactor;
            rightx = gamepad1.right_stick_x*powerfactor;

            liftPower = -gamepad2.left_stick_y;
            rotateArm = -gamepad2.right_stick_y;

            //lefty = gamepad2.left_stick_y;
            boolean dpad_left = gamepad1.dpad_left;
            boolean dpad_right = gamepad1.dpad_right;
            boolean b = gamepad1.b;
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;
            boolean a = gamepad1.a;
//            //if(!dpad_left && !dpad_right)
//            //else
            if(dpad_left) {
                //wheels.Collapse();
                //spinner.setPower(0);
            }
            else if(dpad_right) {
                //wheels.Expand();
                //spinner.setPower(0);
            }
            else if(y)
                powerfactor=0.6;
            else if(a)
                powerfactor=0.25;

            else{
                //wheels.move(lefty,righty,leftx,rightx);
                wheels.move(lefty,righty,leftx,rightx);
                //spinner.setPower(0);
            }

//            if(liftPower!=0)
//                claw.lift(liftPower);
//
//            if(rotateArm!=0)
//                claw.rotate(rotateArm);
//
////            wheels.leftMotorY(-lefty);
////            wheels.rightMotorY(-righty);
////            wheels.rightMotorX(rightx);
////            wheels.leftMotorX(leftx);





//            telemetry.addData("lefty", "%.2f", lefty);
//            telemetry.addData("leftx", "%.2f", leftx);
//            telemetry.addData("rightx", "%.2f", rightx);
//            telemetry.addData("righty", "%.2f", righty);
//
//            telemetry.update();
        }
    }
}


