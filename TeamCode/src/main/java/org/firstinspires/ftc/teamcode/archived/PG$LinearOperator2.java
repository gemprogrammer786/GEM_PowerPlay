package org.firstinspires.ftc.teamcode.archived;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.archived.PG$LinearMontion2;
import org.firstinspires.ftc.teamcode.global.PG$GlobalConfig;

public class PG$LinearOperator2 extends PG$LinearMontion2 {


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

    HardwareMap hardwareMap;
    Telemetry telemetry;
    private ElapsedTime runtime = new ElapsedTime();
    PG$GlobalConfig newGlobalConfig = new PG$GlobalConfig();

    public PG$LinearOperator2(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
        this.hardwareMap=hardwareMap;
        this.telemetry=telemetry;
    }

    public void lift(int level) {
        int position = newGlobalConfig.liftMin;

        if(level == 1){
            position = newGlobalConfig.liftLevel1;

        }
        else if(level == 2){
            position = newGlobalConfig.liftLevel2;

        }
        else if(level == 3){
            position = newGlobalConfig.liftLevel3;

        }

        new_TargetPosition = position;
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
        telemetry.addData("Path1",  "Running to %7d ", new_TargetPosition);

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

}
