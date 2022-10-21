package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.archived.PG$MecanumDriveFourWheels_BKP;
import org.firstinspires.ftc.teamcode.functions.PG$RobotAutoDrive;
@Autonomous(name="GEM_Autonomous_Prototype")

//@Disabled
public class PG$AutonomousPrototype extends LinearOpMode {
    //Configuration used: 6wheelConfig
    private ElapsedTime runtime = new ElapsedTime();
    @Override

    public void runOpMode() throws InterruptedException {
        PG$RobotAutoDrive mecanum= new PG$RobotAutoDrive(hardwareMap,telemetry);

        double speed = 0.75;
        //PG$MecanumDriveFourWheels_BKP mecanum = new PG$MecanumDriveFourWheels_BKP(hardwareMap,telemetry);
//        Claw claw = new Claw(hardwareMap);
//        claw.parent = this;
//        claw.telemetry = this.telemetry;
        sleep(50);
        mecanum.IsAutonomous = true;
        //mecanum.telemetry = this.telemetry;
        mecanum.parent = this;

        waitForStart();
        //forward
        mecanum.encoderDrive(speed,200.0,200.0,200.0,200.0,2.0);
sleep(30000);
        //backward
        //mecanum.encoderDrive(speed,-120.0,-120.0,-120.0,-120.0,2.0);
        //left
        //mecanum.encoderDrive(speed,-120.0,0,120.0,120.0,1.0);
        //right
        //mecanum.encoderDrive(speed,120.0,0,-120.0,-120.0,1.0);
        //left turn
        //mecanum.encoderDrive(speed,-120.0,0,-120.0,120.0,1.0);
        //right turn
        //mecanum.encoderDrive(speed,120.0,0,120.0,-120.0,1.0);
        //contract
        //mecanum.encoderDrive(speed,3.15,0,-3.15,3.1,0,-3.1, 1.0);
        //expand
        //mecanum.encoderDrive(speed,-3.15,0,3.15,-3.1,0,3.1, 1.0);
//        claw.grab();
//        sleep(2000);
//        claw.release();
//        sleep(5000);

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