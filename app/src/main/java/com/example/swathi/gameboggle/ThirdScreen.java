package com.example.swathi.gameboggle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class ThirdScreen extends AppCompatActivity {
    // the third screen holds the boggle game board


    // Fields specific for the shake detector feature
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        // fields used by SHAKE DETECTOR
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  //get sensor management service, cast as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //get accelerometer from the sensor manager

        // make new SHAKE DETECTOR object and initialize with an onShakeListener
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {

                //TODO   add code here...
                //TODO   generate the board on shake...
                Log.d("ShakeDetector", "Shake Detected!");
            }
        });

    }


    @Override  //onResume from Activity class
    public void onResume() {
        super.onResume();

        // registers the SHAKE DETECTOR event with the System Sensor manager...
        //    ...need to register (onResume) and unregister (onPause) to stop listening to this system service

        // args: sensorEventListener, Sensor, Rate for how often to receive sensor - sensor_delay constant for UIs  )
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }


    @Override  //onPause from Activity class
    public void onPause() {
        super.onPause();
        // ...need unregister SHAKE DETECTOR to stop listening to System Sensor when app is paused
        mSensorManager.unregisterListener(mShakeDetector);

    }


}
