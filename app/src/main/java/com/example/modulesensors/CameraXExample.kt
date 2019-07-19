package com.example.modulesensors

import android.content.Context
import android.graphics.Matrix
import android.graphics.SurfaceTexture
import android.hardware.*
import android.hardware.camera2.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import kotlinx.android.synthetic.main.activity_camerax_example.*
import android.location.Location
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.camera.core.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import java.io.File


class CameraXExample() : AppCompatActivity()  {


    private val TAG: String = this.javaClass.getSimpleName()

    private lateinit var imageCapture: ImageCapture
    private lateinit var lensFacing: CameraX.LensFacing



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camerax_example)
        srf_camera_view.post {
            camaeraInit()
        }

        srf_camera_view.addOnLayoutChangeListener { view, i, j, k, l, m, n, o, p ->
            Log.d(TAG, "addOnLAyoutChage")
            updateScreen()
        }

        btn_takeimage.setOnClickListener {
            var fileLoc = File(cacheDir, "cameraX.jpg")
            imageCapture.takePicture(fileLoc,object  : ImageCapture.OnImageSavedListener{
                override fun onImageSaved(file: File) {
                    Toast.makeText(this@CameraXExample,"CameraX Take Photos", Toast.LENGTH_SHORT).show()
                }

                override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) {
                    Log.d(TAG,"Error")
                }

            })
        }
    }

    private fun camaeraInit() {
        Log.d(TAG,"Start Camera")
        lensFacing=CameraX.LensFacing.BACK

        val config=PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational(1,1))
            setTargetResolution(Size(720,720))
            setLensFacing(lensFacing)
        }

        val cameraPreview=Preview(config.build())

        cameraPreview.setOnPreviewOutputUpdateListener {
            val parentView=srf_camera_view.parent as ViewGroup
            parentView.removeView(srf_camera_view)
            parentView.addView(srf_camera_view,1)

            srf_camera_view.surfaceTexture=it.surfaceTexture
            updateScreen()
        }

        val imageConfig=ImageCaptureConfig.Builder().apply {
            setTargetAspectRatio(Rational(1,1))
            setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
        }
        imageCapture= ImageCapture(imageConfig.build())

        CameraX.bindToLifecycle(this,cameraPreview,imageCapture)
    }

    private fun updateScreen() {
        val matrix = Matrix()

        val centerX = srf_camera_view.width / 2f
        val centerY = srf_camera_view.height / 2f

        val rotationDegrees = when (srf_camera_view.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        srf_camera_view.setTransform(matrix)
    }



}

