package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.PG$LinearMotion1;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$LinearOperator1_positive extends PG$LinearMotion1 {



    public LinearOpMode parent;

    public double motorErrorAdjustment = 1.0;
    public boolean IsAutonomous = false;
    public double linearGearCircumference = 6.2; //inches
    //double ticksPerInchMecanum = (384.44 / linearGearCircumference);
    double ticksPerInchMecanum =1.0;

    HardwareMap hardwareMap;
    Telemetry telemetry;

    private ElapsedTime runtime = new ElapsedTime();
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();

    public PG$LinearOperator1_positive(HardwareMap hardwareMap, Telemetry telemetry){
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
        int position = newGlobalConfig.liftLevelMin;

        if (level>=getCurrentLevel()){
            position=newGlobalConfig.lifLevel[level]- newGlobalConfig.lifLevel[getCurrentLevel()];
            return ((int)linearLift.getCurrentPosition() + (int)(position * ticksPerInchMecanum));

        } else{
            position=newGlobalConfig.lifLevel[level]- newGlobalConfig.lifLevel[getCurrentLevel()];
            return ((int)linearLift.getCurrentPosition() - (int)(position * ticksPerInchMecanum));

        }

    }


    public void runViperMotor(double speed, int liftTargetLevel, double timeoutS) {
        int new_TargetPosition=0;


        // Ensure that the opmode is still active
        if (parent.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            new_TargetPosition = levelToPostionCalc(liftTargetLevel);

            linearLift.setTargetPosition(new_TargetPosition);

            // Turn On RUN_TO_POSITION
           // linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.setAutoClear(false);
            Telemetry.Item telemetryliftCurrentLevel = telemetry.addData("Lift Current Level", getCurrentLevel());
            Telemetry.Item telemetryliftTargetLevel = telemetry.addData("Lift Target Level", liftTargetLevel);
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("Lift Current Position", linearLift.getCurrentPosition());
            Telemetry.Item telemetryliftNewTargetPosition = telemetry.addData("Lift NewTarget Position", new_TargetPosition);
            Telemetry.Item telemetryliftCurrentTargetPosition = telemetry.addData("Lift CurrentTarget Position", linearLift.getTargetPosition());

            telemetry.update();



            // keep looping while we are still active, and there is time left, and both motors are running.
            telemetry.addData("Before WhileLoop", "1- "+parent.opModeIsActive()
                    + "2-" + (runtime.seconds() < timeoutS)
                    +"3-"+ linearLift.isBusy()
                    +"4-"+ (linearLift.getTargetPosition() >= linearLift.getCurrentPosition())
                    +"5-"+ (linearLift.getCurrentPosition() >= newGlobalConfig.liftLevelMin)
                    +"6-"+ (linearLift.getCurrentPosition() <= newGlobalConfig.liftLevelMax)
                    +"9:"+ (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (linearLift.isBusy() ) &&
                    (  linearLift.getTargetPosition() >= linearLift.getCurrentPosition()) &&
                    (linearLift.getCurrentPosition() >= newGlobalConfig.liftLevelMin) && (linearLift.getCurrentPosition() <= newGlobalConfig.liftLevelMax ))
                    + "--"+ linearLift.getTargetPosition()
                    +"--"+ linearLift.getCurrentPosition()
            );
            telemetry.update();

            // reset the timeout time and start motion.
            runtime.reset();
            linearLift.setPower(speed*motorErrorAdjustment);

            while (parent.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (linearLift.isBusy()) &&
                    (linearLift.getTargetPosition() >= linearLift.getCurrentPosition())
                //&&
                //(linearLift.getCurrentPosition() >= newGlobalConfig.liftLevelMin) &&
                //(linearLift.getCurrentPosition() <= newGlobalConfig.liftLevelMax)
            ) {
                telemetryliftCurrentLevel.setValue(getCurrentLevel());
                telemetryliftTargetLevel.setValue(liftTargetLevel);
                telemetryliftCurrentPosition.setValue(linearLift.getCurrentPosition());
                telemetryliftNewTargetPosition.setValue(new_TargetPosition);
                telemetryliftCurrentTargetPosition.setValue(linearLift.getTargetPosition());
                telemetry.addData("Inside While Loop",
                        "1- " + parent.opModeIsActive()
                                + "2-" + (runtime.seconds() < timeoutS)
                                + "3-" + linearLift.isBusy()
                                + "4-" + (linearLift.getTargetPosition() >= linearLift.getCurrentPosition())
                                + "5-" + (linearLift.getCurrentPosition() >= newGlobalConfig.liftLevelMin)
                                + "6-" + (linearLift.getCurrentPosition() <= newGlobalConfig.liftLevelMax)
                                + "9:" + (parent.opModeIsActive() &&
                                (runtime.seconds() < timeoutS) &&
                                (linearLift.isBusy()) &&
                                (linearLift.getTargetPosition() >= linearLift.getCurrentPosition()) &&
                                ( linearLift.getCurrentPosition() >= newGlobalConfig.liftLevelMin) && (linearLift.getCurrentPosition() <= newGlobalConfig.liftLevelMax))
                                + "--" + linearLift.getTargetPosition()
                                + "--" + linearLift.getCurrentPosition()

                );

                telemetry.update();
            }


        }
        // Stop all motion;
        linearLift.setPower(0);

        // Turn off RUN_TO_POSITION
        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        parent.sleep(250);   // optional pause after each move

//        telemetry.addData("Lift Current Position:",  "At %7d ", getCurrentLevel());
//        telemetry.addData("Lift Target Position:",  "At %7d ", getNewTargetPosition());
//        telemetry.addData("Lift New Lift Position:",  "At %7d ", new_TargetPosition);
//        telemetry.addData("Lift Lift Position Request:",  "Running to %7d ", liftTargetLevel);
//        telemetry.addData("Motor Current Position", "Running at %7d", linearLift.getCurrentPosition());
//        telemetry.update();

        setCurrentLevel(liftTargetLevel);
        setNewTargetPosition(new_TargetPosition);
        telemetry.addData("outside While Loop",
                "1- "+parent.opModeIsActive()
                        + "2-" + (runtime.seconds() < timeoutS)
                        +"3-"+ linearLift.isBusy()
                        +"4-"+ (  Math.abs(linearLift.getTargetPosition()) >= Math.abs(linearLift.getCurrentPosition()))
                        +"5-"+ (linearLift.getCurrentPosition() >= newGlobalConfig.liftLevelMin)
                        +"6-"+ (linearLift.getCurrentPosition() <= newGlobalConfig.liftLevelMax)
                        +"9:"+ (parent.opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (linearLift.isBusy() ) &&
                        (  Math.abs(linearLift.getTargetPosition()) >= Math.abs(linearLift.getCurrentPosition())) &&
                        (linearLift.getCurrentPosition() >= newGlobalConfig.liftLevelMin) && (linearLift.getCurrentPosition() <= newGlobalConfig.liftLevelMax ))
                        + "--"+ linearLift.getTargetPosition()
                        +"--"+ linearLift.getCurrentPosition()
        );
        telemetry.update();


    }

    public void move(double lefty, double righty, double leftx, double rightx){

//        frontright.setPower((-lefty  +rightx - leftx)*rightErrorAdjustment); // should work same as above
//        frontleft.setPower((lefty + rightx - leftx)*leftErrorAdjustment);
//        backright.setPower((-lefty + rightx + leftx)*rightErrorAdjustment);
//        backleft.setPower((lefty + rightx + leftx)*leftErrorAdjustment);

    }


}
