package org.firstinspires.ftc.teamcode.some;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 11/7/2016.
 */
@TeleOp(name="TeleOp_test2")
public class TeleOp_test2 extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private Servo servo1;
    @Override
    public void runOpMode() throws InterruptedException
    {

        motorLeft= hardwareMap.dcMotor.get("segev1");
        motorRight=hardwareMap.dcMotor.get("segev2");
        servo1 = hardwareMap.servo.get("tom1");
        double maxservo1=0;
        double speedmax = 0.5;
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        while(opModeIsActive()) {
            if (gamepad1.b==true) {
                if(maxservo1 < 1) {
                    maxservo1+=0.1;
                    servo1.setPosition(maxservo1);

                }
            }
            if(gamepad1.a==true){

                if(maxservo1>0){

                    maxservo1-=0.1;
                    servo1.setPosition(maxservo1);

                }
            }

            if(gamepad1.left_bumper==true){
                if(speedmax>0.5) {
                    speedmax -= 0.1;


                }
            }
            if(gamepad1.right_bumper==true){
                if (speedmax< 1) {
                    speedmax += 0.1;
                }
            }
           motorLeft.setPower(gamepad1.left_stick_y+gamepad1.left_stick_x*speedmax);
           motorRight.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x*speedmax);
           telemetry.addData("speedmax: ",Double.toString(speedmax));
            telemetry.addData("servo: ",Double.toString(servo1.getPosition()));
            telemetry.update();
            idle();

        }
    }
}
