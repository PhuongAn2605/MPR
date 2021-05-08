package com.example.lect5sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.example.lect5sensors.adapters.SensorAdapter;

import java.util.List;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        RecyclerView rvSensors = findViewById(R.id.rvSensors);
        rvSensors.setLayoutManager(new LinearLayoutManager(this));
        SensorAdapter sensorAdapter = new SensorAdapter(sensors);
        rvSensors.setAdapter(sensorAdapter);
    }
}
