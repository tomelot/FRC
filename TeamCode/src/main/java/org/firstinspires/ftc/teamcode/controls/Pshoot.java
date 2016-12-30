package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 11/21/2016.
 */

public class Pshoot  {
    private  DcMotor MotorWheelRight;
    private  DcMotor MotorWheelLeft;
    int Left;
    int Right;
    int error;
    public void P(long time){
        Left=MotorWheelLeft.getCurrentPosition();
        Right=MotorWheelRight.getCurrentPosition();

        error= Math.abs(Left-Right);
        if(Left>Right){

        }
    }
}
