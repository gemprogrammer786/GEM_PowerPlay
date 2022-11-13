package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;
import org.firstinspires.ftc.teamcode.components.PG$MecanumDriveFourWheels;


public class PG$RobotAutoDrive extends PG$MecanumDriveFourWheels{

    HardwareMap hardwareMap;
    Telemetry telemetry;
    private ElapsedTime runtime = new ElapsedTime();
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    // PG$MecanumDriveFourWheels robot = new PG$MecanumDriveFourWheels(hardwareMap, telemetry );


    public PG$RobotAutoDrive(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
        this.hardwareMap=hardwareMap;
        this.telemetry=telemetry;
        //robot = new PG$MecanumDriveFourWheels(hardwareMap, telemetry );

    }


    public void encoderDrive(double speed,
                             double frontLeftInches, double backLeftInches, double frontRightInches,
                             double backRightInches, double timeoutS) {
        int new_frontLeftTarget;
        int new_frontRightTarget;
        int new_backLeftTarget;
        int new_backRightTarget;
        double ticksPerInchMecanum = (537.7 /  newGlobalConfig.mecanumWheelCircumference);
        // Ensure that the opmode is still active
        if (parent.opModeIsActive()) {

            //telemetry.setAutoClear(false);
            Telemetry.Item telemetrygetCurrentPosition = telemetry.addData("frontleft.getCurrentPosition()", frontleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPosition = telemetry.addData("frontleft.getTargetPosition()", frontleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("frontleft.getPower()", frontleft.getPower());


            Telemetry.Item telemetrygetCurrentPositionFrontright = telemetry.addData("frontright.getCurrentPosition()", frontright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionFrontright = telemetry.addData("frontright.getTargetPosition()", frontright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionFrontright = telemetry.addData("frontright.getPower()", frontright.getPower());

            Telemetry.Item telemetrygetCurrentPositionBacktleft = telemetry.addData("backleft.getCurrentPosition()", backleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackleft = telemetry.addData("backleft.getTargetPosition()", backleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBacktleft = telemetry.addData("backleft.getPower()", backleft.getPower());

            Telemetry.Item telemetrygetCurrentPositionBackright = telemetry.addData("backright.getCurrentPosition()", backright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackright = telemetry.addData("backright.getTargetPosition()", backright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBackright = telemetry.addData("backright.getPower()", backright.getPower());


            telemetry.update();


            // Determine new target position, and pass to motor controller
            new_frontLeftTarget =  frontleft.getCurrentPosition() + (int) (frontLeftInches * ticksPerInchMecanum);
            new_frontRightTarget =  frontright.getCurrentPosition() + (int) (frontRightInches * ticksPerInchMecanum);
            new_backLeftTarget =  backleft.getCurrentPosition() + (int) (backLeftInches * ticksPerInchMecanum);
            new_backRightTarget =  backright.getCurrentPosition() + (int) (backRightInches * ticksPerInchMecanum);

            frontleft.setTargetPosition(new_frontLeftTarget);
            frontright.setTargetPosition(new_frontRightTarget);
            backleft.setTargetPosition(new_backLeftTarget);
            backright.setTargetPosition(new_backRightTarget);

            // Turn On RUN_TO_POSITION
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            frontright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));

//            frontleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            frontright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontleft.isBusy() || frontright.isBusy() || backleft.isBusy() || backright.isBusy())) {
                // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d  :%7d :%7d :%7d", new_frontLeftTarget, new_frontRightTarget, new_backLeftTarget, new_backRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
//                        frontleft.getCurrentPosition(),
//                        frontright.getCurrentPosition(),
//                        backleft.getCurrentPosition(),
//                        backright.getCurrentPosition());
                 telemetrygetCurrentPosition.setValue( frontleft.getCurrentPosition());
                 telemetrygetTargetPosition.setValue( frontleft.getTargetPosition());
                 telemetryliftCurrentPosition.setValue( frontleft.getPower());


                 telemetrygetCurrentPositionFrontright.setValue( frontright.getCurrentPosition());
                 telemetrygetTargetPositionFrontright.setValue( frontright.getTargetPosition());
                 telemetryliftCurrentPositionFrontright.setValue( frontright.getPower());

                 telemetrygetCurrentPositionBacktleft.setValue(backleft.getCurrentPosition());
                 telemetrygetTargetPositionBackleft.setValue( backleft.getTargetPosition());
                 telemetryliftCurrentPositionBacktleft.setValue( backleft.getPower());

                 telemetrygetCurrentPositionBackright.setValue( backright.getCurrentPosition());
                 telemetrygetTargetPositionBackright.setValue( backright.getTargetPosition());
                 telemetryliftCurrentPositionBackright.setValue(backright.getPower());

                telemetry.update();
            }
        }
        // Stop all motion;
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        // Turn off RUN_TO_POSITION
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

          parent.sleep(250);   // optional pause after each move
    }



    private int lfPos; private int rfPos; private int lrPos; private int rrPos;

    public void echoDrive(int distance, double speed, double timeoutS) {



        // fetch motor positions
        int new_frontLeftTarget = frontleft.getCurrentPosition();
        int new_frontRightTarget = frontright.getCurrentPosition();
        int new_backLeftTarget = backleft.getCurrentPosition();
        int new_backRightTarget = backright.getCurrentPosition();

        // calculate new targets
        new_frontLeftTarget += distance * newGlobalConfig.clicksPerInch;
        new_frontRightTarget += distance * newGlobalConfig.clicksPerInch;
        new_backLeftTarget += distance * newGlobalConfig.clicksPerInch;
        new_backRightTarget += distance * newGlobalConfig.clicksPerInch;



        if (parent.opModeIsActive()) {

            //telemetry.setAutoClear(false);
            Telemetry.Item telemetrygetCurrentPosition = telemetry.addData("frontleft.getCurrentPosition()", frontleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPosition = telemetry.addData("frontleft.getTargetPosition()", frontleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("frontleft.getPower()", frontleft.getPower());


            Telemetry.Item telemetrygetCurrentPositionFrontright = telemetry.addData("frontright.getCurrentPosition()", frontright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionFrontright = telemetry.addData("frontright.getTargetPosition()", frontright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionFrontright = telemetry.addData("frontright.getPower()", frontright.getPower());

            Telemetry.Item telemetrygetCurrentPositionBacktleft = telemetry.addData("backleft.getCurrentPosition()", backleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackleft = telemetry.addData("backleft.getTargetPosition()", backleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBacktleft = telemetry.addData("backleft.getPower()", backleft.getPower());

            Telemetry.Item telemetrygetCurrentPositionBackright = telemetry.addData("backright.getCurrentPosition()", backright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackright = telemetry.addData("backright.getTargetPosition()", backright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBackright = telemetry.addData("backright.getPower()", backright.getPower());


            telemetry.update();

            frontleft.setTargetPosition(new_frontLeftTarget);
            frontright.setTargetPosition(new_frontRightTarget);
            backleft.setTargetPosition(new_backLeftTarget);
            backright.setTargetPosition(new_backRightTarget);

            // Turn On RUN_TO_POSITION
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            frontright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));

//            frontleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            frontright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontleft.isBusy() || frontright.isBusy() || backleft.isBusy() || backright.isBusy())) {
                // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d  :%7d :%7d :%7d", new_frontLeftTarget, new_frontRightTarget, new_backLeftTarget, new_backRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
//                        frontleft.getCurrentPosition(),
//                        frontright.getCurrentPosition(),
//                        backleft.getCurrentPosition(),
//                        backright.getCurrentPosition());
                telemetrygetCurrentPosition.setValue( frontleft.getCurrentPosition());
                telemetrygetTargetPosition.setValue( frontleft.getTargetPosition());
                telemetryliftCurrentPosition.setValue( frontleft.getPower());


                telemetrygetCurrentPositionFrontright.setValue( frontright.getCurrentPosition());
                telemetrygetTargetPositionFrontright.setValue( frontright.getTargetPosition());
                telemetryliftCurrentPositionFrontright.setValue( frontright.getPower());

                telemetrygetCurrentPositionBacktleft.setValue(backleft.getCurrentPosition());
                telemetrygetTargetPositionBackleft.setValue( backleft.getTargetPosition());
                telemetryliftCurrentPositionBacktleft.setValue( backleft.getPower());

                telemetrygetCurrentPositionBackright.setValue( backright.getCurrentPosition());
                telemetrygetTargetPositionBackright.setValue( backright.getTargetPosition());
                telemetryliftCurrentPositionBackright.setValue(backright.getPower());

                telemetry.update();
            }
        }
        // Stop all motion;
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        // Turn off RUN_TO_POSITION
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        parent.sleep(250);   // optional pause after each move
    }


    public void moveForward(double distance, double speed, double timeoutS) {



        // fetch motor positions
        int new_frontLeftTarget = frontleft.getCurrentPosition();
        int new_frontRightTarget = frontright.getCurrentPosition();
        int new_backLeftTarget = backleft.getCurrentPosition();
        int new_backRightTarget = backright.getCurrentPosition();

        // calculate new targets
        new_frontLeftTarget += distance * newGlobalConfig.clicksPerInch;
        new_frontRightTarget += distance * newGlobalConfig.clicksPerInch;
        new_backLeftTarget += distance * newGlobalConfig.clicksPerInch;
        new_backRightTarget += distance * newGlobalConfig.clicksPerInch;



        if (parent.opModeIsActive()) {

            //telemetry.setAutoClear(false);
            Telemetry.Item telemetrygetCurrentPosition = telemetry.addData("frontleft.getCurrentPosition()", frontleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPosition = telemetry.addData("frontleft.getTargetPosition()", frontleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("frontleft.getPower()", frontleft.getPower());


            Telemetry.Item telemetrygetCurrentPositionFrontright = telemetry.addData("frontright.getCurrentPosition()", frontright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionFrontright = telemetry.addData("frontright.getTargetPosition()", frontright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionFrontright = telemetry.addData("frontright.getPower()", frontright.getPower());

            Telemetry.Item telemetrygetCurrentPositionBacktleft = telemetry.addData("backleft.getCurrentPosition()", backleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackleft = telemetry.addData("backleft.getTargetPosition()", backleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBacktleft = telemetry.addData("backleft.getPower()", backleft.getPower());

            Telemetry.Item telemetrygetCurrentPositionBackright = telemetry.addData("backright.getCurrentPosition()", backright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackright = telemetry.addData("backright.getTargetPosition()", backright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBackright = telemetry.addData("backright.getPower()", backright.getPower());


            telemetry.update();

            frontleft.setTargetPosition(new_frontLeftTarget);
            frontright.setTargetPosition(new_frontRightTarget);
            backleft.setTargetPosition(new_backLeftTarget);
            backright.setTargetPosition(new_backRightTarget);

            // Turn On RUN_TO_POSITION
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            frontright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));

//            frontleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            frontright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontleft.isBusy() || frontright.isBusy() || backleft.isBusy() || backright.isBusy())) {
                // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d  :%7d :%7d :%7d", new_frontLeftTarget, new_frontRightTarget, new_backLeftTarget, new_backRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
//                        frontleft.getCurrentPosition(),
//                        frontright.getCurrentPosition(),
//                        backleft.getCurrentPosition(),
//                        backright.getCurrentPosition());
                telemetrygetCurrentPosition.setValue( frontleft.getCurrentPosition());
                telemetrygetTargetPosition.setValue( frontleft.getTargetPosition());
                telemetryliftCurrentPosition.setValue( frontleft.getPower());


                telemetrygetCurrentPositionFrontright.setValue( frontright.getCurrentPosition());
                telemetrygetTargetPositionFrontright.setValue( frontright.getTargetPosition());
                telemetryliftCurrentPositionFrontright.setValue( frontright.getPower());

                telemetrygetCurrentPositionBacktleft.setValue(backleft.getCurrentPosition());
                telemetrygetTargetPositionBackleft.setValue( backleft.getTargetPosition());
                telemetryliftCurrentPositionBacktleft.setValue( backleft.getPower());

                telemetrygetCurrentPositionBackright.setValue( backright.getCurrentPosition());
                telemetrygetTargetPositionBackright.setValue( backright.getTargetPosition());
                telemetryliftCurrentPositionBackright.setValue(backright.getPower());

                telemetry.update();
            }
        }
        // Stop all motion;
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        // Turn off RUN_TO_POSITION
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        parent.sleep(250);   // optional pause after each move
    }

    public void moveRight(double distance, double speed, double timeoutS) {



        // fetch motor positions
        int new_frontLeftTarget = frontleft.getCurrentPosition();
        int new_frontRightTarget = frontright.getCurrentPosition();
        int new_backLeftTarget = backleft.getCurrentPosition();
        int new_backRightTarget = backright.getCurrentPosition();

        // calculate new targets
        new_frontLeftTarget += distance * newGlobalConfig.clicksPerInch;
        new_frontRightTarget -= distance * newGlobalConfig.clicksPerInch;
        new_backLeftTarget -= distance * newGlobalConfig.clicksPerInch;
        new_backRightTarget += distance * newGlobalConfig.clicksPerInch;





        if (parent.opModeIsActive()) {

            //telemetry.setAutoClear(false);
            Telemetry.Item telemetrygetCurrentPosition = telemetry.addData("frontleft.getCurrentPosition()", frontleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPosition = telemetry.addData("frontleft.getTargetPosition()", frontleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("frontleft.getPower()", frontleft.getPower());


            Telemetry.Item telemetrygetCurrentPositionFrontright = telemetry.addData("frontright.getCurrentPosition()", frontright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionFrontright = telemetry.addData("frontright.getTargetPosition()", frontright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionFrontright = telemetry.addData("frontright.getPower()", frontright.getPower());

            Telemetry.Item telemetrygetCurrentPositionBacktleft = telemetry.addData("backleft.getCurrentPosition()", backleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackleft = telemetry.addData("backleft.getTargetPosition()", backleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBacktleft = telemetry.addData("backleft.getPower()", backleft.getPower());

            Telemetry.Item telemetrygetCurrentPositionBackright = telemetry.addData("backright.getCurrentPosition()", backright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackright = telemetry.addData("backright.getTargetPosition()", backright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBackright = telemetry.addData("backright.getPower()", backright.getPower());


            telemetry.update();

            frontleft.setTargetPosition(new_frontLeftTarget);
            frontright.setTargetPosition(new_frontRightTarget);
            backleft.setTargetPosition(new_backLeftTarget);
            backright.setTargetPosition(new_backRightTarget);

            // Turn On RUN_TO_POSITION
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            frontright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));

//            frontleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            frontright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontleft.isBusy() || frontright.isBusy() || backleft.isBusy() || backright.isBusy())) {
                // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d  :%7d :%7d :%7d", new_frontLeftTarget, new_frontRightTarget, new_backLeftTarget, new_backRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
//                        frontleft.getCurrentPosition(),
//                        frontright.getCurrentPosition(),
//                        backleft.getCurrentPosition(),
//                        backright.getCurrentPosition());
                telemetrygetCurrentPosition.setValue( frontleft.getCurrentPosition());
                telemetrygetTargetPosition.setValue( frontleft.getTargetPosition());
                telemetryliftCurrentPosition.setValue( frontleft.getPower());


                telemetrygetCurrentPositionFrontright.setValue( frontright.getCurrentPosition());
                telemetrygetTargetPositionFrontright.setValue( frontright.getTargetPosition());
                telemetryliftCurrentPositionFrontright.setValue( frontright.getPower());

                telemetrygetCurrentPositionBacktleft.setValue(backleft.getCurrentPosition());
                telemetrygetTargetPositionBackleft.setValue( backleft.getTargetPosition());
                telemetryliftCurrentPositionBacktleft.setValue( backleft.getPower());

                telemetrygetCurrentPositionBackright.setValue( backright.getCurrentPosition());
                telemetrygetTargetPositionBackright.setValue( backright.getTargetPosition());
                telemetryliftCurrentPositionBackright.setValue(backright.getPower());

                telemetry.update();
            }
        }
        // Stop all motion;
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        // Turn off RUN_TO_POSITION
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        parent.sleep(250);   // optional pause after each move
    }

    public void moveLeft(double distance, double speed, double timeoutS) {



        // fetch motor positions
        int new_frontLeftTarget = frontleft.getCurrentPosition();
        int new_frontRightTarget = frontright.getCurrentPosition();
        int new_backLeftTarget = backleft.getCurrentPosition();
        int new_backRightTarget = backright.getCurrentPosition();

        // calculate new targets
        new_frontLeftTarget -= distance * newGlobalConfig.clicksPerInch;
        new_frontRightTarget += distance * newGlobalConfig.clicksPerInch;
        new_backLeftTarget += distance * newGlobalConfig.clicksPerInch;
        new_backRightTarget -= distance * newGlobalConfig.clicksPerInch;





        if (parent.opModeIsActive()) {

            //telemetry.setAutoClear(false);
            Telemetry.Item telemetrygetCurrentPosition = telemetry.addData("frontleft.getCurrentPosition()", frontleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPosition = telemetry.addData("frontleft.getTargetPosition()", frontleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("frontleft.getPower()", frontleft.getPower());


            Telemetry.Item telemetrygetCurrentPositionFrontright = telemetry.addData("frontright.getCurrentPosition()", frontright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionFrontright = telemetry.addData("frontright.getTargetPosition()", frontright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionFrontright = telemetry.addData("frontright.getPower()", frontright.getPower());

            Telemetry.Item telemetrygetCurrentPositionBacktleft = telemetry.addData("backleft.getCurrentPosition()", backleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackleft = telemetry.addData("backleft.getTargetPosition()", backleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBacktleft = telemetry.addData("backleft.getPower()", backleft.getPower());

            Telemetry.Item telemetrygetCurrentPositionBackright = telemetry.addData("backright.getCurrentPosition()", backright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackright = telemetry.addData("backright.getTargetPosition()", backright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBackright = telemetry.addData("backright.getPower()", backright.getPower());


            telemetry.update();

            frontleft.setTargetPosition(new_frontLeftTarget);
            frontright.setTargetPosition(new_frontRightTarget);
            backleft.setTargetPosition(new_backLeftTarget);
            backright.setTargetPosition(new_backRightTarget);

            // Turn On RUN_TO_POSITION
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            frontright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));

//            frontleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            frontright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontleft.isBusy() || frontright.isBusy() || backleft.isBusy() || backright.isBusy())) {
                // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d  :%7d :%7d :%7d", new_frontLeftTarget, new_frontRightTarget, new_backLeftTarget, new_backRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
//                        frontleft.getCurrentPosition(),
//                        frontright.getCurrentPosition(),
//                        backleft.getCurrentPosition(),
//                        backright.getCurrentPosition());
                telemetrygetCurrentPosition.setValue( frontleft.getCurrentPosition());
                telemetrygetTargetPosition.setValue( frontleft.getTargetPosition());
                telemetryliftCurrentPosition.setValue( frontleft.getPower());


                telemetrygetCurrentPositionFrontright.setValue( frontright.getCurrentPosition());
                telemetrygetTargetPositionFrontright.setValue( frontright.getTargetPosition());
                telemetryliftCurrentPositionFrontright.setValue( frontright.getPower());

                telemetrygetCurrentPositionBacktleft.setValue(backleft.getCurrentPosition());
                telemetrygetTargetPositionBackleft.setValue( backleft.getTargetPosition());
                telemetryliftCurrentPositionBacktleft.setValue( backleft.getPower());

                telemetrygetCurrentPositionBackright.setValue( backright.getCurrentPosition());
                telemetrygetTargetPositionBackright.setValue( backright.getTargetPosition());
                telemetryliftCurrentPositionBackright.setValue(backright.getPower());

                telemetry.update();
            }
        }
        // Stop all motion;
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        // Turn off RUN_TO_POSITION
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        parent.sleep(250);   // optional pause after each move
    }

    public void turnClockwise(double whatAngle, double speed, double timeoutS) {



        // fetch motor positions
        int new_frontLeftTarget = frontleft.getCurrentPosition();
        int new_frontRightTarget = frontright.getCurrentPosition();
        int new_backLeftTarget = backleft.getCurrentPosition();
        int new_backRightTarget = backright.getCurrentPosition();

        // calculate new targets
        new_frontLeftTarget += whatAngle * newGlobalConfig.clicksPerInch;
        new_frontRightTarget -= whatAngle * newGlobalConfig.clicksPerInch;
        new_backLeftTarget += whatAngle * newGlobalConfig.clicksPerInch;
        new_backRightTarget -= whatAngle * newGlobalConfig.clicksPerInch;




        if (parent.opModeIsActive()) {

            //telemetry.setAutoClear(false);
            Telemetry.Item telemetrygetCurrentPosition = telemetry.addData("frontleft.getCurrentPosition()", frontleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPosition = telemetry.addData("frontleft.getTargetPosition()", frontleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("frontleft.getPower()", frontleft.getPower());


            Telemetry.Item telemetrygetCurrentPositionFrontright = telemetry.addData("frontright.getCurrentPosition()", frontright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionFrontright = telemetry.addData("frontright.getTargetPosition()", frontright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionFrontright = telemetry.addData("frontright.getPower()", frontright.getPower());

            Telemetry.Item telemetrygetCurrentPositionBacktleft = telemetry.addData("backleft.getCurrentPosition()", backleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackleft = telemetry.addData("backleft.getTargetPosition()", backleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBacktleft = telemetry.addData("backleft.getPower()", backleft.getPower());

            Telemetry.Item telemetrygetCurrentPositionBackright = telemetry.addData("backright.getCurrentPosition()", backright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackright = telemetry.addData("backright.getTargetPosition()", backright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBackright = telemetry.addData("backright.getPower()", backright.getPower());


            telemetry.update();

            frontleft.setTargetPosition(new_frontLeftTarget);
            frontright.setTargetPosition(new_frontRightTarget);
            backleft.setTargetPosition(new_backLeftTarget);
            backright.setTargetPosition(new_backRightTarget);

            // Turn On RUN_TO_POSITION
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            frontright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));

//            frontleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            frontright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontleft.isBusy() || frontright.isBusy() || backleft.isBusy() || backright.isBusy())) {
                // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d  :%7d :%7d :%7d", new_frontLeftTarget, new_frontRightTarget, new_backLeftTarget, new_backRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
//                        frontleft.getCurrentPosition(),
//                        frontright.getCurrentPosition(),
//                        backleft.getCurrentPosition(),
//                        backright.getCurrentPosition());
                telemetrygetCurrentPosition.setValue( frontleft.getCurrentPosition());
                telemetrygetTargetPosition.setValue( frontleft.getTargetPosition());
                telemetryliftCurrentPosition.setValue( frontleft.getPower());


                telemetrygetCurrentPositionFrontright.setValue( frontright.getCurrentPosition());
                telemetrygetTargetPositionFrontright.setValue( frontright.getTargetPosition());
                telemetryliftCurrentPositionFrontright.setValue( frontright.getPower());

                telemetrygetCurrentPositionBacktleft.setValue(backleft.getCurrentPosition());
                telemetrygetTargetPositionBackleft.setValue( backleft.getTargetPosition());
                telemetryliftCurrentPositionBacktleft.setValue( backleft.getPower());

                telemetrygetCurrentPositionBackright.setValue( backright.getCurrentPosition());
                telemetrygetTargetPositionBackright.setValue( backright.getTargetPosition());
                telemetryliftCurrentPositionBackright.setValue(backright.getPower());

                telemetry.update();
            }
        }
        // Stop all motion;
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        // Turn off RUN_TO_POSITION
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        parent.sleep(250);   // optional pause after each move
    }

    public void turnAntiClockwise(double whatAngle, double speed, double timeoutS) {



        // fetch motor positions
        int new_frontLeftTarget = frontleft.getCurrentPosition();
        int new_frontRightTarget = frontright.getCurrentPosition();
        int new_backLeftTarget = backleft.getCurrentPosition();
        int new_backRightTarget = backright.getCurrentPosition();

        // calculate new targets
        new_frontLeftTarget -= whatAngle * newGlobalConfig.clicksPerInch;
        new_frontRightTarget += whatAngle * newGlobalConfig.clicksPerInch;
        new_backLeftTarget -= whatAngle * newGlobalConfig.clicksPerInch;
        new_backRightTarget += whatAngle * newGlobalConfig.clicksPerInch;




        if (parent.opModeIsActive()) {

            //telemetry.setAutoClear(false);
            Telemetry.Item telemetrygetCurrentPosition = telemetry.addData("frontleft.getCurrentPosition()", frontleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPosition = telemetry.addData("frontleft.getTargetPosition()", frontleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("frontleft.getPower()", frontleft.getPower());


            Telemetry.Item telemetrygetCurrentPositionFrontright = telemetry.addData("frontright.getCurrentPosition()", frontright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionFrontright = telemetry.addData("frontright.getTargetPosition()", frontright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionFrontright = telemetry.addData("frontright.getPower()", frontright.getPower());

            Telemetry.Item telemetrygetCurrentPositionBacktleft = telemetry.addData("backleft.getCurrentPosition()", backleft.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackleft = telemetry.addData("backleft.getTargetPosition()", backleft.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBacktleft = telemetry.addData("backleft.getPower()", backleft.getPower());

            Telemetry.Item telemetrygetCurrentPositionBackright = telemetry.addData("backright.getCurrentPosition()", backright.getCurrentPosition());
            Telemetry.Item telemetrygetTargetPositionBackright = telemetry.addData("backright.getTargetPosition()", backright.getTargetPosition());
            Telemetry.Item telemetryliftCurrentPositionBackright = telemetry.addData("backright.getPower()", backright.getPower());


            telemetry.update();

            frontleft.setTargetPosition(new_frontLeftTarget);
            frontright.setTargetPosition(new_frontRightTarget);
            backleft.setTargetPosition(new_backLeftTarget);
            backright.setTargetPosition(new_backRightTarget);

            // Turn On RUN_TO_POSITION
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            frontright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backleft.setPower(Range.clip((speed+ newGlobalConfig.leftWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));
            backright.setPower(Range.clip((speed+ newGlobalConfig.rightWheelErrorAdjustment) * newGlobalConfig.autodrivePowerfactor, -1, 1));

//            frontleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            frontright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
//            backright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontleft.isBusy() || frontright.isBusy() || backleft.isBusy() || backright.isBusy())) {
                // Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d  :%7d :%7d :%7d", new_frontLeftTarget, new_frontRightTarget, new_backLeftTarget, new_backRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
//                        frontleft.getCurrentPosition(),
//                        frontright.getCurrentPosition(),
//                        backleft.getCurrentPosition(),
//                        backright.getCurrentPosition());
                telemetrygetCurrentPosition.setValue( frontleft.getCurrentPosition());
                telemetrygetTargetPosition.setValue( frontleft.getTargetPosition());
                telemetryliftCurrentPosition.setValue( frontleft.getPower());


                telemetrygetCurrentPositionFrontright.setValue( frontright.getCurrentPosition());
                telemetrygetTargetPositionFrontright.setValue( frontright.getTargetPosition());
                telemetryliftCurrentPositionFrontright.setValue( frontright.getPower());

                telemetrygetCurrentPositionBacktleft.setValue(backleft.getCurrentPosition());
                telemetrygetTargetPositionBackleft.setValue( backleft.getTargetPosition());
                telemetryliftCurrentPositionBacktleft.setValue( backleft.getPower());

                telemetrygetCurrentPositionBackright.setValue( backright.getCurrentPosition());
                telemetrygetTargetPositionBackright.setValue( backright.getTargetPosition());
                telemetryliftCurrentPositionBackright.setValue(backright.getPower());

                telemetry.update();
            }
        }
        // Stop all motion;
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        // Turn off RUN_TO_POSITION
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        parent.sleep(250);   // optional pause after each move
    }

}
