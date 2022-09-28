package org.firstinspires.ftc.teamcode.global;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class PG$GlobalConfig {


    /* Declare OpMode members. */
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;

    /* local OpMode members. */
    HardwareMap hwMap = null;
    Telemetry telemetry = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public PG$GlobalConfig(HardwareMap ahwMap, Telemetry telemetry) {
        this.hwMap=ahwMap;
        this.telemetry=telemetry;
    }

    /* Initialize standard Hardware interfaces */
    public void initRobot() {

        // Retrieve the IMU from the hardware map
        BNO055IMU imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        // Initialize the drive system variables.
        leftMotorFront = hwMap.get(DcMotor.class, "leftmotor_front");
        rightMotorFront = hwMap.get(DcMotor.class, "rightmotor_front");
        leftMotorBack = hwMap.get(DcMotor.class, "leftmotor_back");
        rightMotorBack = hwMap.get(DcMotor.class, "rightmotor_back");

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
}
