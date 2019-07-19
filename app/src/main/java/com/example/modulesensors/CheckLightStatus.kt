package com.example.modulesensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_check_light_status.*

class CheckLightStatus : AppCompatActivity(), SensorEventListener {
    private val TAG = MainActivity::class.java!!.getSimpleName()

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d(TAG,"Light sensor change")
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {

        txt_light_status.text=sensorEvent!!.values[0].toString()
    }

    lateinit var sensorManager : SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_light_status)
        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,sensorManager.getDefaultSensor
            (Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL)
        Log.d(TAG,"Reg Light sensor change")

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        Log.d(TAG,"UnReg Light sensor change")
    }
}
