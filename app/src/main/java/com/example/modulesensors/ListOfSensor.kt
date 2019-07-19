package com.example.modulesensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_of_sensor.*

class ListOfSensor : AppCompatActivity() {
    private val TAG = MainActivity::class.java!!.getSimpleName()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_sensor)
        val sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager
        val availabeSenors=sensorManager.getSensorList(Sensor.TYPE_ALL)

        listview_listofsensor.setAdapter(ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1, availabeSenors))
        Log.d(TAG,availabeSenors.toString())
    }
}
