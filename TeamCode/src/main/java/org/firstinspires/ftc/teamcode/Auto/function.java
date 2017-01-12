package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


/**
 * Created by Le Nayad on 12/25/2016.
 */

public class function   {
    public DcMotor motorLeft;
    public DcMotor motorRight;
    public DcMotor MotorWheelRight;
    public DcMotor MotorWheelLeft;
    public DcMotor esuf;
    public Servo IntoWheels;
    HardwareMap hwMap = null;
    private ElapsedTime period  = new ElapsedTime();

    public void in() {
        motorLeft = hwMap.dcMotor.get("motorleft");
        motorRight = hwMap.dcMotor.get("motorright");
        esuf = hwMap.dcMotor.get("esuf");
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelRight = hwMap.dcMotor.get("motorwheelright");
        MotorWheelLeft = hwMap.dcMotor.get("motorwheelleft");
        IntoWheels = hwMap.servo.get("intowheels");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        IntoWheels.setPosition(0.8);
    }

    public boolean shoot(int balls, double speed) {
        esuf.setPower(1);
        MotorWheelLeft.setPower(speed);
        MotorWheelRight.setPower(speed);
        waitForTick(3000*balls);
        IntoWheels.setPosition(0.2);
        waitForTick(1500*balls);
        MotorWheelLeft.setPower(0);
        MotorWheelRight.setPower(0);
        esuf.setPower(0);
        return true;
    }

    public void drive(int cm, int FOB) {
        int value = motorLeft.getCurrentPosition();
        int value2 = motorRight.getCurrentPosition();
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (FOB > 0) {
            while (-motorLeft.getCurrentPosition() < (cm / 32 * 1440 + value) * FOB && -motorRight.getCurrentPosition() < (cm / 32 * 1440 + value2) * FOB) {
                motorLeft.setPower(FOB);
                motorRight.setPower(FOB);
            }
        } else {
            while (-motorLeft.getCurrentPosition() > (cm / 32 * 1440 + value) * FOB && -motorRight.getCurrentPosition() > (cm / 32 * 1440 + value2) * FOB) {
                motorLeft.setPower(FOB);
                motorRight.setPower(FOB);
            }
        }
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }


    public void turn(float turnDeg) {
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnDeg = turnDeg / 4;
        float turnDistance = turnDeg*1440 /32;
        float finalValueLeft = -(turnDistance + getCurrentPosition(motorLeft));
        float finalValueRight = turnDistance + getCurrentPosition(motorRight);

            while (getCurrentPosition(motorRight) < finalValueRight&& getCurrentPosition(motorLeft)>finalValueLeft) {
                motorRight.setPower(1);
                motorLeft.setPower(-1);
            }
            motorLeft.setPower(0);
            motorRight.setPower(0);
        //if (turnDeg < 0) {
//       }
//        else {
//            while (motorLeft.getCurrentPosition() > turnDistance + finalValueLeft && -motorRight.getCurrentPosition() < turnDistance + finalValueRight) {
//                motorLeft.setPower(-1);
//                motorRight.setPower(1);
//            }
//            motorLeft.setPower(0);
//            motorRight.setPower(0);
//        }
    }
    public int getCurrentPosition(DcMotor motor){
        return -motor.getCurrentPosition();
    }
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
