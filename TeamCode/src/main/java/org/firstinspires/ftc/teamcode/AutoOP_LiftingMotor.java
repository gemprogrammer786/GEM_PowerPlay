package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Mecanum Drive", group="GEMBot")

public class AutoOP_LiftingMotor extends LinearOpMode{
    DcMotor liftMotor;

    public void runOpMode() {
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");

        liftMotor.setDirection(DcMotor.Direction.FORWARD);

    }

    // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

    // Wait for the game to start (driver presses PLAY)
    waitForStart();

    public void lowSpring() {

    }
}
