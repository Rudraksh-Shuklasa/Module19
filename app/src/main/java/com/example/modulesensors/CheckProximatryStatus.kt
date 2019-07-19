package com.example.modulesensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_check_proximatry_status.*

class CheckProximatryStatus : AppCompatActivity(), SensorEventListener {
    private val SENSOR_SENSITIVITY = 1
    private val TAG = MainActivity::class.java!!.getSimpleName()

    lateinit var sensorManager : SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_proximatry_status)

        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                txt_device_postion.text="near"
                Log.d(TAG,"Proxi sensor change")

            } else {

                txt_device_postion.text="far"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener( this,sensorManager.getDefaultSensor
            (Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL)
        Log.d(TAG,"Reg Proxi sensor change")

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        Log.d(TAG,"UnReg Proxi sensor change")
    }

}
