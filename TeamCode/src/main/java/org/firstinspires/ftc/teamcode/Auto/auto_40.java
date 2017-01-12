package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Le Nayad on 1/12/2017.
 */
@Autonomous(name = "Auto_40")
public class auto_40 extends LinearOpMode {
    org.firstinspires.ftc.teamcode.Auto.function function = new function();



    public void runOpMode() throws InterruptedException {
        function.in();
        waitForStart();
        while(opModeIsActive()) {
            function.shoot(1, 0.25);
        }
    }

}
