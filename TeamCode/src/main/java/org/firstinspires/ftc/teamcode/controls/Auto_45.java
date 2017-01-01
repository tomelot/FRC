package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 12/25/2016.
 */
@Autonomous(name="Auto_1")
public class Auto_45 extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private  DcMotor MotorWheelRight;
    private  DcMotor MotorWheelLeft;
    private Servo IntoWheels;

    @Override

    public void runOpMode() throws InterruptedException {


        motorLeft= hardwareMap.dcMotor.get("motorleft");
        motorRight=hardwareMap.dcMotor.get("motorright");
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorWheelRight=hardwareMap.dcMotor.get("motorwheelright");
        MotorWheelLeft=hardwareMap.dcMotor.get("motorwheelleft");
        IntoWheels=hardwareMap.servo.get("intowheels");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        IntoWheels.setPosition(0.8);
        waitForStart();

    }
    public boolean shoot(int balls,double speed){
        MotorWheelLeft.setPower(speed);
        MotorWheelRight.setPower(speed);
        sleep(3000);
        IntoWheels.setPosition(0.2);
        sleep(1500*balls);
        IntoWheels.setPosition(0.8);
        MotorWheelLeft.setPower(0);
        MotorWheelRight.setPower(0);
        return true;
    }
    public void drive(int cm,int FOB){
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setTargetPosition((cm/32*1440)*FOB);
        motorRight.setTargetPosition((cm/32*1440)*FOB);
        motorLeft.setPower(FOB);
        motorRight.setPower(FOB);
    }
}