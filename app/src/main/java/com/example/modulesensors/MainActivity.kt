package com.example.modulesensors

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java!!.getSimpleName()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_getsensorlist.setOnClickListener {
            var getSensorList=Intent(this,ListOfSensor::class.java)
            startActivity(getSensorList)
            Log.d(TAG,"btn_getsensorlist")
        }

        btn_getaccumelator.setOnClickListener {
            var getAccumulatorState=Intent(this,CheckAccumelatorStatus::class.java)
            startActivity(getAccumulatorState)
            Log.d(TAG,"btn_getaccumelator")
        }
        btn_gettemprature.setOnClickListener {
            var getTempratureStatus=Intent(this,CheckTemratureStatus::class.java)
            startActivity(getTempratureStatus)
            Log.d(TAG,"btn_gettemprature")
        }

        btn_getproximatry.setOnClickListener {
            var getProximatryStatus=Intent(this,CheckProximatryStatus::class.java)
            startActivity(getProximatryStatus)
            Log.d(TAG,"btn_getproximatry")
        }

        btn_getlightsendo.setOnClickListener {
            var getLightyStatus=Intent(this,CheckLightStatus::class.java)
            startActivity(getLightyStatus)
            Log.d(TAG,"btn_getlightsendo")
        }

        btn_opencamerax.setOnClickListener {
            var getCameraX=Intent(this,CameraXExample::class.java)
            startActivity(getCameraX)
            Log.d(TAG,"btn_getlightsendo")
        }

        btn_camera2.setOnClickListener {
            var getCameraApi=Intent(this,Camera2Example::class.java)
            startActivity(getCameraApi)
            Log.d(TAG,"btn_getlightsendo")

        }


    }
}
