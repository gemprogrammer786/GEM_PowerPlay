package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class touchSensor extends LinearOpMode {

    TouchSensor touchSensor_front = hardwareMap.get(TouchSensor.class, "touchSensor_front");

    @Override
    public void runOpMode() {

        waitForStart();

        while (opModeIsActive()) {
            if (touchSensor_front.isPressed()) {
                telemetry.addData("Path: ", "Touch Sensor is Pressed");
                telemetry.update();

                // Send telemetry message to signify robot waiting;
                telemetry.addData("Status", "Ready to run");    //
                telemetry.update();

                // Wait for the game to start (driver presses PLAY)
                waitForStart();

                while (opModeIsActive()) {
                    if (touchSensor_front.isPressed()) {
                        telemetry.addData("Path: ", "Touch Sensor is Pressed");
                        telemetry.update();

                    } else {
                        telemetry.addData("Path: ", "Waiting for it to be pressed");
                        telemetry.update();
                    }
                }
                telemetry.addData("Path", "Complete");
                telemetry.update();
                sleep(1000);

            }


        }

    }
}
