package com.example.modulesensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_check_temrature_status.*

class CheckTemratureStatus : AppCompatActivity(), SensorEventListener {
    lateinit var sensorManager : SensorManager
    private val TAG = MainActivity::class.java!!.getSimpleName()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_temrature_status)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        txt_temprature.text = sensorEvent!!.values[0].toString()
        Log.d(TAG,"Temp sensor change")
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,sensorManager.getDefaultSensor
            (Sensor.TYPE_AMBIENT_TEMPERATURE), SensorManager.SENSOR_DELAY_NORMAL)
        Log.d(TAG,"Reg Temp sensor change")

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        Log.d(TAG,"UnReg Temp sensor change")
    }

}
