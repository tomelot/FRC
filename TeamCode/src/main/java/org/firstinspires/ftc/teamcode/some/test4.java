package org.firstinspires.ftc.teamcode.some;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 11/13/2016.
 */
@TeleOp(name="TeleOp_test4")
public class test4 extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorshotleft;
    private DcMotor motorshotright;
    private Servo servo1;

    @Override
    public void runOpMode() throws InterruptedException
    {

        motorLeft= hardwareMap.dcMotor.get("motorshotleft");
        motorRight=hardwareMap.dcMotor.get("motorshotright");
        motorshotleft=hardwareMap.dcMotor.get("segev1");
        motorshotright=hardwareMap.dcMotor.get("segev2");
        double maxservo1=0;
        double speedmax = 0.5;
        long start_time;
        long check=0;

        long check2 =0 ;
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        start_time = System.currentTimeMillis();
        while(opModeIsActive()) {

            motorLeft.setPower(gamepad1.left_stick_y+gamepad1.left_stick_x*speedmax);
            motorRight.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x*speedmax);
            if(gamepad1.a==true){

                    motorshotleft.setPower(-1);
                    motorshotright.setPower(1);




























                           

            }
            if(gamepad1.b==true){

                motorshotleft.setPower(0);
                motorshotright.setPower(0);

            }

            idle();

        }
    }
}
