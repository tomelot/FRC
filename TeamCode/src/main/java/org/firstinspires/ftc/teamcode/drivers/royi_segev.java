package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by owner on 1/11/2017.
 */
@TeleOp(name="royi_segev")
public class royi_segev extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor MotorWheelRight;
    private DcMotor MotorWheelLeft;
    private Servo IntoWheels;
    long start_time;
    long time_check1 = 0;
    boolean checkshoot = false;
    boolean checkshootinglow = false;
    boolean checkshootinghigh = false;

    @Override
    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.dcMotor.get("motorleft");
        motorRight = hardwareMap.dcMotor.get("motorright");
        MotorWheelRight = hardwareMap.dcMotor.get("motorwheelright");
        MotorWheelLeft = hardwareMap.dcMotor.get("motorwheelleft");
        IntoWheels = hardwareMap.servo.get("intowheels");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        IntoWheels.setPosition(0.8);
        waitForStart();
        start_time = System.currentTimeMillis();
        while (opModeIsActive()) {
            start_time = System.currentTimeMillis();
            drive();
            shootpowerhigh();
            shootpowerlow();
            shoot();
            telemetry.addData("speed", MotorWheelLeft.getCurrentPosition());
            telemetry.update();
        }

    }

    public void drive() {
        double i = 1;
        if (gamepad1.left_stick_y != 0 && gamepad1.right_stick_x != 0) {
            i = 0.75;
        }
        motorLeft.setPower(-(gamepad1.left_stick_y - gamepad1.right_stick_x) * i);
        motorRight.setPower(-(gamepad1.left_stick_y + gamepad1.right_stick_x) * i);
    }

    public void shoot() {
        if (gamepad2.a == true) {
            if (checkshoot == false) {
                checkshoot = true;
                time_check1 = start_time + 2000;
                IntoWheels.setPosition(0.2);
            }
        }
        if (time_check1 <= start_time && time_check1 > 0) {
            time_check1 = 0;
            IntoWheels.setPosition(0.8);
            checkshoot = false;

        }
        telemetry.addData("servo", IntoWheels.getPosition());

    }

    public void shootpowerlow() {
        if (checkshootinghigh == false) {
            if (gamepad2.left_trigger > 0) {
                MotorWheelLeft.setPower(0.27);
                MotorWheelRight.setPower(0.27);
                checkshootinglow = true;

            } else {
                MotorWheelLeft.setPower(0);
                MotorWheelRight.setPower(0);
                checkshootinglow = false;

            }

        }
    }

    public void shootpowerhigh() {
        if (checkshootinglow == false) {
            if (gamepad2.right_trigger > 0) {
                MotorWheelLeft.setPower(0.35);
                MotorWheelRight.setPower(0.35);
                checkshootinghigh = true;

            } else {
                MotorWheelLeft.setPower(0);
                MotorWheelRight.setPower(0);
                checkshootinghigh = false;

            }
        }
    }
}
