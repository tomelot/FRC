package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 11/21/2016.
 */

@TeleOp (name="control-yair")
public class yair extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private  DcMotor MotorWheelRight;
    private  DcMotor MotorWheelLeft;
    private Servo IntoWheels;
    long start_time;
    long time_check1=0;
    long time_check2=0;
    long time_check3=0;
    boolean checkshoot=false;
    boolean toggle_Mode=false;
    boolean toggle1=false;
    boolean first=false;
    boolean first2=false;

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
            shootpower();
            togglemode();
           shoot();
            telemetry.addData("triggermode", toggle_Mode);
            telemetry.update();
        }

    } 
    public void drive(){
        motorLeft.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x);
        motorRight.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x);
    }
    public void shoot() {
        if (gamepad1.a == true) {
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
        telemetry.addData("servo", gamepad1.left_trigger);

    }
    public void togglemode(){
        if(gamepad1.left_trigger==1&&first==false){
            time_check2=start_time;
            first=true;
        }
        if(gamepad1.left_trigger==0) {
            if ((start_time - time_check2) <=500) {
                if((start_time-time_check3)>=500 &&time_check3>0){
                    MotorWheelLeft.setPower(0);
                    MotorWheelRight.setPower(0);
                    toggle_Mode = false;
                    time_check3=0;
                    first=false;
                }
              else {
                    toggle_Mode = true;
                    MotorWheelLeft.setPower(0.27);
                    MotorWheelRight.setPower(0.27);
                    first=false;
                }
            }
        }
    }
    public void shootpower() {

        if(toggle_Mode==false){
            if(gamepad1.left_trigger>0){
                MotorWheelLeft.setPower(0.35);
                MotorWheelRight.setPower(0.35);
            }
            else {
                MotorWheelLeft.setPower(0);
                MotorWheelRight.setPower(0);
                first=false;
            }
        }
    }




}

