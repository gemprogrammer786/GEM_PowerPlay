/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file illustrates the concept of driving a path based on time.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: RobotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backward for 1 Second
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@Autonomous(name="Mecanum Drive", group="GEMBot")
//@Disabled
public class AutoOp_MecanumDrive extends LinearOpMode {

    /* Declare OpMode members. */
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;

    static final double     MOTOR_SPEED = 0.5;

    @Override
    public void runOpMode() {

        // Initialize the drive system variables.
        leftMotorFront = hardwareMap.get(DcMotor.class, "leftmotor_front");
        rightMotorFront = hardwareMap.get(DcMotor.class, "rightmotor_front");
        leftMotorBack = hardwareMap.get(DcMotor.class, "leftmotor_back");
        rightMotorBack = hardwareMap.get(DcMotor.class, "rightmotor_back");

        leftMotorFront.setDirection(DcMotor.Direction.FORWARD);
        leftMotorBack.setDirection(DcMotor.Direction.REVERSE);
        rightMotorFront.setDirection(DcMotor.Direction.FORWARD);
        rightMotorBack.setDirection(DcMotor.Direction.FORWARD);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        while (opModeIsActive()) {
            //Move forward
            leftMotorFront.setPower(0.4);
            leftMotorBack.setPower(0.4);
            rightMotorFront.setPower(0.4);
            rightMotorBack.setPower(0.4);
            sleep(2000);

            //Move backward
            leftMotorFront.setPower(-0.4);
            leftMotorBack.setPower(-0.4);
            rightMotorFront.setPower(-0.4);
            rightMotorBack.setPower(-0.4);
            sleep(2000);

            //Move side right
            leftMotorFront.setPower(0.4);
            leftMotorBack.setPower(-0.4);
            rightMotorFront.setPower(-0.4);
            rightMotorBack.setPower(0.4);
            sleep(2000);

            //Move side left
            leftMotorFront.setPower(-0.4);
            leftMotorBack.setPower(0.4);
            rightMotorFront.setPower(0.4);
            rightMotorBack.setPower(-0.4);
            sleep(2000);

            //Move diagonal right forward
            /*leftMotorFront.setPower(0.5);
            leftMotorBack.setPower(0);
            rightMotorFront.setPower(0);
            rightMotorBack.setPower(0.5);
            sleep(500);

            //Move diagonal left backwards
            leftMotorFront.setPower(-0.5);
            leftMotorBack.setPower(0);
            rightMotorFront.setPower(0);
            rightMotorBack.setPower(-0.5);
            sleep(500);

            //Move diagonal right backward
            leftMotorFront.setPower(0);
            leftMotorBack.setPower(-0.5);
            rightMotorFront.setPower(-0.5);
            rightMotorBack.setPower(0);
            sleep(500);

            //Move diagonal left backward
            leftMotorFront.setPower(-0.5);
            leftMotorBack.setPower(0);
            rightMotorFront.setPower(0);
            rightMotorBack.setPower(-0.5);
            sleep(500);

            //Move clockwise
            leftMotorFront.setPower(0.5);
            leftMotorBack.setPower(0.5);
            rightMotorFront.setPower(-0.5);
            rightMotorBack.setPower(-0.5);
            sleep(500);

            //Move clockwise
            leftMotorFront.setPower(-0.5);
            leftMotorBack.setPower(-0.5);
            rightMotorFront.setPower(0.5);
            rightMotorBack.setPower(0.5);
            sleep(500); */

        }

    }
}
