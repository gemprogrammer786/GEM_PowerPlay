package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.PG$MecanumDriveFourWheels;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;


public class PG$RobotTeleOpDrive extends PG$MecanumDriveFourWheels{

    HardwareMap hardwareMap;
    Telemetry telemetry;

    public PG$RobotTeleOpDrive(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
        this.hardwareMap=hardwareMap;
        this.telemetry=telemetry;

    }

    //PG$MecanumDriveFourWheels robot = new PG$MecanumDriveFourWheels( hardwareMap, telemetry );
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    private ElapsedTime runtime = new ElapsedTime();


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
    }


}
