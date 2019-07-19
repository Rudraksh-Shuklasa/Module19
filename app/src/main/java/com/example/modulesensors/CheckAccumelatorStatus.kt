package com.example.modulesensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_check_accumelator_status.*

class CheckAccumelatorStatus : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager : SensorManager
    private val TAG = MainActivity::class.java!!.getSimpleName()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_accumelator_status)
        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager.registerListener( this,sensorManager.getDefaultSensor
            (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
         txt_xaxis.text = event!!.values[0].toString()
         txt_yaxis.text= event!!.values[1].toString()
         txt_zaxis.text= event!!.values[2].toString()
        Log.d(TAG,"Light sensor change")

    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,sensorManager.getDefaultSensor
            (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        Log.d(TAG,"Reg Acculator sensor change")

    }

    override fun onPause() {
        super.onPause()
       sensorManager.unregisterListener(this)
        Log.d(TAG,"Reg Acculator sensor change")
    }
}
