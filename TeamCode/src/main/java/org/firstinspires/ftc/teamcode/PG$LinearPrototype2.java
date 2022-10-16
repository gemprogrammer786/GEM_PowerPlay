package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.components.PG$LinearMotion1;
import org.firstinspires.ftc.teamcode.functions.PG$ArmLiftAndGrabber;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator2;
import org.firstinspires.ftc.teamcode.functions.PG$RobotAutoDrive;

@Autonomous(name="GEM_Linear_Prototype2")

//@Disabled
public class PG$LinearPrototype2 extends LinearOpMode {
    //Configuration used: 6wheelConfig
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        PG$LinearOperator2 lift = new PG$LinearOperator2(hardwareMap,telemetry);

        double speed = 10;


        sleep(50);
        lift.IsAutonomous = true;

        lift.parent = this;
        //lift.initiateLift();
        waitForStart();
        //forward
       //lift.encoderDrive(speed,120.0,1.0);
        lift.lift(1);
        sleep(2000);
        lift.lift(0);
        lift.lift(2);
        sleep(2000);
        lift.lift(3);
        sleep(2000);
        //lift.encoderDrive(speed,-120.0,1.0);
        lift.lift(0);
//        mecanum.moveForward();
//        //mecanum.TestMechanumWheels(0.5);
//        //mecanum.TestOmniWheels(0.5);
//        //mecanum.TestOmniWheelsBackward(1);
//        //while (opModeIsActive())
//        //{
//        //    if(runtime.seconds() <3.0) {
//                mecanum.moveY(12);
//                //mecanum.moveY(-1000);
//                telemetry.addData("Done", runtime.seconds());
//                telemetry.addData("middleright", mecanum.middleright.getPower());
//                telemetry.addData("middleleft", mecanum.middleleft.getPower());
//                //telemetry.addData
//                telemetry.update();
//        //    }
//        //}
//        while (mecanum.frontleft.isBusy()) {
//            //mecanum.moveY(1000);
//        //    else if(runtime.seconds() >3.0 && runtime.seconds()<6.0) {
//        //        mecanum.moveY(-1000);
//                telemetry.addData("Done", runtime.seconds());
//                telemetry.addData("middleright", mecanum.middleright.getPower());
//                telemetry.addData("middleleft", mecanum.middleleft.getPower());
//                //telemetry.addData
//                telemetry.update();
//        //    }
//        }
//        mecanum.moveY(-12);
//        while (mecanum.frontleft.isBusy()) {
//            //mecanum.moveY(1000);
//            //    else if(runtime.seconds() >3.0 && runtime.seconds()<6.0) {
//            //        mecanum.moveY(-1000);
//            telemetry.addData("Done", runtime.seconds());
//            telemetry.addData("middleright", mecanum.middleright.getPower());
//            telemetry.addData("middleleft", mecanum.middleleft.getPower());
//            //telemetry.addData
//            telemetry.update();
//            //    }
//        }
//
//        //mecanum.TestOmniWheels(0.5);
//        //mecanum.TestOmniWheelsBackward(0);
//        //mecanum.TestMechanumWheels(0);

    }

}