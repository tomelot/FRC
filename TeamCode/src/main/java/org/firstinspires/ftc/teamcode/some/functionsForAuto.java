package org.firstinspires.ftc.teamcode.some;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class functionsForAuto {
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;

    public void tomdrive(double dic, double speed) {
        dic = dic * 45.1147 * -1;
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (motorLeft.getCurrentPosition() >= dic && motorRight.getCurrentPosition() >= dic) {
            motorRight.setPower(speed);
            motorLeft.setPower(speed);
        }
    }
    public void tomturnInPlace(double angle , double speed) {
        angle = (4/360)* angle;
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if(speed == 1){
            while (motorLeft.getCurrentPosition() >= angle && motorRight.getCurrentPosition() <= angle) {
                motorRight.setPower(-speed);
                motorLeft.setPower(speed);
            }
        }
        else{
            while (motorLeft.getCurrentPosition() <= angle && motorRight.getCurrentPosition() >= angle) {
                motorRight.setPower(-speed);
                motorLeft.setPower(speed);
            }
        }
    }
}
