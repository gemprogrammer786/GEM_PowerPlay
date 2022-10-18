package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.PG$LinearMotion1;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$LinearOperator1 extends PG$LinearMotion1 {



    public LinearOpMode parent;

    public double motorErrorAdjustment = 1.0;
    public boolean IsAutonomous = false;
    public double linearGearCircumference = 6.2; //inches

    HardwareMap hardwareMap;
    Telemetry telemetry;

    private ElapsedTime runtime = new ElapsedTime();
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();

    public PG$LinearOperator1(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
        this.hardwareMap=hardwareMap;
        this.telemetry=telemetry;
    }

    private int newTargetPosition = 0;
    public int getNewTargetPosition() {
        return newTargetPosition;
    }
    public void setNewTargetPosition(int newTarget) {
        this.newTargetPosition = newTarget;
    }

    private int currentLevel =0; // private = restricted access
    public int getCurrentLevel() {
        return currentLevel;
    }
    public void setCurrentLevel(int newPosition) {
        this.currentLevel = newPosition;
    }


    public int levelToPostionCalc(int level) {
        int position = newGlobalConfig.lifLevel[0];

        if (level>=getCurrentLevel()){
            position=newGlobalConfig.lifLevel[level]- newGlobalConfig.lifLevel[getCurrentLevel()];
        } else{
            position=newGlobalConfig.lifLevel[level]- newGlobalConfig.lifLevel[getCurrentLevel()];
        }
        return(position);
    }


    public void runViperMotor(double speed, int liftLevel, double timeoutS) {
        int new_TargetPosition=0;

        //double ticksPerInchMecanum = (384.44 / linearGearCircumference);
        double ticksPerInchMecanum =1.0;
        // Ensure that the opmode is still active
        if (parent.opModeIsActive()) {
            Telemetry.Item telemetryliftPosition = telemetry.addData("Lift Position", linearLift.getCurrentPosition());
            telemetry.update();
            // Determine new target position, and pass to motor controller
            new_TargetPosition = (int)(levelToPostionCalc(liftLevel)* ticksPerInchMecanum);
            linearLift.setTargetPosition(new_TargetPosition);

            // Turn On RUN_TO_POSITION
            linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            linearLift.setPower(speed*motorErrorAdjustment);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (linearLift.isBusy() ) &&
                    (  linearLift.getTargetPosition() != linearLift.getCurrentPosition()*-1) &&
                    (-1*linearLift.getCurrentPosition() >= newGlobalConfig.lifLevel[0] || -1*linearLift.getCurrentPosition() <= newGlobalConfig.lifLevel[3] )) {
                telemetryliftPosition.setValue(linearLift.getCurrentPosition());
                telemetry.update();
            }
        }
        // Stop all motion;
        linearLift.setPower(0);

        // Turn off RUN_TO_POSITION
        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //  sleep(250);   // optional pause after each move

        telemetry.addData("Lift Current Position:",  "At %7d ", getCurrentLevel());
        telemetry.addData("Lift Target Position:",  "At %7d ", getNewTargetPosition());
        telemetry.addData("Lift New Lift Position:",  "At %7d ", new_TargetPosition);
        telemetry.addData("Lift Lift Position Request:",  "Running to %7d ", liftLevel);
        telemetry.addData("Motor Current Position", "Running at %7d", linearLift.getCurrentPosition());

        setCurrentLevel(liftLevel);
        setNewTargetPosition(new_TargetPosition);

        //armRight.getCurrentPosition();
        telemetry.update();
    }

    public void move(double lefty, double righty, double leftx, double rightx){

//        frontright.setPower((-lefty  +rightx - leftx)*rightErrorAdjustment); // should work same as above
//        frontleft.setPower((lefty + rightx - leftx)*leftErrorAdjustment);
//        backright.setPower((-lefty + rightx + leftx)*rightErrorAdjustment);
//        backleft.setPower((lefty + rightx + leftx)*leftErrorAdjustment);

    }


}
