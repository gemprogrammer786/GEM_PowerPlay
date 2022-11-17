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


    public void moveNoIMU(double y, double x, double rx){
         y *=newGlobalConfig.teleOpdrivePowerfactor;
         x *= newGlobalConfig.teleOpdrivePowerfactor * 1.1 ;
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
    }


}
