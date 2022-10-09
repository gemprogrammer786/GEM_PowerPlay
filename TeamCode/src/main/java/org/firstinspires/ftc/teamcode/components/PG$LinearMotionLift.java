package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PG$LinearMotionLift {
    //Configuration used: 6wheelConfig
    public DcMotorEx viperMotor;

    public double motorErrorAdjustment = 1.0;

    public boolean IsAutonomous = false;

    public double mecanumWheelCircumference = 11.87; //inches

    public LinearOpMode parent;

    public int velocity = 200;

    private ElapsedTime runtime = new ElapsedTime();

    public Telemetry telemetry;

    public PG$LinearMotionLift(HardwareMap hardwareMap) {
        viperMotor = hardwareMap.get(DcMotorEx.class,"viperMotor");
    }

    //initialize for TeleOp
    public void initialize() {
        double reset = 0;
        viperMotor.setPower(reset);
        viperMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        viperMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void encoderDrive(double speed, double viperMotorInches, double timeoutS) {
        int new_liftTarget;

        double ticksPerInchMecanum = (537.7 / mecanumWheelCircumference);
        // Ensure that the opmode is still active
        if (parent.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            new_liftTarget = viperMotor.getCurrentPosition() + (int) (viperMotorInches * ticksPerInchMecanum);
            viperMotor.setTargetPosition(new_liftTarget);

            // Turn On RUN_TO_POSITION
            viperMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            viperMotor.setPower(speed*motorErrorAdjustment);


            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (viperMotor.isBusy() )) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d", new_liftTarget);
                telemetry.addData("Path2", "Running at %7d", viperMotor.getCurrentPosition());
                telemetry.update();
            }
        }
        // Stop all motion;
        viperMotor.setPower(0);

        // Turn off RUN_TO_POSITION
        viperMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //  sleep(250);   // optional pause after each move
    }

    public void move(double lefty, double righty, double leftx, double rightx){

//        frontright.setPower((-lefty  +rightx - leftx)*rightErrorAdjustment); // should work same as above
//        frontleft.setPower((lefty + rightx - leftx)*leftErrorAdjustment);
//        backright.setPower((-lefty + rightx + leftx)*rightErrorAdjustment);
//        backleft.setPower((lefty + rightx + leftx)*leftErrorAdjustment);

    }
}
