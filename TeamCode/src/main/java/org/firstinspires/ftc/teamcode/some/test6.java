package org.firstinspires.ftc.teamcode.some;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Le Nayad on 11/21/2016.
 */

@TeleOp (name="joy2")
public class test6 extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private  DcMotor pic;

     double check = -1;

    @Override
    public void runOpMode() throws InterruptedException {


            motorLeft= hardwareMap.dcMotor.get("segev1");
                waitForStart();
        while(opModeIsActive()) {

        }

    }


}
