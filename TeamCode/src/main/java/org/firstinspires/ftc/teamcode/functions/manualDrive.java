package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;




public class manualDrive  extends LinearOpMode {


    /* Declare OpMode members. */
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;

    // Retrieve the IMU from the hardware map
    BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();


    @Override
    public void runOpMode() {
        // Initialize the drive system variables.
        leftMotorFront = hardwareMap.get(DcMotor.class, "leftmotor_front");
        rightMotorFront = hardwareMap.get(DcMotor.class, "rightmotor_front");
        leftMotorBack = hardwareMap.get(DcMotor.class, "leftmotor_back");
        rightMotorBack = hardwareMap.get(DcMotor.class, "rightmotor_back");

        leftMotorFront.setDirection(DcMotor.Direction.FORWARD);
        leftMotorBack.setDirection(DcMotor.Direction.REVERSE);
        rightMotorFront.setDirection(DcMotor.Direction.FORWARD);
        rightMotorBack.setDirection(DcMotor.Direction.FORWARD);


        // Technically this is the default, however specifying it is clearer
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        // Without this, data retrieving from the IMU throws an exception
        imu.initialize(parameters);

        // Send telemetry message to signify robot waiting;
        telemetry.addData(">", "Robot Ready.  Press Play.");    //
        telemetry.update();

    }

    public void runRobot() {

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Read inverse IMU heading, as the IMU heading is CW positive
            double botHeading = -imu.getAngularOrientation().firstAngle;

            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
            double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            leftMotorFront.setPower(frontLeftPower);
            leftMotorBack.setPower(backLeftPower);
            rightMotorFront.setPower(frontRightPower);
            rightMotorBack.setPower(backRightPower);
}


}
