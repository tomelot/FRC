package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 11/21/2016.
 */

@TeleOp (name="control-ori")
public class ori extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private  DcMotor MotorWheelRight;
    private  DcMotor MotorWheelLeft;
    private Servo IntoWheels;
    long start_time;
    long time_check1=0;
    boolean checkshoot=false;
    boolean checkshootinglow=false;
    boolean checkshootinghigh=false;
    boolean toggle1=false;
    boolean toggle2=false;

    @Override

    public void runOpMode() throws InterruptedException {


        motorLeft= hardwareMap.dcMotor.get("motorleft");
        motorRight=hardwareMap.dcMotor.get("motorright");
        MotorWheelRight=hardwareMap.dcMotor.get("motorwheelright");
        MotorWheelLeft=hardwareMap.dcMotor.get("motorwheelleft");
        IntoWheels=hardwareMap.servo.get("intowheels");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        IntoWheels.setPosition(0.8);
        waitForStart();
        start_time = System.currentTimeMillis();
        while(opModeIsActive()) {
            start_time = System.currentTimeMillis();
            drive();
            shootpowerhigh();
            shootpowerlow();
           shoot();

        }

    } 
    public void drive(){
        motorLeft.setPower(gamepad1.left_stick_y);
        motorRight.setPower(gamepad1.right_stick_y);
    }
    public void shoot(){
        if(gamepad1.a==true) {
            if (checkshoot == false) {
                checkshoot = true;
                time_check1 = start_time +2000;
                IntoWheels.setPosition(0.2);
            }
        }
        if(time_check1<=start_time&&time_check1>0) {
            time_check1=0;
            IntoWheels.setPosition(0.8);
            checkshoot=false;

        }
        telemetry.addData("servo",IntoWheels.getPosition());

    }
    public void shootpowerlow(){
            if (gamepad1.x==true) {
                if(toggle1==false) {
                    MotorWheelLeft.setPower(0.27);
                    MotorWheelRight.setPower(0.27);
                    toggle1=true;
                }else {
                    MotorWheelLeft.setPower(0);
                    MotorWheelRight.setPower(0);
                    toggle1=false;
                }

            }


        telemetry.addData("speed",motorLeft.getPower());
        telemetry.update();
    }
    public void shootpowerhigh() {
            if (gamepad1.right_bumper == true) {
                if(toggle2==false){
                MotorWheelLeft.setPower(0.35);
                MotorWheelRight.setPower(0.35);
                toggle2=true;
            }
                else {
                MotorWheelLeft.setPower(0);
                MotorWheelRight.setPower(0);
                toggle2=false;

                }


        }

    }
}

