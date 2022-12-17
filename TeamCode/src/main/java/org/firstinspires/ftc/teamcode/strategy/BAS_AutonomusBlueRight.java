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
       // claw.grab();
        //sleep(50);
        //lift.runViperMotor(.6, afterGrabLevel,"coneLevelTicks", 1); // required to lift the cone to avoid any hitting to the object
        wheels.moveRight(77,newGlobalConfig.veryfast,3.0);
        //lift.runViperMotor(.6,3,"lifLevelTicks",1); // lift to the hight of small pole
       // wheels.moveForward(4.3, newGlobalConfig.veryslow,3.0);
        //lift.runViperMotor(.6, 0, "coneLevelTicks", 1); // To ensure that cone is not falling outside the pole
        //claw.release();
        //sleep(500);
        //wheels.moveForward(-4.6, newGlobalConfig.medium,3.0);
        wheels.moveLeft(16, newGlobalConfig.veryfast,3.0);
        // wheels.turnAntiClockwise(180,1,.897);
        // wheels.moveForward(26,newGlobalConfig.fast,3.0);

        /* END OF FIRST CONE */
        /* START OF SECOND CONE */
        //claw.release();
        //wheels.moveForward(24, newGlobalConfig.medium,2.0);
       /* sleep(200);
        lift.runViperMotor(1, 5, "coneLevelTicks", 1); // to lift the cone from the stack
        afterGrabLevel=6;
        claw.grab();
        sleep(150);
        lift.runViperMotor(1, afterGrabLevel,"coneLevelTicks", 1); // to ensure that its not tripping the stack down
        afterGrabLevel=1;
        wheels.moveForward(-26, newGlobalConfig.medium,2.0);
        wheels.turnAntiClockwise(180,1,.897);
        lift.runViperMotor(1,3,"liflevelticks",1);

        wheels.moveRight(12.5, newGlobalConfig.fast,10.0);
        wheels.moveForward(4.6, newGlobalConfig.veryslow,3.0);
        sleep(200);
        lift.runViperMotor(1, 0, "coneLevelTicks", 1); // To ensure that cone is not falling outside the pole
        claw.release();
        wheels.moveForward(-4.6, newGlobalConfig.medium,3.0);

        claw.grab();

        /* END OF SECOND CONE */




        if(tagOfInterest.id == RIGHT){
            //trajectory for parking in RIGHT
            //wheels.moveLeft(12,newGlobalConfig.medium,2.0);
            wheels.moveForward(-23.7, newGlobalConfig.veryfast,2.0);
            wheels.moveLeft(24,newGlobalConfig.veryfast,3.0);
        }

        if(tagOfInterest.id == MIDDLE) {
            //trajectory for parking in MIDDLE
            wheels.moveLeft(24, newGlobalConfig.veryfast, 2.0);
        }

        if(tagOfInterest.id == LEFT){
            //trajectory for parking in LEFT
            //wheels.moveLeft(12,newGlobalConfig.medium,2.0);
            wheels.moveForward(24, newGlobalConfig.veryfast,2.0);
            wheels.moveLeft(24,newGlobalConfig.veryfast,2);


        }

    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        if(distanceSensorBack.getDistance(DistanceUnit.INCH) >= 27.0 && distanceSensorBack.getDistance(DistanceUnit.INCH) <= 28.0) {
            telemetry.addLine(String.format("\nDistance Back=%.01f inch", distanceSensorBack.getDistance(DistanceUnit.INCH)));
            telemetry.addLine("Robot Placement: Ur Good ma G");
        }
        else {
            telemetry.addLine(String.format("\nDistance Back=%.01f inch", distanceSensorBack.getDistance(DistanceUnit.INCH)));
            telemetry.addLine("Robot Placement: Fix it, watcha doin");
        }
    }

}


