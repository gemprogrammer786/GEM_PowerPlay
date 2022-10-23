package org.firstinspires.ftc.teamcode.global;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.*;
import com.qualcomm.robotcore.hardware.Gamepad;


public class PG$GameControlerMapping {

        public Gamepad gamepad1;
        public Gamepad gamepad2;

        public final float gm1_lefty = gamepad1.left_stick_y;
        public final float gm1_lleftx = gamepad1.left_stick_x;
        public final float gm1_lrighty = gamepad1.right_stick_y;
        public final float gm1_lrightx = gamepad1.right_stick_x;
        public final boolean gm1_a =gamepad1.a;
        public final boolean gm1_b =gamepad1.b;
        public final boolean gm1_x =gamepad1.x;
        public final boolean gm1_y =gamepad1.y;
        public final boolean gm1_lb =gamepad1.left_bumper;
        public final boolean gm1_rb =gamepad1.right_bumper;
        public final float gm1_lt =gamepad1.left_trigger;
        public final float gm1_rt =gamepad1.right_trigger;
        public final boolean gm1_dpDown =gamepad1.dpad_down;
        public final boolean gm1_dpUp =gamepad1.dpad_up;
        public final boolean gm1_dpLeft =gamepad1.dpad_left;
        public final boolean gm1_dpRight =gamepad1.dpad_right;



        public final float gm2_lefty = gamepad2.left_stick_y;
        public final float gm2_lleftx = gamepad2.left_stick_x;
        public final float gm2_lrighty = gamepad2.right_stick_y;
        public final float gm2_lrightx = gamepad2.right_stick_x;
        public final boolean gm2_a =gamepad2.a;
        public final boolean gm2_b =gamepad2.b;
        public final boolean gm2_x =gamepad2.x;
        public final boolean gm2_y =gamepad2.y;
        public final boolean gm2_lb =gamepad2.left_bumper;
        public final boolean gm2_rb =gamepad2.right_bumper;
        public final float gm2_lt =gamepad2.left_trigger;
        public final float gm2_rt =gamepad2.right_trigger;
        public final boolean gm2_dpDown =gamepad2.dpad_down;
        public final boolean gm2_dpUp =gamepad2.dpad_up;
        public final boolean gm2_dpLeft =gamepad2.dpad_left;
        public final boolean gm2_dpRight =gamepad2.dpad_right;


        public final double drive_lefty = gm1_lefty;
        public final double drive_leftx = gm1_lleftx;
        public final double drive_righty = gm1_lrighty;
        public final double drive_rightx = gm1_lrightx;


    public PG$GameControlerMapping(Gamepad gamepad1, Gamepad gamepad2 ){

            this.gamepad1=gamepad1;
            this.gamepad2=gamepad2;

    }


}
