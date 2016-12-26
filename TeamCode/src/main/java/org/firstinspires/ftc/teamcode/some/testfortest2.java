package org.firstinspires.ftc.teamcode.some;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 11/21/2016.
 */

@TeleOp (name="testservo")
public class testfortest2 extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private  DcMotor MotorWheelRight;
    private  DcMotor MotorWheelLeft;
    private Servo IntoWheels;
    long start_time;
    long time_check1=0;
    boolean checkshoot=false;

    @Override

    public void runOpMode() throws InterruptedException {


        motorLeft= hardwareMap.dcMotor.get("motorleft");
        motorRight=hardwareMap.dcMotor.get("motorright");
        MotorWheelRight=hardwareMap.dcMotor.get("motorwheelright");
        MotorWheelLeft=hardwareMap.dcMotor.get("motorwheelleft");
        IntoWheels=hardwareMap.servo.get("intowheels");

        waitForStart();
        start_time = System.currentTimeMillis();
        while(opModeIsActive()) {
            IntoWheels.setPosition(0.8);

           telemetry.addData("servo",IntoWheels.getPosition());
            telemetry.update();
        }

    }
    public void drive(){
        motorLeft.setPower(gamepad1.right_stick_y+gamepad1.left_stick_x);
        motorRight.setPower(gamepad1.right_stick_y-gamepad1.left_stick_x);
    }
    public void shoot(){
        if(start_time-time_check1>=1000)
        {
            IntoWheels.setPosition(0);
            if(IntoWheels.getPosition()==0){
                checkshoot=false;
            }
        }
        else {
            IntoWheels.setPosition(-0.5);
        }
        time_check1 = System.currentTimeMillis();
        telemetry.addData("servo",IntoWheels.getPosition());

    }
    public void shootpowerlow(){
        if(gamepad1.left_trigger>0){
            MotorWheelLeft.setPower(0.5);
            MotorWheelRight.setPower(0.5);

        }
        else{
            MotorWheelLeft.setPower(0);
            MotorWheelRight.setPower(0);
        }
    }
    public void shootpowerhigh(){
        if(gamepad1.right_trigger>0){
            MotorWheelLeft.setPower(0.8);
            MotorWheelRight.setPower(0.8);

        }
        else{
            MotorWheelLeft.setPower(0);
            MotorWheelRight.setPower(0);
        }
    }


}

