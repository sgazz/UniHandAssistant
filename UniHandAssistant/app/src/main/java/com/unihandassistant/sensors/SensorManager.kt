package com.unihandassistant.sensors

import android.content.Context
import com.unihandassistant.data.models.Settings
import com.unihandassistant.domain.models.ImageState
import com.unihandassistant.domain.usecases.MoveUseCase

class SensorManager(private val context: Context) {
    
    private val gyroscopeSensor = GyroscopeSensor(context)
    private val accelerometerSensor = AccelerometerSensor(context)
    private val moveUseCase = MoveUseCase()
    
    private var currentImageState: ImageState? = null
    private var currentSettings: Settings? = null
    private var onImageStateUpdate: ((ImageState) -> Unit)? = null
    
    init {
        setupSensorListeners()
    }
    
    private fun setupSensorListeners() {
        gyroscopeSensor.setOnGyroscopeDataListener { x, y, z ->
            handleSensorData(x, y, z, 0f, 0f, 0f)
        }
        
        accelerometerSensor.setOnAccelerometerDataListener { x, y, z ->
            handleSensorData(0f, 0f, 0f, x, y, z)
        }
    }
    
    private fun handleSensorData(gyroX: Float, gyroY: Float, gyroZ: Float, accelX: Float, accelY: Float, accelZ: Float) {
        currentImageState?.let { imageState ->
            currentSettings?.let { settings ->
                if (imageState.isMoveMode) {
                    val newState = moveUseCase.calculateMovement(
                        gyroX, gyroY, gyroZ, accelX, accelY, accelZ,
                        imageState, settings
                    )
                    onImageStateUpdate?.invoke(newState)
                }
            }
        }
    }
    
    fun setImageState(imageState: ImageState) {
        currentImageState = imageState
    }
    
    fun setSettings(settings: Settings) {
        currentSettings = settings
    }
    
    fun setOnImageStateUpdateListener(listener: (ImageState) -> Unit) {
        onImageStateUpdate = listener
    }
    
    fun startListening() {
        if (gyroscopeSensor.isAvailable()) {
            gyroscopeSensor.startListening()
        }
        if (accelerometerSensor.isAvailable()) {
            accelerometerSensor.startListening()
        }
    }
    
    fun stopListening() {
        gyroscopeSensor.stopListening()
        accelerometerSensor.stopListening()
    }
    
    fun isAvailable(): Boolean {
        return gyroscopeSensor.isAvailable() && accelerometerSensor.isAvailable()
    }
}
