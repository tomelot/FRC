package org.firstinspires.ftc.teamcode.some;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Le Nayad on 11/7/2016.
 */
@TeleOp(name="segev")
public class Liron2 extends LinearOpMode {
   DcMotor tom1;
    DcMotor tom2;
    Servo tom12;
    @Override
    public void runOpMode() throws InterruptedException
    {
        tom1=hardwareMap.dcMotor.get("segev1");
        tom2=hardwareMap.dcMotor.get("segev2");
        tom12=hardwareMap.servo.get("segev3");
        waitForStart();
        while(opModeIsActive()==true) {
            tom1.setPower(gamepad1.right_stick_y+gamepad1.right_stick_x);
            tom2.setPower(gamepad1.right_stick_y-gamepad1.right_stick_x);
            idle();

        }
    }

}

