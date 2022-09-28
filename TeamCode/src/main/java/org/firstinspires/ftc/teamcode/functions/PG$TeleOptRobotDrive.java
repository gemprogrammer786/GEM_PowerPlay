package org.firstinspires.ftc.teamcode.functions;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;


public class PG$TeleOptRobotDrive {






    /* local OpMode members. */
    HardwareMap hwMap = null;
    Telemetry telemetry =null;
    private ElapsedTime period = new ElapsedTime();
    PG$GlobalConfig globalConfig = new PG$GlobalConfig(hwMap,telemetry);
    /* Constructor */
    public PG$TeleOptRobotDrive(HardwareMap ahwMap, Telemetry telemetry) {
        this.hwMap=ahwMap;
        this.telemetry=telemetry;
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap, Telemetry telemetry, Gamepad gamepad) {



        double y = -gamepad.left_stick_y; // Remember, this is reversed!
        double x = gamepad.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad.right_stick_x;

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
