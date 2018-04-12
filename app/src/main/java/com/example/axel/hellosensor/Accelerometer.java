package com.example.axel.hellosensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    TextView txt_X;
    TextView txt_Y;
    TextView txt_Z;
    TextView txt_Pich;
    TextView txt_Roll;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private float lastX, lastY, lastZ;
    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    private String dirText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        txt_X = (TextView) findViewById(R.id.xValue);
        txt_Y = (TextView) findViewById(R.id.yValue);
        txt_Z = (TextView) findViewById(R.id.zValue);
        txt_Pich = (TextView) findViewById(R.id.direction);
        txt_Roll = (TextView) findViewById(R.id.direction2);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // For pitch and roll
        double pich;
        double roll;
        double mX = lastX;
        double mY = lastY;
        double mZ = lastZ;

        // get the change of the x,y,z values of the accelerometer
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        // if the change is below 2, it is just plain noise
        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        if (deltaZ < 2)
            deltaZ = 0;

        // set the last know values of x,y,z
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];

        txt_X.setText(Float.toString(deltaX));
        txt_Y.setText(Float.toString(deltaY));
        txt_Z.setText(Float.toString(deltaZ));

        pich = Math.atan2(mY, mZ);
        roll = Math.atan2((- mX), Math.sqrt(mY * mY + mY * mZ));

        if(pich > 0 ){
            txt_Pich.setText("Upp");
        }
        else if (pich < 0){
            txt_Pich.setText("Down");
        }

        if(roll > 0 ){
            txt_Roll.setText("Right");
        }
        else if (roll < 0){
            txt_Roll.setText("Left");
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
