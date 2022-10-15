package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.PG$MecanumDriveFourWheels;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;


public class PG$TeleOpRobotDrive extends PG$MecanumDriveFourWheels{

    HardwareMap hardwareMap;
    Telemetry telemetry;



    public PG$TeleOpRobotDrive(HardwareMap hardwareMap,Telemetry telemetry){
        this.hardwareMap=hardwareMap;
        this.telemetry=telemetry;
    }



    PG$MecanumDriveFourWheels robot = new PG$MecanumDriveFourWheels( hardwareMap, telemetry );
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
        double frontLeftPower = (rotY + rotX + rightx) / denominator;
        double backLeftPower = (rotY - rotX + rightx) / denominator;
        double frontRightPower = (rotY - rotX - rightx) / denominator;
        double backRightPower = (rotY + rotX - rightx) / denominator;

        frontright.setPower(frontRightPower  * newGlobalConfig.robotTeleOpsSpeedReducer);
        frontleft.setPower(frontLeftPower  * newGlobalConfig.robotTeleOpsSpeedReducer);
        backright.setPower(backRightPower  * newGlobalConfig.robotTeleOpsSpeedReducer);
        backleft.setPower(backLeftPower  * newGlobalConfig.robotTeleOpsSpeedReducer);

        // frontright.setPower((-lefty  +rightx - leftx)*rightErrorAdjustment   * newGlobalConfig.robotTeleOpsSpeedReducer); // should work same as above
        // frontleft.setPower((lefty + rightx - leftx)*leftErrorAdjustment   * newGlobalConfig.robotTeleOpsSpeedReducer);
        // backright.setPower((-lefty + rightx + leftx)*rightErrorAdjustment   * newGlobalConfig.robotTeleOpsSpeedReducer);
        // backleft.setPower((lefty + rightx + leftx)*leftErrorAdjustment   * newGlobalConfig.robotTeleOpsSpeedReducer);

    }


}
