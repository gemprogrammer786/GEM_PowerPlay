package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.PG$LinearMotion1;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$LinearOperator1 extends PG$LinearMotion1 {


//    //0 position
//    public double liftMin = 0.83;
//    //Level 1
//    public double liftLevel1 = 0.58;
//    //Level 2
//    public double liftLevel2 = 0.26;
//    //Level 3
//    public double liftLevel3 = 0.2;
//
//    public double liftPower = -0.2;
    public LinearOpMode parent;

    public int new_TargetPosition = 0;
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


    private int currentPosition=0; // private = restricted access

    public int getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(int newPosition) {
        this.currentPosition = newPosition;
    }



    public int setTargetPosition(int level) {
        int position = newGlobalConfig.liftMin;
        int currentPosition=getCurrentPosition();
        if (level>=currentPosition){
            position=newGlobalConfig.lifLevel[level]- newGlobalConfig.lifLevel[currentPosition];
        } else{
            position=-(newGlobalConfig.lifLevel[level]- newGlobalConfig.lifLevel[currentPosition]);
        }
        /*
        if(level == 1){
            position = newGlobalConfig.liftLevel1;

        }
        else if(level == 2){
            position = newGlobalConfig.liftLevel2;

        }
        else if(level == 3){
            position = newGlobalConfig.liftLevel3;

        }
*/
        new_TargetPosition = position;
        return(new_TargetPosition);
        /*
        if(level!=0) {
            linearLift.setTargetPosition(new_TargetPosition);
            parent.sleep(200);
        }
        else{

            parent.sleep(200);
            linearLift.setTargetPosition(new_TargetPosition);
        }

        if(position==0){
            linearLift.setPower(0.2);
            parent.sleep(200);
        }
        else {
            linearLift.setPower(0.6);
            //armRight.setPower(0.6);
        }
        telemetry.addData("Path1",  "Running to %7d ", new_TargetPosition);
        telemetry.addData("Path2",  "Running at %7d ",
                linearLift.getCurrentPosition());
        telemetry.update();

         */

    }

    /*
        public void lift(double speed, int direction,int limit) {
                int position=10;
                if(direction<0)
                    position = -1*position;

            new_frontLeftTarget += position;
            if(new_frontLeftTarget>=0 && new_frontLeftTarget<limit){
                linearLift.setTargetPosition(new_frontLeftTarget);
                linearLift.setPower(speed);
                parent.sleep(100);
            }
            else if(new_frontLeftTarget<0)
                new_frontLeftTarget =0;
            else if(new_frontLeftTarget > limit)
                new_frontLeftTarget = limit;
                telemetry.addData("Path1",  "Running to %7d ", new_frontLeftTarget);

                telemetry.addData("Path2",  "Running at %7d ",
                        linearLift.getCurrentPosition());
                        //armRight.getCurrentPosition();
                telemetry.update();

        }

    public void initiateLift(){
//        telemetry.addData("Postion lift 2:%d", linearLift.getCurrentPosition());
//        telemetry.update();
        linearLift.setPower(newGlobalConfig.liftPower);
        parent.sleep(1000);
        linearLift.setPower(0);
        telemetry.addData("Postion lift 2:%d", linearLift.getCurrentPosition());
        telemetry.update();
        linearLift.setPower(-newGlobalConfig.liftPower);
        parent.sleep(1000);
        linearLift.setPower(0);
        telemetry.addData("Postion lift 2:%d", linearLift.getCurrentPosition());
        telemetry.update();
    }
*/

    public void runViperMotor(double speed, int liftLevel, double timeoutS) {
        int new_liftTarget;

        //double ticksPerInchMecanum = (384.44 / linearGearCircumference);
        double ticksPerInchMecanum =5.0;
        // Ensure that the opmode is still active
        if (parent.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            //new_liftTarget = linearLift.getCurrentPosition() + (int) (numberRotation * ticksPerInchMecanum);
            new_liftTarget = (int)(setTargetPosition(liftLevel)* ticksPerInchMecanum);

            linearLift.setTargetPosition(new_liftTarget);
            telemetry.addData("Current Position-1",  "At %7d ", getCurrentPosition());
            telemetry.addData("Current Position-2",  "At %7d ", new_liftTarget);
            telemetry.update();
            parent.sleep(10000);

            // Turn On RUN_TO_POSITION
            linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            linearLift.setPower(speed*motorErrorAdjustment);


            // keep looping while we are still active, and there is time left, and both motors are running.
            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (linearLift.isBusy() )) {
                // Display it for the driver.
                telemetry.addData("Current Position",  "At %7d ", getCurrentPosition());
                telemetry.addData("New Position",  "Running to %7d ", liftLevel);
                telemetry.addData("Motor Current Position", "Running at %7d", linearLift.getCurrentPosition());
                telemetry.update();
            }
        }
        // Stop all motion;
        linearLift.setPower(0);

        // Turn off RUN_TO_POSITION
        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //  sleep(250);   // optional pause after each move

        telemetry.addData("Current Position",  "At %7d ", getCurrentPosition());
        telemetry.addData("New Position",  "Running to %7d ", liftLevel);
        telemetry.addData("Motor Current Position", "Running at %7d", linearLift.getCurrentPosition());

        setCurrentPosition(liftLevel);

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
