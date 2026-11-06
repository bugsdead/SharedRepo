package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TeleOpOnlyWheels")
public class TeleOpOnlyWheels extends LinearOpMode {
    // Motors
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    @Override
    public void runOpMode() {
        // Initializing
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        waitForStart();
        while (opModeIsActive()) {
            // Code

            double DEADZONE = 0.2;


            double forward = Math.abs(gamepad1.left_stick_y) > DEADZONE ? -gamepad1.left_stick_y : 0;
            double strafing = Math.abs(gamepad1.left_stick_x) > DEADZONE ? gamepad1.left_stick_x : 0;
            double turn = Math.abs(gamepad1.right_stick_x) > DEADZONE ? gamepad1.right_stick_x : 0;

            // Mecanum Drive Power Calculations
            frontLeft.setPower(-forward + strafing - turn);
            frontRight.setPower(forward + strafing + turn);
            backLeft.setPower(-forward - strafing + turn);
            backRight.setPower(forward - strafing - turn);
        }
    }
}
