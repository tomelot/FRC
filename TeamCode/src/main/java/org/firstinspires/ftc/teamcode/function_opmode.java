package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.function;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by owner on 1/11/2017.
 */
public class function_opmode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor MotorWheelRight;
    private DcMotor MotorWheelLeft;
    private Servo IntoWheels;
    private DcMotor esuf;
    long start_time;
    long time_check1 = 0;
    boolean checkshoot = false;
    boolean checkshootinglow = false;
    boolean checkshootinghigh = false;
    HardwareMap wh = null;

    public void init (HardwareMap WH) {
        wh = WH;
        motorLeft = wh.dcMotor.get("motorleft");
        motorRight = wh.dcMotor.get("motorright");
        MotorWheelRight = wh.dcMotor.get("motorwheelright");
        MotorWheelLeft = wh.dcMotor.get("motorwheelleft");
        IntoWheels =    wh.servo.get("intowheels");
        esuf = wh.dcMotor.get("esuf");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        IntoWheels.setPosition(0.8);
        MotorWheelLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelLeft.setMaxSpeed(6930);
        MotorWheelRight.setMaxSpeed(6930);
        start_time = System.currentTimeMillis();
    }
    public void esuf(Gamepad Gamepad) {
        if (Gamepad.left_bumper == true) {
            esuf.setPower(1);
        }
        if (Gamepad.right_bumper == true) {
            esuf.setPower(-1);
        }
        if (Gamepad.b == true) {
            esuf.setPower(0);
        }
    }

    public void drive(Gamepad Gamepad) {
        double i = 1;
        if (Gamepad.left_stick_y != 0 && Gamepad.right_stick_x != 0) {
            i = 0.75;
        }
        motorLeft.setPower(-(Gamepad.left_stick_y - Gamepad.right_stick_x) * i);
        motorRight.setPower(-(Gamepad.left_stick_y + Gamepad.right_stick_x) * i);
    }

    public void shoot(Gamepad Gamepad) {
        if (Gamepad.a == true) {
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

    }

    public void shootpowerlow(Gamepad Gamepad) {
        if (checkshootinghigh == false) {
            if (Gamepad.left_trigger > 0) {
                MotorWheelLeft.setPower(0.115);
                MotorWheelRight.setPower(0.115);
                checkshootinglow = true;

            } else {
                MotorWheelLeft.setPower(0);
                MotorWheelRight.setPower(0);
                checkshootinglow = false;

            }

        }
    }

    public void shootpowerhigh(Gamepad Gamepad) {
        if (checkshootinglow == false) {
            if (Gamepad.right_trigger > 0) {
                MotorWheelLeft.setPower(0.16);
                MotorWheelRight.setPower(0.16);
                checkshootinghigh = true;

            } else {
                MotorWheelLeft.setPower(0);
                MotorWheelRight.setPower(0);
                checkshootinghigh = false;

            }
        }
    }
}
