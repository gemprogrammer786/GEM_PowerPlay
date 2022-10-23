package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.components.PG$LinearMotion;
import org.firstinspires.ftc.teamcode.functions.PG$ArmLiftAndGrabber;
import org.firstinspires.ftc.teamcode.functions.PG$ClawOperator;
import org.firstinspires.ftc.teamcode.functions.PG$RobotAutoDrive;
import org.firstinspires.ftc.teamcode.functions.PG$TurnTableOperation;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class Left_backup {

    package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.components.PG$LinearMotion;
import org.firstinspires.ftc.teamcode.functions.PG$ArmLiftAndGrabber;
import org.firstinspires.ftc.teamcode.functions.PG$ClawOperator;
import org.firstinspires.ftc.teamcode.functions.PG$RobotAutoDrive;
    @Autonomous(name="GEM_Autonomous_Prototype")

    public class Blue_right_Red_right_auton extends LinearOpMode {

        private ElapsedTime runtime = new ElapsedTime();
        @Override

        public void runOpMode() throws InterruptedException {

            double speed = 0.5;
            //PG$MecanumDriveFourWheels_BKP mecanum = new PG$MecanumDriveFourWheels_BKP(hardwareMap,telemetry);
            PG$RobotAutoDrive mecanum = new PG$RobotAutoDrive(hardwareMap,telemetry);
            PG$ClawOperator claw= new PG$ClawOperator(hardwareMap,telemetry);
            PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
            PG$TurnTableOperation turntable = new PG$TurnTableOperation();

            PG$LinearMotion linearmotion = new  PG$LinearMotion(hardwareMap,telemetry);

            double speed = 10;
            PG$LinearMotion lift = new PG$LinearMotion(hardwareMap);
            PG$ArmLiftAndGrabber claw = new PG$ArmLiftAndGrabber(hardwareMap);
            //claw.initiateLift();
            claw.parent = this;
            claw.telemetry = this.telemetry;

//        Claw claw = new Claw(hardwareMap);
//        claw.parent = this;
//        claw.telemetry = this.telemetry;
            sleep(50);
            mecanum.IsAutonomous = true;
            //mecanum.telemetry = this.telemetry;
            mecanum.parent = this;

            waitForStart();
            //forward 12 inches
            mecanum.encoderDrive(speed,200.0,200.0,200.0,200.0,1.0);/
            //move turn table 90 degrees
            //carouselwheel.rotate
            //move left 36 inches while raising arm
            mecanum.encoderDrive(speed,-120.0,0,120.0,120.0,3.0);
            //arm.linearmotion(speed, 0.5)
            //open claw
            claw.release();
            //right 12 in
            mecanum.encoderDrive(speed,120.0,0,-120.0,-120.0,1.0);
            //rotate turntable 180 degrees
            mecanum.encoderDrive(speed,0,-200,200,0,2.0);
            //forward 24 inches
            mecanum.encoderDrive(speed,200.0,200.0,200.0,200.0,2.0);
            //close claw
            claw.grab();
            //forward 24 inches
            mecanum.encoderDrive(speed,200.0,200.0,200.0,200.0,2.0);
            //open claw
            claw.release();
            //move back 24 in
            mecanum.encoderDrive(speed,-120.0,-120.0,-120.0,-120.0,2.0);
            //move right 24 in
            mecanum.encoderDrive(speed,120.0,0,-120.0,-120.0,2.0);
            //forward 30 inches
            mecanum.encoderDrive(speed,200.0,200.0,200.0,200.0,2.5);
            //close claw
            claw.grab();
            //move back 30 in while raising arm
            mecanum.encoderDrive(speed,-120.0,-120.0,-120.0,-120.0,2.0);
            claw.lift();
            //rotate turntable 180 degrees
            mecanum.encoderDrive(speed,0,-200,200,0,2.0);
            //move left 12 in
            mecanum.encoderDrive(speed,120.0,0,-120.0,-120.0,1.0);
            //open claw
            claw.release();
            //right 12 in
            mecanum.encoderDrive(speed,120.0,0,-120.0,-120.0,1.0);
            //rotate turntable 180 degrees
            mecanum.encoderDrive(speed,0,-200,200,0,2.0);
            //forward 30 inches while lowering arm
            mecanum.encoderDrive(speed,200.0,200.0,200.0,200.0,2.5);
            claw.lower();
            //close claw
            claw.grab();
            //move back 30 in while raising arm
            mecanum.encoderDrive(speed,-120.0,-120.0,-120.0,-120.0,2.0);
            claw.lift();
            //rotate turntable 180 degrees
            mecanum.encoderDrive(speed,0,-200,200,0,2.0);
            //move left 12 in
            mecanum.encoderDrive(speed,120.0,0,-120.0,-120.0,1.0);
            //open claw
            claw.release();
            //rotate 90 degrees clockwise
            mecanum.encoderDrive(speed,0,-200,200,0,1.0);
            //forward 36 inches
            mecanum.encoderDrive(speed,200.0,200.0,200.0,200.0,3.0);
            //use stored information to park

            if (parkingSpot=1)
            { //right 12 inches
                mecanum.encoderDrive(speed, 120.0, 0, -120.0, -120.0, 1.0);
            }

            else if (parkingSpot=2)
            { //stay there
                mecanum.encoderDrive(speed, 0.0, 0.0, 0.0, 0.0, 0.0);
            }

            else if (parkingSpot=3)
            { //move left 24 inches
                mecanum.encoderDrive(speed, 120.0, 0, -120.0, -120.0, 2.0);
            }


        }

    }
