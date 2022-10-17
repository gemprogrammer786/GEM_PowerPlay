package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator1;

@Autonomous(name="GEM_Linear_Prototype")

//@Disabled
public class PG$LinearPrototype1 extends LinearOpMode {
    //Configuration used: 6wheelConfig
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {

        double speed = 10;
        PG$LinearOperator1 lift = new PG$LinearOperator1(hardwareMap,telemetry);
/*        PG$ArmLiftAndGrabber claw = new PG$ArmLiftAndGrabber(hardwareMap);
        //claw.initiateLift();
        claw.parent = this;
        claw.telemetry = this.telemetry;

        sleep(50);
  */
        boolean IsAutonomous = false;
  //      lift.velocity = 400;
  //      lift.telemetry = this.telemetry;
        lift.parent = this;
        waitForStart();
        lift.runViperMotor(0.5,1,10);
        //forward
       //lift.encoderDrive(speed,120.0,1.0);
   //     claw.lift(1);
   //     claw.grab();
        sleep(2000);
   //     claw.lift(0);
   //     claw.release();
   //     claw.lift(1);
        sleep(5000);
        //lift.encoderDrive(speed,-120.0,1.0);

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