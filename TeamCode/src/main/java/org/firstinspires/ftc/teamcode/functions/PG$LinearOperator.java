package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.PG$LinearMotion;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$LinearOperator extends PG$LinearMotion {



    public LinearOpMode parent;

    public double motorErrorAdjustment = 1.0;
    public boolean IsAutonomous = false;
    public double linearGearCircumference = 6.2; //inches
    double ticksPerInchMecanum = (384.44 / linearGearCircumference);
    //double ticksPerInchMecanum =1.0;

    HardwareMap hardwareMap;
    Telemetry telemetry;

    private ElapsedTime runtime = new ElapsedTime();
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();

    public PG$LinearOperator(HardwareMap hardwareMap, Telemetry telemetry){
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
       return(level==0 ? 0 :newGlobalConfig.lifLevelTicks[level] );
    }


    public void runViperMotor(double speed, int liftTargetLevel, String whichLevelData, double timeoutS) {
        int new_TargetPosition=0;

        if (parent.opModeIsActive()) {

            //new_TargetPosition = levelToPostionCalc(liftTargetLevel);
            new_TargetPosition=(liftTargetLevel==0 ? 0 :(whichLevelData=="coneLevelTicks" ? newGlobalConfig.coneLevelTicks[liftTargetLevel]:newGlobalConfig.lifLevelTicks[liftTargetLevel]) );
            linearLift.setTargetPosition(new_TargetPosition);
            linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.setAutoClear(false);
            Telemetry.Item telemetryliftCurrentLevel = telemetry.addData("Lift Current Level", getCurrentLevel());
            Telemetry.Item telemetryliftTargetLevel = telemetry.addData("Lift Target Level", liftTargetLevel);
            Telemetry.Item telemetryliftCurrentPosition = telemetry.addData("Lift Current Position", linearLift.getCurrentPosition());
            Telemetry.Item telemetryliftNewTargetPosition = telemetry.addData("Lift NewTarget Position", new_TargetPosition);
            Telemetry.Item telemetryliftCurrentTargetPosition = telemetry.addData("Lift CurrentTarget Position", linearLift.getTargetPosition());

            telemetry.update();

            runtime.reset();
            linearLift.setPower( speed*motorErrorAdjustment);

            while (parent.opModeIsActive() &&
                    //(runtime.seconds() < timeoutS) &&
                    (linearLift.isBusy()) &&
                    (Math.abs(linearLift.getTargetPosition()) >= Math.abs(linearLift.getCurrentPosition())) &&
                    (linearLift.getCurrentPosition() >= newGlobalConfig.liftLevelMin) &&
                    (linearLift.getCurrentPosition() <= newGlobalConfig.liftLevelMax)) {
                telemetryliftCurrentLevel.setValue(getCurrentLevel());
                telemetryliftTargetLevel.setValue(liftTargetLevel);
                telemetryliftCurrentPosition.setValue(linearLift.getCurrentPosition());
                telemetryliftNewTargetPosition.setValue(new_TargetPosition);
                telemetryliftCurrentTargetPosition.setValue(linearLift.getTargetPosition());
                telemetry.update();
            }
        }
        parent.sleep(250);   // optional pause after each move
        setCurrentLevel(liftTargetLevel);
    }
}
