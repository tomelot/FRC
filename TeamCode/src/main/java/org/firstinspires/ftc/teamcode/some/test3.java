package org.firstinspires.ftc.teamcode.some;
import java.lang.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by Le Nayad on 11/13/2016.
 */

@TeleOp(name="TeleOp_test3")
public class test3 extends LinearOpMode {
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
        long start_time;
        long check=0;
        long check2 =0 ;
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        start_time = System.currentTimeMillis();
        while(opModeIsActive()) {
            start_time = System.currentTimeMillis();
            if (gamepad1.b==true) {
                if(maxservo1 < 1) {
                    if(start_time-check>=50) {
                        maxservo1 += 0.1;
                        servo1.setPosition(maxservo1);
                        check = start_time;
                    }
                }
            }
            if(gamepad1.a==true){

                if(maxservo1>0){
                    if(start_time-check>=50) {
                        maxservo1 -= 0.1;
                        servo1.setPosition(maxservo1);
                        check = start_time;
                    }
                }
            }

            if(gamepad1.left_bumper==true){
                if(speedmax>0.5) {
                    if(start_time-check2>=50) {
                        speedmax -= 0.1;
                        check2 = start_time;
                    }

                }
            }
            if(gamepad1.right_bumper==true){
                if (speedmax< 1) {
                    speedmax += 0.1;
                    check2 = start_time;
                }
            }
            motorLeft.setPower(gamepad1.left_stick_y+gamepad1.left_stick_x*speedmax);
            motorRight.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x*speedmax);
            telemetry.addData("speedmax: ",Double.toString(speedmax));
            telemetry.addData("servo: ",Double.toString(servo1.getPosition()));
            telemetry.addData("b",Boolean.toString(gamepad1.b));
            telemetry.update();
            idle();

        }
    }
}
