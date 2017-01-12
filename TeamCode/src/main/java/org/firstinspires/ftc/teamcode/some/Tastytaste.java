package org.firstinspires.ftc.teamcode.some;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;
import android.os.Message;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Le Nayad on 1/11/2017.
 */
@TeleOp(name="tastytaste")
public class Tastytaste extends LinearOpMode implements SensorEventListener {

    Context context;
    private SensorManager sensorManager;
    private Sensor mGyro;
    float azimuth;
    float[] orientation = new float[3];
    float[] rMat = new float[9];
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public void onSensorChanged(SensorEvent event){

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {

            SensorManager.getRotationMatrixFromVector( rMat, event.values );
            azimuth = (int) ( Math.toDegrees( SensorManager.getOrientation( rMat, orientation )[0] ) + 360 ) % 360;
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        context = hardwareMap.appContext;
        sensorManager = (SensorManager) hardwareMap.appContext.getSystemService(context.SENSOR_SERVICE);
        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener((SensorEventListener) this, mGyro,SensorManager.SENSOR_DELAY_NORMAL);
        waitForStart();
        while(opModeIsActive()==true) {
            telemetry.addData("tom",azimuth);
            telemetry.update();
        }

    }
}
