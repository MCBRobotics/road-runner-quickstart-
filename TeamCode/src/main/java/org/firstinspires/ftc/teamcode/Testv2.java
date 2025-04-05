package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Testv2", group="Linear OpMode")
//@Disabled
public class Testv2 extends LinearOpMode {

    // Declare OpMode members
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor Arm1 = null;
    private DcMotor Arm2 = null;
    private DcMotor viper = null;
    private Servo test_servo = null;
    private Servo rotate_servo = null;

    // Viper Slide Motor constants
    private static final double MAX_EXTENSION_INCHES = 10.0; // Maximum extension in inches
    private static final int ENCODER_COUNTS_PER_INCH = 113;  // Encoder counts per inch (must be calibrated)

    @Override
    public void runOpMode() {

        // Initialize the hardware variables
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        Arm1 = hardwareMap.get(DcMotor.class, "ArmMotor1");
        Arm2 = hardwareMap.get(DcMotor.class, "ArmMotor2");
        viper = hardwareMap.get(DcMotor.class, "viper");
        test_servo = hardwareMap.get(Servo.class, "test_servo");
        rotate_servo = hardwareMap.get(Servo.class, "rotate_servo");

        // Initialize motor modes
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set motor directions
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        // Initialize servo position
        test_servo.setPosition(0);
        rotate_servo.setPosition(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            // Drive control using left and right joysticks for movement and rotation
            double axial = -gamepad1.left_stick_y;  // Forward/backward movement
            double lateral = gamepad1.left_stick_x;  // Side-to-side movement
            double yaw = gamepad1.right_stick_x;     // Rotation (turning)

            // Combine joystick inputs to calculate motor powers
            double leftFrontPower = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower = axial - lateral + yaw;
            double rightBackPower = axial + lateral - yaw;

            // Normalize motor powers to ensure they don't exceed 100%
            double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            // Control the Viper slide motor using gamepad2 left stick
//            double slidePower = gamepad2.left_stick_y; // Adjust the power using left stick on gamepad2
//            int targetPosition = (int) (MAX_EXTENSION_INCHES * ENCODER_COUNTS_PER_INCH);
//
//            // Move the viper slide motor
//            if (Math.abs(viper.getCurrentPosition()) < targetPosition) {
//                viper.setPower(slidePower);
//            } else {
//                viper.setPower(0); // Stop the motor once the target position is reached
//            }

            // Control the arm motors using the D-pad
//            if (gamepad1.dpad_up) {
//                Arm1.setTargetPosition(-300);  // Arm1 moves up
//                Arm2.setTargetPosition(300);   // Arm2 moves up
//                Arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm1.setPower(0.2);
//                Arm2.setPower(0.2);
//            } else if (gamepad1.dpad_down) {
//                Arm1.setTargetPosition(-15);   // Arm1 moves down
//                Arm2.setTargetPosition(15);    // Arm2 moves down
//                Arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm1.setPower(0.3);
//                Arm2.setPower(0.3);
//            }

            //make this so that it only roates if it pressed
//            if (gamepad1.dpad_up) {
//                Arm1.setTargetPosition(-330);  // Arm1 moves up
//                Arm2.setTargetPosition(330);   // Arm2 moves up
//                Arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm1.setPower(1);
//                Arm2.setPower(1);
//            } else if (gamepad1.dpad_down) {
//                Arm1.setTargetPosition(-15);   // Arm1 moves down
//                Arm2.setTargetPosition(15);    // Arm2 moves down
//                Arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm1.setPower(0.3);
//                Arm2.setPower(0.3);
//            }

            if (gamepad2.dpad_up) {
                // Rotate the arm motors forward when the dpad_up is held down
                Arm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  // Disable position control, allows continuous rotation
                Arm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                Arm1.setPower(-0.6);  // Apply power to rotate forward
                Arm2.setPower(0.6);
            } else if (gamepad2.dpad_down) {
                // Rotate the arm motors in reverse when the dpad_down is held down
                Arm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  // Disable position control, allows continuous rotation
                Arm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                Arm1.setPower(0.6);  // Apply negative power to rotate backward
                Arm2.setPower(-0.6);
            }else if (gamepad1.b){
                Arm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  // Disable position control, allows continuous rotation
                Arm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                Arm1.setPower(1);  // Apply negative power to rotate backward
                Arm2.setPower(-1);
            }
            else if (gamepad1.a) {
                // Rotate the arm motors in reverse when the dpad_down is held down
                viper.setPower(0);  // Apply negative power to rotate backward
                //Arm2.setPower(0);
            } else {
                // Stop the motors when neither button is pressed
//                int Arm1Pos = Arm1.getCurrentPosition();
//                int Arm2Pos = Arm2.getCurrentPosition();
//                Arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                Arm1.setTargetPosition(Arm1Pos);
//                Arm1.setTargetPosition(Arm2Pos);
                Arm1.setPower(0);
                Arm2.setPower(0);

            }



//            }else{
//                Arm1.setPower(0);
//                Arm2.setPower(0);
//            }

            // Control viper motor directly using D-pad
            if (gamepad2.dpad_right) {
                viper.setPower(0.5);  // Move the viper motor forward at 50% power when the right D-pad button is pressed
            } else if (gamepad2.dpad_left) {
                viper.setPower(-0.5); // Move the viper motor backward at 50% power when the left D-pad button is pressed
            } else {
                viper.setPower(0);  // Stop the motor when neither button is pressed
            }

//            else {
//                viper.setPower(0);    // Stop the viper motor when no D-pad input is detected
//            }

            // Control the servo using the D-pad on gamepad2
            if (gamepad1.dpad_down) {
                test_servo.setPosition(0);    // Servo position 0 (e.g., closed)
            } else if (gamepad1.dpad_up) {
                test_servo.setPosition(0.5);  // Servo position 0.5 (e.g., opened)
            }

            if (gamepad2.b){
                rotate_servo.setPosition(0);
            }

            // Set the drive motors to their calculated power
            leftFrontDrive.setPower(leftFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightFrontDrive.setPower(rightFrontPower);
            rightBackDrive.setPower(rightBackPower);

            // Display telemetry data
            telemetry.addData("Arm1 Position", Arm1.getCurrentPosition());
            telemetry.addData("Arm2 Position", Arm2.getCurrentPosition());
            telemetry.addData("Viper Position", viper.getCurrentPosition());
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front Left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back Left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.update();
        }
    }
}
