package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.Spinner;


import java.util.List;

@Autonomous(name="EPIC_Blue_LEFT_Autonomous", group="Robot19587")
public class EPIC_BLUE_LEFT_Autonomous extends LinearOpMode {
    //Configuration used: 6wheelConfig

    private static String MODEL_FILE_NAME = "EPIC_blue_left_model.tflite";
    private static String LABEL_FILE_NAME = "EPIC_blue_left_labels.txt";
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        double distance = 0;
        double correctionFactor = 1;

            double speed = 0.6;
            Mecanum_Wheels mecanum = new Mecanum_Wheels(hardwareMap);
            mecanum.leftErrorAdjustment = 0.991;

            mecanum.parent = this;
            //sleep(5000);
            mecanum.IsAutonomous = true;
            mecanum.velocity = 400;
            mecanum.telemetry = this.telemetry;
            mecanum.initialize();
            runtime.reset();
            telemetry.addData("Opmode Active", "No");
            telemetry.update();
            waitForStart();
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive() ) {

            mecanum.encoderDrive(0.6,distance,distance,distance,distance,4);
           //Grab

//            //Backwards
//            distance = 65;
//            mecanum.encoderDrive(0.6,-distance,-distance,-distance,-distance,3);
//            //Turn Right
//            distance = 17.3;
//            mecanum.encoderDrive(0.6,distance,distance,-distance,-distance,2);
//            //Forward
//            distance = 17;
//            mecanum.encoderDrive(0.6,distance,distance,distance,distance,2);
//            //Release
//            claw.release();
//            //Backwards
//            distance = 17;
//            mecanum.encoderDrive(0.6,-distance,-distance,-distance,-distance,2);
//            //Turn Left
//            distance = 17.3;
//            mecanum.encoderDrive(0.6,-distance,-distance,distance,distance,2);
//            //Forward
//            distance = 55;
//            mecanum.encoderDrive(0.6,distance,distance,distance,distance,2);

        }


    }

}