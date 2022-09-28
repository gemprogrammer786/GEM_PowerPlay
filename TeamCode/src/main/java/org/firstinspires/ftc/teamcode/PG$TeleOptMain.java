package org.firstinspires.ftc.teamcode;






import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.functions.PG$TeleOptRobotDrive;

/**
 * This particular OpMode executes a POV Game style Teleop for a direct drive robot
 * The code is structured as a LinearOpMode
 *
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the arm using the Gamepad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TeleOp TeleOptMain", group="GENBot")
@Disabled
public class PG$TeleOptMain extends OpMode {

//    manualDriveRobot manualDriveRobot = new manualDriveRobot();
//    @Override
//    public void runOpMode() {
//
//
//        // Send telemetry message to signify robot waiting;
//        telemetry.addData(">", "Robot Ready.  Press Play.");    //
//        telemetry.update();
//
//        // Wait for the game to start (driver presses PLAY)
//        waitForStart();
//
//        // run until the end of the match (driver presses STOP)
//        while (opModeIsActive()) {
//
//            manualDriveRobot.runRobot();
//
//        }
//    }



    PG$TeleOptRobotDrive teleOptRobot   = new PG$TeleOptRobotDrive(hardwareMap,  telemetry);

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        /* ->
             initializing your robot
        <-*/

        teleOptRobot.init(hardwareMap,  telemetry,  gamepad1);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
