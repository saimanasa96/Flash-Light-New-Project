package com.example.flashlightapplication

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private lateinit var toggleFlashLightOnOff: ImageButton
    private var cameraManager: CameraManager? = null
    private var getCameraID: String? = null

    var isImage1Selected = true

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleFlashLightOnOff = findViewById(R.id.toggle_flashlight)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            // O means back camera unit,
            // 1 means front camera unit
            getCameraID = cameraManager!!.cameraIdList[0]
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun toggleFlashLight(view: View?) {
        toggleFlashLightOnOff.setOnClickListener{
            if (isImage1Selected){

                toggleFlashLightOnOff.setImageResource(R.drawable.torch_on)
                try {
                    // true sets the torch in ON mode
                    cameraManager!!.setTorchMode(getCameraID!!, true)
                } catch (e: CameraAccessException) {
                    e.printStackTrace()
                }
            }
            else{
                try {

                    toggleFlashLightOnOff.setImageResource(R.drawable.torch_off)
                    // true sets the torch in OFF mode
                    cameraManager!!.setTorchMode(getCameraID!!, false)
                } catch (e: CameraAccessException) {
                    e.printStackTrace()
                }

            }
            isImage1Selected = !isImage1Selected
        }

    }



}