package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


/**
 * Created by Le Nayad on 12/25/2016.
 */

@Autonomous(name = "Auto_1")
public class Auto_45 extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor MotorWheelRight;
    private DcMotor MotorWheelLeft;
    private Servo IntoWheels;
    double degcm;

    @Override

    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.dcMotor.get("motorleft");
        motorRight = hardwareMap.dcMotor.get("motorright");
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelRight = hardwareMap.dcMotor.get("motorwheelright");
        MotorWheelLeft = hardwareMap.dcMotor.get("motorwheelleft");
        IntoWheels = hardwareMap.servo.get("intowheels");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        IntoWheels.setPosition(0.8);
        commit();
    }

    public void Auto1() {
        drive(155, 1);
        drive(20, -1);
        turn(130);
        drive(500, 1);
    }

    public void commit() {
        waitForStart();
        telemetry.addData("encoder", motorLeft.getCurrentPosition());
        telemetry.update();
        //shoot(2, 1);
//        turn(90);
        turn(90);
        //drive(100,1);
    }

    public boolean shoot(int balls, double speed) {
        MotorWheelLeft.setPower(speed);
        MotorWheelRight.setPower(speed);
        sleep(3000);
        IntoWheels.setPosition(0.2);
        sleep(1500 * balls);
        IntoWheels.setPosition(0.8);
        MotorWheelLeft.setPower(0);
        MotorWheelRight.setPower(0);
        MotorWheelRight.setPower(0);
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
                telemetry.addData("encoder", -motorRight.getCurrentPosition());
                telemetry.update();
            }
        } else {
            while (-motorLeft.getCurrentPosition() > (cm / 32 * 1440 + value) * FOB && -motorRight.getCurrentPosition() > (cm / 32 * 1440 + value2) * FOB) {
                motorLeft.setPower(FOB);
                motorRight.setPower(FOB);
                telemetry.addData("encoder", -motorRight.getCurrentPosition());
                telemetry.update();
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
        telemetry.addData("tom",turnDistance);
        telemetry.update();
//        if (turnDeg < 0) {

            while (getCurrentPosition(motorRight) < finalValueRight&& getCurrentPosition(motorLeft)>finalValueLeft) {
                motorRight.setPower(1);
                motorLeft.setPower(-1);
                telemetry.addData("InOrOut","In");
                telemetry.update();
            }
        telemetry.addData("InOrOut","Out");
        telemetry.update();
            motorLeft.setPower(0);
            motorRight.setPower(0);
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
}
