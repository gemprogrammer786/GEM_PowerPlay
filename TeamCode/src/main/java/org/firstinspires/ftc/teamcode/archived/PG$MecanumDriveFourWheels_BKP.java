package org.firstinspires.ftc.teamcode.archived;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$MecanumDriveFourWheels_BKP {

    public boolean IsAutonomous = false;
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    public LinearOpMode parent;
    private ElapsedTime runtime = new ElapsedTime();
    public Telemetry telemetry;


    //Configuration used: 4wheelConfig
    public DcMotorEx frontright;
    public DcMotorEx frontleft;
    public DcMotorEx backright;
    public DcMotorEx backleft;
    public BNO055IMU imu;

    //public DcMotorEx xRail;




    public PG$MecanumDriveFourWheels_BKP(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry=telemetry;

        frontright = hardwareMap.get(DcMotorEx.class,newGlobalConfig.frontRightWheel);
        frontleft = hardwareMap.get(DcMotorEx.class,newGlobalConfig.frontLeftWheel);
        backright = hardwareMap.get(DcMotorEx.class, newGlobalConfig.backRightWheel);
        backleft = hardwareMap.get(DcMotorEx.class, newGlobalConfig.backLeftWheel);
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        double reset = 0;
        frontright.setPower(reset);
        frontleft.setPower(reset);
        backleft.setPower(reset);
        backright.setPower(reset);
        backright.setDirection(DcMotorSimple.Direction.REVERSE);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);
        backleft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontleft.setDirection(DcMotorSimple.Direction.FORWARD);


        if(IsAutonomous)
        {


            backright.setDirection(DcMotorSimple.Direction.REVERSE);
            frontright.setDirection(DcMotorSimple.Direction.FORWARD);
            backleft.setDirection(DcMotorSimple.Direction.FORWARD);
            frontleft.setDirection(DcMotorSimple.Direction.FORWARD);

            frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else{

            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            // Technically this is the default, however specifying it is clearer
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            // Without this, data retrieving from the IMU throws an exception
            imu.initialize(parameters);

            // Send telemetry message to signify robot waiting;
            // telemetry.addData(">", "Robot Ready.  Press Play.");    //
            // telemetry.update();

        }


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

    public void move(double lefty, double righty, double leftx, double rightx){


//        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
//        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
//        double rx = gamepad1.right_stick_x;

        //leftx = leftx*1.1; // Counteract imperfect strafing

        // Read inverse IMU heading, as the IMU heading is CW positive
        double botHeading = -imu.getAngularOrientation().firstAngle;

        double rotX = leftx * Math.cos(botHeading) - lefty * Math.sin(botHeading);
        double rotY = leftx * Math.sin(botHeading) + lefty * Math.cos(botHeading);

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(lefty) + Math.abs(leftx) + Math.abs(rightx), 1);
        double frontLeftPower = ((rotY + rotX + rightx) / denominator  * newGlobalConfig.robotTeleOpsSpeedReducer);
        double backLeftPower = ((rotY - rotX + rightx) / denominator * newGlobalConfig.robotTeleOpsSpeedReducer);
        double frontRightPower = ((rotY - rotX - rightx) / denominator * newGlobalConfig.robotTeleOpsSpeedReducer);
        double backRightPower = ((rotY + rotX - rightx) / denominator * newGlobalConfig.robotTeleOpsSpeedReducer);


       frontright.setPower(frontRightPower);
       frontleft.setPower(frontLeftPower);
       backright.setPower(backRightPower);
       backleft.setPower(backLeftPower);



        // Display it for the driver.
        telemetry.addData("frontRightPower", "Power Is to %.3f  :", frontRightPower);
        telemetry.addData("frontLeftPower", "Running at %.3f :", frontLeftPower);
        telemetry.addData("backRightPower", "Running to %.3f  :", backRightPower);
        telemetry.addData("backLeftPower", "Running at %.3f :", backLeftPower);

        telemetry.update();

//        frontright.setPower((-lefty  +rightx - leftx)*newGlobalConfig.rightWheelErrorAdjustment   * newGlobalConfig.robotTeleOpsSpeedReducer); // should work same as above
//        frontleft.setPower((lefty + rightx - leftx)*newGlobalConfig.leftWheelErrorAdjustment   * newGlobalConfig.robotTeleOpsSpeedReducer);
//        backright.setPower((-lefty + rightx + leftx)*newGlobalConfig.rightWheelErrorAdjustment   * newGlobalConfig.robotTeleOpsSpeedReducer);
//        backleft.setPower((lefty + rightx + leftx)*newGlobalConfig.leftWheelErrorAdjustment   * newGlobalConfig.robotTeleOpsSpeedReducer);

    }
}
