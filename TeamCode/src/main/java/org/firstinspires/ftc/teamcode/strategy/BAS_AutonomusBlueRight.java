/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/
package org.firstinspires.ftc.teamcode.strategy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.functions.AprilTagDetectionPipeline;
import org.firstinspires.ftc.teamcode.functions.PG$ClawOperator;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator;
import org.firstinspires.ftc.teamcode.functions.PG$RobotAutoDrive;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(name="BAS_AutonomusBlueRight")

//@Disabled
public class BAS_AutonomusBlueRight extends LinearOpMode {

    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    PG$RobotAutoDrive wheels;
    PG$ClawOperator claw;
    PG$LinearOperator lift;
    DistanceSensor distanceSensorBack;
    DistanceSensor distanceSensorRight;
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

        distanceSensorBack = hardwareMap.get(DistanceSensor.class,"distancesensor_back");

        //telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$TeleGEMPrototype_V1 - runOpMode");
        telemetry.update();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 2"), cameraMonitorViewId);
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

        int afterGrabLevel=1;


        /* START OF FIRST CONE */
        if(tagOfInterest.id == RIGHT){
            //trajectory for parking in RIGHT
            claw.grab();
            lift.runViperMotor(1,1,"coneLevelTicks",1);
            sleep(50);
            wheels.moveForward(-26,newGlobalConfig.medium,2);
            wheels.moveRight(49.5,newGlobalConfig.medium,2);
            lift.runViperMotor(1,1,"lifLevelTicks",1);
            wheels.moveForward(3.25,newGlobalConfig.slow,1);
            sleep(1500);
            lift.runViperMotor(.6,6,"lifLevelTicks",1);
            sleep(400);
            claw.release();
            sleep(500);
            wheels.moveForward(-1,newGlobalConfig.slow,1);
            sleep(3000);
            lift.runViperMotor(.6,0,"lifLevelTicks",1);
            sleep(200);
            wheels.moveForward(-2,newGlobalConfig.slow,1);
            sleep(200);
        }
        if(tagOfInterest.id == MIDDLE) {
            //trajectory for parking in MIDDLE
            claw.grab();           lift.runViperMotor(1,1,"coneLevelTicks",1);
            sleep(50);
            wheels.moveRight(49, newGlobalConfig.medium, 2.0);
            lift.runViperMotor(.6,2,"lifLevelTicks",1);
            sleep(1500);
            wheels.moveForward(3.25,newGlobalConfig.slow,1);
            sleep(1500);
            lift.runViperMotor(.6,5,"lifLevelTicks",1);
            sleep(400);
            claw.release();
            wheels.moveForward(-3.5,newGlobalConfig.slow,1);
            sleep(200);
            lift.runViperMotor(.6,0,"lifLevelTicks",1);
            sleep(1000);
            wheels.moveForward(-1,newGlobalConfig.slow,1);
            sleep(200);

        }
        if(tagOfInterest.id == LEFT){
            //trajectory for parking in LEFT
            claw.grab();
            lift.runViperMotor(1,1,"coneLevelTicks",1);
            sleep(50);
            wheels.moveRight(34, newGlobalConfig.veryfast, 2.0);
            wheels.moveForward(26,newGlobalConfig.medium,2.0);
            wheels.turnClockwise(180,newGlobalConfig.medium,1.42);
            wheels.moveLeft(14,newGlobalConfig.medium,2);
            lift.runViperMotor(.6,2,"lifLevelTicks",1);
            wheels.moveForward(3000,newGlobalConfig.veryslow,1);
            lift.runViperMotor(.6,5,"lifLevelTicks",1);
            sleep(500);
            claw.release();
            wheels.moveForward(-3.5,newGlobalConfig.slow,1);
            sleep(200);
            lift.runViperMotor(.6,0,"lifLevelTicks",1);
            sleep(1000);
            wheels.moveForward(-2,newGlobalConfig.slow,1);
            sleep(200);

        }

    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        if(distanceSensorBack.getDistance(DistanceUnit.INCH) >= 27.0 && distanceSensorBack.getDistance(DistanceUnit.INCH) <= 27.1) {
            telemetry.addLine(String.format("\nDistance Back=%.01f inch", distanceSensorBack.getDistance(DistanceUnit.INCH)));
            telemetry.addLine("Robot Placement: Ur Good ma G");
        }
        else {
            telemetry.addLine(String.format("\nDistance Back=%.01f inch", distanceSensorBack.getDistance(DistanceUnit.INCH)));
            telemetry.addLine("Robot Placement: Fix it, watcha doin");
        }
    }

}


