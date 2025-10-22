package com.unihandassistant.utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import com.unihandassistant.data.models.Settings

class ThumbZoneTester(private val context: Context) {
    
    private var onTouchListener: View.OnTouchListener? = null
    private var testResults: MutableList<TouchEvent> = mutableListOf()
    
    data class TouchEvent(
        val x: Float,
        val y: Float,
        val timestamp: Long,
        val isInThumbZone: Boolean
    )
    
    fun startTesting(view: View, settings: Settings) {
        onTouchListener = View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val position = when (settings.position) {
                    Settings.Position.LEFT -> ThumbZoneUtils.Position.LEFT
                    Settings.Position.RIGHT -> ThumbZoneUtils.Position.RIGHT
                }
                
                val isInThumbZone = ThumbZoneUtils.isInThumbZone(
                    event.x, event.y, context, position
                )
                
                testResults.add(
                    TouchEvent(
                        x = event.x,
                        y = event.y,
                        timestamp = System.currentTimeMillis(),
                        isInThumbZone = isInThumbZone
                    )
                )
            }
            false
        }
        
        view.setOnTouchListener(onTouchListener)
    }
    
    fun stopTesting(view: View) {
        view.setOnTouchListener(null)
        onTouchListener = null
    }
    
    fun getTestResults(): List<TouchEvent> {
        return testResults.toList()
    }
    
    fun getThumbZoneAccuracy(): Float {
        if (testResults.isEmpty()) return 0f
        
        val inThumbZoneCount = testResults.count { it.isInThumbZone }
        return (inThumbZoneCount.toFloat() / testResults.size) * 100f
    }
    
    fun getAverageTouchPosition(): Pair<Float, Float> {
        if (testResults.isEmpty()) return Pair(0f, 0f)
        
        val avgX = testResults.map { it.x }.average().toFloat()
        val avgY = testResults.map { it.y }.average().toFloat()
        
        return Pair(avgX, avgY)
    }
    
    fun clearResults() {
        testResults.clear()
    }
}
