package org.firstinspires.ftc.teamcode.some;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by Le Nayad on 11/7/2016.
 */
@TeleOp(name="TeleOp_test")
public class TeleOp_test extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    @Override
    public void runOpMode() throws InterruptedException
    {
        motorLeft= hardwareMap.dcMotor.get("segev1");
        motorRight=hardwareMap.dcMotor.get("segev2");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        while(opModeIsActive()) {

            motorLeft.setPower(gamepad1.right_stick_y);
            motorRight.setPower(gamepad1.left_stick_y);
            idle();

        }
    }
}

