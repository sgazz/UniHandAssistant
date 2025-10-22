package com.unihandassistant.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class AccelerometerSensor(private val context: Context) : SensorEventListener {
    
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    
    private var onAccelerometerData: ((Float, Float, Float) -> Unit)? = null
    private var isListening = false
    
    fun setOnAccelerometerDataListener(listener: (Float, Float, Float) -> Unit) {
        onAccelerometerData = listener
    }
    
    fun startListening() {
        if (!isListening && accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
            isListening = true
        }
    }
    
    fun stopListening() {
        if (isListening) {
            sensorManager.unregisterListener(this)
            isListening = false
        }
    }
    
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let { sensorEvent ->
            if (sensorEvent.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val x = sensorEvent.values[0]
                val y = sensorEvent.values[1]
                val z = sensorEvent.values[2]
                
                // Calculate magnitude for filtering
                val magnitude = sqrt(x * x + y * y + z * z)
                
                // Only process if movement is significant enough
                if (magnitude > 0.5f) {
                    onAccelerometerData?.invoke(x, y, z)
                }
            }
        }
    }
    
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if needed
    }
    
    fun isAvailable(): Boolean {
        return accelerometer != null
    }
}
