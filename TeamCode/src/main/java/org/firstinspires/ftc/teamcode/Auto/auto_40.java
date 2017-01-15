package org.firstinspires.ftc.teamcode.Auto;

import android.provider.Settings;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.*;

/**
 * Created by Le Nayad on 1/12/2017.
 */
@Autonomous(name = "Auto_40")
public class auto_40 extends LinearOpMode {
    org.firstinspires.ftc.teamcode.Auto.function function = new function();
    TimerTask tasknew = new TimerTask() {
        @Override
        public void run() {
            telemetry.addData("motorright", function.getCurrentPosition(function.motorRight));
            telemetry.addData("motorleft", function.getCurrentPosition(function.motorLeft));
            telemetry.update();
            function.shootpower(System);
        }
    };
    Timer timer = new Timer();


    public void runOpMode() throws InterruptedException {
        function.init(hardwareMap);
        idle();
        waitForStart();
        timer.scheduleAtFixedRate(tasknew, 0, 1);
        function.shoot(1, 0.22);
        function.drive(150, -1);
        function.drive(20, 1);
        function.drive(45, -1);
    }

}
