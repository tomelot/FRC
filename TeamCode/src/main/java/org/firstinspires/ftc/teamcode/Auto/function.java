package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Timer;
import java.util.*;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;

import java.util.TimerTask;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Le Nayad on 12/25/2016.
 */

public class function extends Thread {
    public DcMotor motorLeft = null;
    public DcMotor motorRight = null;
    public DcMotor MotorWheelRight = null;
    public DcMotor MotorWheelLeft = null;
    public DcMotor esuf = null;
    public Servo IntoWheels = null;
    public LightSensor lightSensor;
    HardwareMap hwMap = null;
    Telemetry tm = null;
    System sy = null;
    public ElapsedTime period = new ElapsedTime();
    long start;
    long end;
    long passedTime = 0;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        motorLeft = hwMap.dcMotor.get("motorleft");
        motorRight = hwMap.dcMotor.get("motorright");
        esuf = hwMap.dcMotor.get("esuf");
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelRight = hwMap.dcMotor.get("motorwheelright");
        MotorWheelLeft = hwMap.dcMotor.get("motorwheelleft");
        IntoWheels = hwMap.servo.get("intowheels");
        lightSensor = hwMap.lightSensor.get("sensor_light");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        MotorWheelLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        IntoWheels.setPosition(0.8);
        lightSensor.enableLed(true);
    }

    public void shoot(final int balls, final double speed) throws InterruptedException {
        esuf.setPower(1);
        MotorWheelLeft.setPower(speed);
        MotorWheelRight.setPower(speed);
        Thread.sleep(5000 * balls);
        IntoWheels.setPosition(0.2);
        Thread.sleep(1500 * balls);
        IntoWheels.setPosition(0.9);
        esuf.setPower(0);
        MotorWheelLeft.setPower(0);
        MotorWheelRight.setPower(0);
    }

    public void drive(int cm, int FOB) {
        int value = getCurrentPosition(motorLeft);
        int value2 = getCurrentPosition(motorRight);
        if (value < 0) {
            value = -value;
            value2 = -value2;
        }
        if (FOB > 0) {
            while (getCurrentPosition(motorLeft) < (cm / 32 * 1440 + value) * FOB && getCurrentPosition(motorRight) < (cm / 32 * 1440 + value) * FOB) {
                motorLeft.setPower(FOB);
                motorRight.setPower(FOB);
            }
        } else {
            while (-getCurrentPosition(motorLeft) < (cm / 32 * 1440 + value) * FOB && -getCurrentPosition(motorRight) < (cm / 32 * 1440 + value2) * FOB) {
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
        float turnDistance = turnDeg * 1440 / 32;
        float finalValueLeft = -(turnDistance + getCurrentPosition(motorLeft));
        float finalValueRight = turnDistance + getCurrentPosition(motorRight);

        while (getCurrentPosition(motorRight) < finalValueRight && getCurrentPosition(motorLeft) > finalValueLeft) {
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

    public int getCurrentPosition(DcMotor motor) {
        return -motor.getCurrentPosition();
    }

    public void shootpower(System system) {
        sy = system;
        int kp = 120;
        int lposR = MotorWheelRight.getCurrentPosition(); int newposL ,newposR; Long newtimeR, newtimeL;
        long lasttimeR = 0;
        int lposL = MotorWheelLeft.getCurrentPosition();
        long lasttimeL = 0;
        double speedL , speedR;
        double powerL = 0, powerR = 0;
        double target = (MotorWheelLeft.getPower()+MotorWheelRight.getPower())/2 * kp ;
        while (MotorWheelLeft.isBusy() && MotorWheelRight.isBusy()) {
            start = sy.currentTimeMillis();
            newposL = MotorWheelLeft.getCurrentPosition();
            newtimeL = sy.currentTimeMillis();
            newposR = MotorWheelRight.getCurrentPosition();
            newtimeR = sy.currentTimeMillis();
            speedR = (newposR - lposR) / (newtimeR-lasttimeR);
            speedL = (newposL - lposL) / (newtimeL-lasttimeL);
            double mickeyL = target-speedL;
            double mickeyR = target-speedR;
            if (mickeyL > 0)
            {powerL += 0.05;}
            else if (mickeyL < 0)
            { powerL -=0.05;}
            if (mickeyR > 0)
            {powerR += 0.05;}
            else if(mickeyR < 0)
            {powerR -= 0.05;}
            MotorWheelLeft.setPower(powerL);
            MotorWheelRight.setPower(powerR);
            lposL = newposL;
            lposR = newposR;
            lasttimeL = newtimeL;
            lasttimeR = newtimeR;

            end = sy.currentTimeMillis();
        }
        passedTime = end - start;
    }
}
