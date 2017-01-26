package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.function;


/**
 * Created by Le Nayad on 1/12/2017.
 */
@Autonomous(name = "Auto_40")
public class auto_40 extends LinearOpMode {
    org.firstinspires.ftc.teamcode.function function = new function();


    public void runOpMode() throws InterruptedException {
        function.init(hardwareMap);
        waitForStart();
        function.shoot(1, 0.135);
        function.drive(228,1);
    }

}
