package org.firstinspires.ftc.teamcode.archived;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PG$ArmLiftAndGrabber {
    //Configuration used: 6wheelConfig
    public Servo clawGrabber;
    public DcMotorEx linearLift;


//    public double clawMin = 0.2;
//    public double clawMax = 0.7;
    public double clawMin = 0.0;
    public double clawMax = 1.0;
    //0 position
    public double liftMin = 0.83;
    //Level 1
    public double liftLevel1 = 0.58;
    //Level 2
    public double liftLevel2 = 0.26;
    //Level 3
    public double liftLevel3 = 0.2;


    public double liftPower = -0.2;
    public LinearOpMode parent;
    public Telemetry telemetry;
    public double pos = 0.0;

    public int new_frontLeftTarget = 0;

    public PG$ArmLiftAndGrabber(HardwareMap hardwareMap) {
        clawGrabber = hardwareMap.get(Servo.class,"arm");
        linearLift = hardwareMap.get(DcMotorEx.class,"viperMotor");
        clawGrabber.setPosition(clawMin);
        linearLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearLift.setTargetPosition(0);
        linearLift.setPower(0.2);

    }
    public void lift(int level) {
        int position = 0;
        double liftpos = liftMin;
        
        if(level == 1){
            position = 135;
            liftpos = liftLevel1;
        
        }
        else if(level == 2){
            position = 260;
            liftpos = liftLevel2;
        
        }
        else if(level == 3){
            position = 380;
            liftpos = liftLevel3;
        
        }

        new_frontLeftTarget = position;
        if(level!=0) {
            linearLift.setTargetPosition(new_frontLeftTarget);
            parent.sleep(200);
        }
        else{

            parent.sleep(200);
            linearLift.setTargetPosition(new_frontLeftTarget);
        }

        if(position==0){
            linearLift.setPower(0.2);
            //armRight.setPower(0.2);
            parent.sleep(200);
        }
        else {
            linearLift.setPower(0.6);
            //armRight.setPower(0.6);
        }
        //else{
        //arm.setPower(0.0);
        //}
        telemetry.addData("Path1",  "Running to %7d ", new_frontLeftTarget);

        telemetry.addData("Path2",  "Running at %7d ",
                linearLift.getCurrentPosition());
                //armRight.getCurrentPosition();
        telemetry.update();
        //}
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
*/
    public void initiateLift(){
//        telemetry.addData("Postion lift 2:%d", linearLift.getCurrentPosition());
//        telemetry.update();
        linearLift.setPower(liftPower);
        parent.sleep(1000);
        linearLift.setPower(0);
        telemetry.addData("Postion lift 2:%d", linearLift.getCurrentPosition());
        telemetry.update();
        linearLift.setPower(-liftPower);
        parent.sleep(1000);
        linearLift.setPower(0);
        telemetry.addData("Postion lift 2:%d", linearLift.getCurrentPosition());
        telemetry.update();
    }

    public void initiateClaw() {

    }

    public void lift(double power)
    {
        linearLift.setPower(power);
    }

//    public void swing(double position){
//        pos = position;
//        arm.setPosition(pos);
//        parent.sleep(750);
//    }

//    public void rotate(double power)
//    {
//        //arm.setPosition(power);
//        if(power<0) {
//            pos = pos - 0.1;
//            if (pos < -1.5)
//                pos = -1.5;
//        }
//        else if (power > 0){
//        pos = pos + 0.1;
//        if (pos >1.5)
//            pos = 1.5;
//        }
//        arm.setPosition(pos);
//
//    }

    public void rest()
    {
        linearLift.setPower(-liftPower);
    }

    public void pick()
    {

        clawGrabber.setPosition(clawMax);
    }
    public void grab()
    {
        telemetry.addData("Postion Claw 1:%d", clawGrabber.getPosition());
        telemetry.update();
        clawGrabber.setPosition(clawMin);
        telemetry.addData("Postion Claw 1:%d", clawGrabber.getPosition());
        telemetry.update();
    }

    public void release() {
        clawGrabber.setPosition(clawMax);

        telemetry.addData("Postion Claw 1:%d", clawGrabber.getPosition());
        telemetry.update();
        //parent.sleep(5000);
    }
}
