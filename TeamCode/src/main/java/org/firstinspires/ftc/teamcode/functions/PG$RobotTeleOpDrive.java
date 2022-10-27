package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

        lefty *= newGlobalConfig.teleOpdrivePowerfactor;
        righty *= newGlobalConfig.teleOpdrivePowerfactor;
        leftx *= newGlobalConfig.teleOpdrivePowerfactor;
        rightx *= newGlobalConfig.teleOpdrivePowerfactor;

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
        double frontLeftPower = ((rotY + rotX + rightx) / denominator);
        double backLeftPower = ((rotY - rotX + rightx) / denominator);
        double frontRightPower = ((rotY - rotX - rightx) / denominator);
        double backRightPower = ((rotY + rotX - rightx) / denominator);

//        frontright.setPower(frontRightPower);
//        frontleft.setPower(frontLeftPower);
//        backright.setPower(backRightPower);
//        backleft.setPower(backLeftPower);

        frontright.setPower(Range.clip(frontRightPower, -1, 1));
        frontleft.setPower(Range.clip(frontLeftPower, -1, 1));
        backright.setPower(Range.clip(backRightPower, -1, 1));
        backleft.setPower(Range.clip(backLeftPower, -1, 1));



        // Display it for the driver.
//        telemetry.addData("frontRightPower", "Power Is to %.3f  :", frontRightPower);
//        telemetry.addData("frontLeftPower", "Running at %.3f :", frontLeftPower);
//        telemetry.addData("backRightPower", "Running to %.3f  :", backRightPower);
//        telemetry.addData("backLeftPower", "Running at %.3f :", backLeftPower);

        telemetry.update();
    }

    public void moveNoIMU(double y, double x, double rx){
         y *=newGlobalConfig.teleOpdrivePowerfactor; // Remember, this is reversed!
         x *= newGlobalConfig.teleOpdrivePowerfactor * 1.1 ; // Counteract imperfect strafing
         rx *=newGlobalConfig.teleOpdrivePowerfactor;

//        double y = -gamepad1.left_stick_y *0.5; // Remember, this is reversed!
//        double x = gamepad1.left_stick_x * 1.1 *0.5; // Counteract imperfect strafing
//        double rx = gamepad1.right_stick_x *0.5;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx ) / denominator;
        double backLeftPower = (y - x + rx ) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontleft.setPower(frontLeftPower);
        backleft.setPower(backLeftPower);
        frontright.setPower(frontRightPower);
        backright.setPower(backRightPower);


        // Display it for the driver.
//        telemetry.addData("frontRightPower", "Power Is to %.3f  :", frontRightPower);
//        telemetry.addData("frontLeftPower", "Running at %.3f :", frontLeftPower);
//        telemetry.addData("backRightPower", "Running to %.3f  :", backRightPower);
//        telemetry.addData("backLeftPower", "Running at %.3f :", backLeftPower);

        telemetry.update();

    }


}
