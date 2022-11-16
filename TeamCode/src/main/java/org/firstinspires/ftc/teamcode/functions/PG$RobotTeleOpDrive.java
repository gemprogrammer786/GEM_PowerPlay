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

    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    private ElapsedTime runtime = new ElapsedTime();


    public void move(double y,  double x, double rx){

        y *= newGlobalConfig.teleOpdrivePowerfactor;
        x *= newGlobalConfig.teleOpdrivePowerfactor;
        rx *= newGlobalConfig.teleOpdrivePowerfactor;

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

        frontleft.setPower(frontLeftPower);
        backleft.setPower(backLeftPower);
        frontright.setPower(frontRightPower);
        backright.setPower(backRightPower);

    }

    public void moveNoIMU(double y, double x, double rx){
         y *=newGlobalConfig.teleOpdrivePowerfactor; // Remember, this is reversed!
         x *= newGlobalConfig.teleOpdrivePowerfactor * 1.1 ; // Counteract imperfect strafing
         rx *=newGlobalConfig.teleOpdrivePowerfactor;




        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx ) / denominator;
        double backLeftPower = (y - x + rx ) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;


        frontleft.setPower(frontLeftPower);
        backleft.setPower(backLeftPower);
        frontright.setPower(frontRightPower);
        backright.setPower(backRightPower);


        telemetry.update();

    }


}
