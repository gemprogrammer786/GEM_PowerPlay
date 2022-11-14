/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/
package org.firstinspires.ftc.teamcode.strategy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.auton.AprilTagDetectionPipeline;
import org.firstinspires.ftc.teamcode.functions.PG$ClawOperator;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator;
import org.firstinspires.ftc.teamcode.functions.PG$RobotAutoDrive;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(name="PG$AutonomusGEM_V2")

//@Disabled
public class PG$AutonomusGEM_V2 extends LinearOpMode {

    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    PG$RobotAutoDrive wheels;
    PG$ClawOperator claw;
    PG$LinearOperator lift;
    //PG$TurnTableOperation turnTable;
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag ID 1,2,3 from the 36h11 family
    int LEFT = 1;
    int MIDDLE = 2;
    int RIGHT = 3;

    AprilTagDetection tagOfInterest = null;


    @Override
    public void runOpMode() throws InterruptedException {

        wheels = new PG$RobotAutoDrive(hardwareMap,telemetry);
        wheels.parent = this;

//        turnTable= new PG$TurnTableOperation(hardwareMap,telemetry);
//        turnTable.parent = this;
//
        claw= new PG$ClawOperator(hardwareMap,telemetry);
        claw.parent = this;
//
        lift= new PG$LinearOperator(hardwareMap,telemetry);
        lift.parent = this;

        //telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$TeleGEMPrototype_V1 - runOpMode");
        telemetry.update();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /* Update the telemetry */
        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        waitForStart();


    /*        wheels.encoderDrive(.5,10.0,10.0,10.0,10.0,2.0);
     */

        /* START OF FIRST CONE */
        claw.grab();
        wheels.moveRight(21,newGlobalConfig.medium,2.0);
        lift.runViperMotor(1,1,1);
        wheels.moveForward(7.5, newGlobalConfig.medium,2.0);
        sleep(200);
        claw.release();
        wheels.moveForward(-7.5, newGlobalConfig.medium,2.0);
        lift.runViperMotor(1,0,1);
        claw.grab();
        wheels.moveLeft(19,newGlobalConfig.medium,2.0);

        /* END OF FIRST CONE */
        /* START OF SECOND CONE */
        claw.release();
        wheels.moveForward(24, newGlobalConfig.medium,2.0);
        claw.grab();
        wheels.moveForward(-24, newGlobalConfig.medium,2.0);
        wheels.moveRight(44,newGlobalConfig.medium,2.0);
        lift.runViperMotor(1,2,1);
        wheels.moveForward(7.5, newGlobalConfig.medium,2.0);
        sleep(200);
        claw.release();
        wheels.moveForward(-7.5, newGlobalConfig.medium,2.0);
        lift.runViperMotor(1,0,1);
        claw.grab();


        /* END OF SECOND CONE */



        /* END OF THIRD CONE */


       /*
        lift.runViperMotor(1,2,1);
        claw.release();

        wheels.encoderDrive(.5,-10.0,-10.0,-10.0,-10.0,2.0);
        lift.runViperMotor(1,0,1);
        claw.grab();

        wheels.encoderDrive(.5,-20.0,20.0,-20.0,20.0,2.0);



        wheels.turnClockwise(-45, newGlobalConfig.fast,2.0);
        wheels.moveForward(5, newGlobalConfig.fast,2.0);
        wheels.turnClockwise(-45, newGlobalConfig.fast,2.0);
        wheels.moveForward(6, newGlobalConfig.fast,2.0);

        wheels.moveForward(-6, newGlobalConfig.fast,2.0);
        wheels.turnClockwise(-3, newGlobalConfig.medium,2.0); // aiming tweak
        wheels.moveRight(5, newGlobalConfig.fast,2.0);

        wheels.moveForward(-5, newGlobalConfig.fast,2.0);
        wheels.turnClockwise(-135, newGlobalConfig.fast,2.0);
        wheels.moveForward(10, newGlobalConfig.fast,2.0);*/

        if(tagOfInterest.id == RIGHT){
            //trajectory for parking in RIGHT
            wheels.moveLeft(12,newGlobalConfig.medium,2.0);
            wheels.moveForward(-26, newGlobalConfig.medium,2.0);
        }

        if(tagOfInterest.id == MIDDLE){
            //trajectory for parking in MIDDLE
            wheels.moveLeft(12,newGlobalConfig.medium,2.0);
        }

        if(tagOfInterest.id == LEFT){
            //trajectory for parking in LEFT
            wheels.moveLeft(12,newGlobalConfig.medium,2.0);
            wheels.moveForward(26, newGlobalConfig.medium,2.0);


        }

    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));

    }

}


