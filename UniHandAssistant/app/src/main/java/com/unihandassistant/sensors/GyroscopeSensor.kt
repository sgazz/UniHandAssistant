package com.unihandassistant.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class GyroscopeSensor(private val context: Context) : SensorEventListener {
    
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscope: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    
    private var onGyroscopeData: ((Float, Float, Float) -> Unit)? = null
    private var isListening = false
    
    fun setOnGyroscopeDataListener(listener: (Float, Float, Float) -> Unit) {
        onGyroscopeData = listener
    }
    
    fun startListening() {
        if (!isListening && gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_GAME)
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
            if (sensorEvent.sensor.type == Sensor.TYPE_GYROSCOPE) {
                val x = sensorEvent.values[0]
                val y = sensorEvent.values[1]
                val z = sensorEvent.values[2]
                
                // Calculate magnitude for filtering
                val magnitude = sqrt(x * x + y * y + z * z)
                
                // Only process if movement is significant enough
                if (magnitude > 0.1f) {
                    onGyroscopeData?.invoke(x, y, z)
                }
            }
        }
    }
    
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if needed
    }
    
    fun isAvailable(): Boolean {
        return gyroscope != null
    }
}
