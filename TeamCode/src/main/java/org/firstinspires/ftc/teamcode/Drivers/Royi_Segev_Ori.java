package org.firstinspires.ftc.teamcode.Drivers;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.function_opmode;

public class Royi_Segev_Ori extends LinearOpMode {
    function_opmode function = new function_opmode();
    public void runOpMode() throws InterruptedException {
        function.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            function.drive(gamepad1);
            function.esuf(gamepad2);
            function.shoot(gamepad2);
            function.shootpowerhigh(gamepad2);
            function.shootpowerlow(gamepad2);
        }
    }
}
