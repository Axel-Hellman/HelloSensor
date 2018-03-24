package com.example.axel.hellosensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void compass(View view){
    Intent intent = new Intent(this, CompassActivity.class);
    startActivity(intent);
    }

    public void Accelerometer(View view){
        Intent intent = new Intent(this, Accelerometer.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
