package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 12/25/2016.
 */
@Autonomous(name="Auto_45")
public class Auto_45 extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private  DcMotor MotorWheelRight;
    private  DcMotor MotorWheelLeft;
    private Servo IntoWheels;
    long start_time;
    long time_check1=0;
    boolean toggle1 = false;
    boolean checkshoot=false;
    boolean checkshootinglow=false;
    boolean checkshootinghigh=false;

    @Override

    public void runOpMode() {


        motorLeft= hardwareMap.dcMotor.get("motorleft");
        motorRight=hardwareMap.dcMotor.get("motorright");
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelRight=hardwareMap.dcMotor.get("motorwheelright");
        MotorWheelLeft=hardwareMap.dcMotor.get("motorwheelleft");
        IntoWheels=hardwareMap.servo.get("intowheels");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        IntoWheels.setPosition(0.8);
        waitForStart();
    }

    public void shoot(int power, int balls){
        MotorWheelLeft.setPower(power);
        MotorWheelRight.setPower(power);
        sleep(3000);
        IntoWheels.setPosition(0.2);
        sleep(balls * 2000);
        IntoWheels.setPosition(0.8);
        MotorWheelLeft.setPower(0.0);
        MotorWheelRight.setPower(0.0);
    }

    public void driveStraight(int cm, int speed){
        int spins = cm / 32 * 1440;
        motorLeft.setTargetPosition(spins);
        motorRight.setTargetPosition(spins);
        motorLeft.setPower(speed / 100);
        motorRight.setPower(speed / 100);
    }
}
