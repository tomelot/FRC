package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 12/25/2016.
 */

public class function extends Thread {
    public DcMotor motorLeft;
    public DcMotor motorRight;
    public DcMotor MotorWheelRight;
    public DcMotor MotorWheelLeft;

    public DcMotor esuf = null;
    public Servo IntoWheels = null;
    HardwareMap hwMap = null;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        motorLeft = hwMap.dcMotor.get("motorleft");
        motorRight = hwMap.dcMotor.get("motorright");
        esuf = hwMap.dcMotor.get("esuf");
        MotorWheelRight = hwMap.dcMotor.get("motorwheelright");
        MotorWheelLeft = hwMap.dcMotor.get("motorwheelleft");
        IntoWheels = hwMap.servo.get("intowheels");
        MotorWheelLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelLeft.setMaxSpeed(6930);
        MotorWheelRight.setMaxSpeed(6930);
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        IntoWheels.setPosition(0.8);
    }

    public void shoot(final int balls,  double speed) throws InterruptedException {
        esuf.setPower(1);
        MotorWheelLeft.setPower(speed);
        MotorWheelRight.setPower(speed);
        Thread.sleep(3000 * balls);
        IntoWheels.setPosition(0.2);
        Thread.sleep(2000 * balls);
        MotorWheelLeft.setPower(0);
        MotorWheelRight.setPower(0);
        esuf.setPower(0);
    }
    public void reset(){
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void drive(long cm, int FOB) throws InterruptedException{
        long time  = cm/60 * 1000;
        motorLeft.setPower(FOB);
        motorRight.setPower(FOB);
        Thread.sleep(time);
        motorRight.setPower(0);
        motorLeft.setPower(0);
//        int value = Math.abs(getCurrentPosition(motorLeft));
//        int value2 = Math.abs(getCurrentPosition(motorRight));
////        motorLeft.setTargetPosition((cm*2310/32)+value);
////        motorRight.setTargetPosition((cm*2310/32)+value2);
////        MotorWheelLeft.setPower(FOB);
////        motorRight.setPower(FOB);
////        }
//        if (FOB > 0) {
//            while (Math.abs(getCurrentPosition(motorLeft)) < (cm / 32 * 2310)+ value && Math.abs(getCurrentPosition(motorRight)) < (cm / 32 * 2310 )+value2) {
//                motorLeft.setPower(FOB);
//                motorRight.setPower(-FOB);
//            }
//        } else {
//            while (Math.abs(getCurrentPosition(motorLeft))  < ((cm / 32 * 2310 )+ value) && Math.abs(getCurrentPosition(motorRight)) < (cm / 32 * 2310)+value2) {
//                motorLeft.setPower(FOB);
//                motorRight.setPower(-FOB);
//            }
//        }
//        motorLeft.setPower(0);
//        motorRight.setPower(0);
    }


    public void turn(int turnDeg,int LoR) throws InterruptedException {
            int time = turnDeg/4/60*10000;
            motorLeft.setPower(LoR);
            motorRight.setPower(-LoR);
            Thread.sleep(time);
            motorRight.setPower(0);
            motorLeft.setPower(0);
//        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        turnDeg = turnDeg / 4;
//        float turnDistance = turnDeg * 1440 / 32;
//        float finalValueLeft = -(turnDistance + getCurrentPosition(motorLeft));
//        float finalValueRight = turnDistance + getCurrentPosition(motorRight);
//
//        while (getCurrentPosition(motorRight) < finalValueRight && getCurrentPosition(motorLeft) > finalValueLeft) {
//            motorRight.setPower(1);
//            motorLeft.setPower(-1);
//        }
//        motorLeft.setPower(0);
//        motorRight.setPower(0);
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
        return motor.getCurrentPosition();
    }

    public void shootpower(Gamepad GP,DcMotor Motorwheelleft,DcMotor Motorwheelright) {
//        gp = GP;
//        int kp = 120;
//        int lposR = Motorwheelright.getCurrentPosition();
//        int lposL = Motorwheelleft.getCurrentPosition();
//        int newposL, newposR;
//        Long newtimeR, newtimeL;
//        long lasttimeR = sy.sy.currentTimeMillis() ,lasttimeL = sy.sy.currentTimeMillis();
//        double target = 0;
//        double speedL, speedR;
//        double powerL = 0, powerR = 0;
//        if (gp.left_trigger > 0) {
//            target = 200;
//        }
//        if (gp.right_trigger > 0) {
//            target = 400;
//        }
//        while (gp.left_trigger > 0 || gp.right_trigger > 0) {
//            newposL = Motorwheelleft.getCurrentPosition();
//            newtimeL = sy.sy.currentTimeMillis();
//            newposR = Motorwheelright.getCurrentPosition();
//            newtimeR = sy.sy.currentTimeMillis();
//            speedR = (newposR - lposR) / (newtimeR - lasttimeR);
//            speedL = (newposL - lposL) / (newtimeL - lasttimeL);
//            double mickeyL = target - speedL;
//            double mickeyR = target - speedR;
//            if (mickeyL > 0) {
//                powerL += 0.05;
//            } else if (mickeyL < 0) {
//                powerL -= 0.05;
//            }
//            if (mickeyR > 0) {
//                powerR += 0.05;
//            } else if (mickeyR < 0) {
//                powerR -= 0.05;
//            }
//            Motorwheelleft.setPower(powerL);
//            Motorwheelright.setPower(powerR);
//            lposL = newposL;
//            lposR = newposR;
//            lasttimeL = newtimeL;
//            lasttimeR = newtimeR;
//        }
//        end = sy.sy.currentTimeMillis();
//        passedTime = end - start;
    }
}
