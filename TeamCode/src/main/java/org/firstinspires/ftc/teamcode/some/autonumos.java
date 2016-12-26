package org.firstinspires.ftc.teamcode.some;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;


@Autonomous(name="auto_test")

public class autonumos extends LinearOpMode {
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;

    OpticalDistanceSensor sensor1;
    @Override

    public void runOpMode() throws InterruptedException
    {
        motorLeft= hardwareMap.dcMotor.get("segev1");
        motorRight=hardwareMap.dcMotor.get("segev2");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //Resets encoders
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Sets mode to use encoders
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //setMode() is used instead of setChannelMode(), which is now deprecated
        waitForStart();


        telemetry.addData("Raw",    sensor1.getRawLightDetected());
        telemetry.addData("Normal", sensor1.getLightDetected());

        telemetry.update();


    }



}
