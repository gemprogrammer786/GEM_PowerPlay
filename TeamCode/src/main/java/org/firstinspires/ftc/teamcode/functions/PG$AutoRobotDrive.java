package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;
import org.firstinspires.ftc.teamcode.components.PG$MecanumDriveFourWheels;


public class PG$AutoRobotDrive extends PG$MecanumDriveFourWheels{

    //private HardwareMap hardwareMap;
    //private Telemetry telemetry;
    PG$MecanumDriveFourWheels robot;

    public PG$AutoRobotDrive(HardwareMap hardwareMap,Telemetry telemetry){
        parent.hardwareMap=hardwareMap;
        parent.telemetry=telemetry;
        robot = new PG$MecanumDriveFourWheels(hardwareMap, telemetry );

    }


   // PG$MecanumDriveFourWheels robot = new PG$MecanumDriveFourWheels(hardwareMap, telemetry );
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    private ElapsedTime runtime = new ElapsedTime();


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

            frontleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
            frontright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
            backleft.setPower(speed* newGlobalConfig.leftWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);
            backright.setPower(speed* newGlobalConfig.rightWheelErrorAdjustment * newGlobalConfig.robotAutonomusSpeedReducer);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontleft.isBusy() || frontright.isBusy() || backleft.isBusy() || backright.isBusy())) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d  :%7d :%7d :%7d", new_frontLeftTarget, new_frontRightTarget, new_backLeftTarget, new_backRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
                        frontleft.getCurrentPosition(),
                        frontright.getCurrentPosition(),
                        backleft.getCurrentPosition(),
                        backright.getCurrentPosition());
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

        //  sleep(250);   // optional pause after each move
    }



}
