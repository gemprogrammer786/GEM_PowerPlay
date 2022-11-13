/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/
package org.firstinspires.ftc.teamcode.strategy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.functions.PG$ClawOperator;
import org.firstinspires.ftc.teamcode.functions.PG$LinearOperator;
import org.firstinspires.ftc.teamcode.functions.PG$RobotAutoDrive;
import org.firstinspires.ftc.teamcode.functions.PG$TurnTableOperation;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;


@Autonomous(name="PG$AutonomusGEM_V1")

//@Disabled
public class PG$AutonomusGEM_V1 extends LinearOpMode {

    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();
    PG$RobotAutoDrive wheels;
    PG$ClawOperator claw;
    PG$LinearOperator lift;
    //PG$TurnTableOperation turnTable;


    @Override
    public void runOpMode() throws InterruptedException {

        wheels = new PG$RobotAutoDrive(hardwareMap,telemetry);
        wheels.parent = this;

//        turnTable= new PG$TurnTableOperation(hardwareMap,telemetry);
//        turnTable.parent = this;
//
        claw= new PG$ClawOperator(hardwareMap,telemetry);
        claw.parent = this;
//
        lift= new PG$LinearOperator(hardwareMap,telemetry);
        lift.parent = this;

        //telemetry.setAutoClear(false);
        Telemetry.Item telemetryDebug = telemetry.addData("Currenlty at", "PG$TeleGEMPrototype_V1 - runOpMode");
        telemetry.update();

        waitForStart();


    /*        wheels.encoderDrive(.5,10.0,10.0,10.0,10.0,2.0);
     */

        /* START OF FIRST CONE */
        claw.grab();
        wheels.moveRight(21,newGlobalConfig.medium,2.0);
        lift.runViperMotor(1,1,1);
        wheels.moveForward(7.5, newGlobalConfig.medium,2.0);
        sleep(200);
        claw.release();
        wheels.moveForward(-7.5, newGlobalConfig.medium,2.0);
        lift.runViperMotor(1,0,1);
        claw.grab();
        wheels.moveLeft(19,newGlobalConfig.medium,2.0);

        /* END OF FIRST CONE */
        /* START OF SECOND CONE */
        claw.release();
        wheels.moveForward(24, newGlobalConfig.medium,2.0);
        claw.grab();
        wheels.moveForward(-24, newGlobalConfig.medium,2.0);
        wheels.moveRight(43,newGlobalConfig.medium,2.0);
        lift.runViperMotor(1,2,1);
        wheels.moveForward(7.5, newGlobalConfig.medium,2.0);
        sleep(200);
        claw.release();
        wheels.moveForward(-7.5, newGlobalConfig.medium,2.0);
        lift.runViperMotor(1,0,1);
        claw.grab();
        wheels.moveLeft(-43,newGlobalConfig.medium,2.0);

        /* END OF SECOND CONE */



        /* END OF THIRD CONE */


       /*
        lift.runViperMotor(1,2,1);
        claw.release();

        wheels.encoderDrive(.5,-10.0,-10.0,-10.0,-10.0,2.0);
        lift.runViperMotor(1,0,1);
        claw.grab();

        wheels.encoderDrive(.5,-20.0,20.0,-20.0,20.0,2.0);



        wheels.turnClockwise(-45, newGlobalConfig.fast,2.0);
        wheels.moveForward(5, newGlobalConfig.fast,2.0);
        wheels.turnClockwise(-45, newGlobalConfig.fast,2.0);
        wheels.moveForward(6, newGlobalConfig.fast,2.0);

        wheels.moveForward(-6, newGlobalConfig.fast,2.0);
        wheels.turnClockwise(-3, newGlobalConfig.medium,2.0); // aiming tweak
        wheels.moveRight(5, newGlobalConfig.fast,2.0);

        wheels.moveForward(-5, newGlobalConfig.fast,2.0);
        wheels.turnClockwise(-135, newGlobalConfig.fast,2.0);
        wheels.moveForward(10, newGlobalConfig.fast,2.0);*/


    }
}


