package com.unihandassistant.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import kotlin.math.pow
import kotlin.math.sqrt

object ThumbZoneUtils {
    
    enum class Position {
        LEFT, RIGHT
    }
    
    fun getThumbZonePosition(context: Context, position: Position): Pair<Float, Float> {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        
        val screenWidth = displayMetrics.widthPixels.toFloat()
        val screenHeight = displayMetrics.heightPixels.toFloat()
        
        return when (position) {
            Position.LEFT -> {
                // Left Thumb Zone: 20% from left edge, 20% from bottom
                Pair(screenWidth * 0.2f, screenHeight * 0.8f)
            }
            Position.RIGHT -> {
                // Right Thumb Zone: 20% from right edge, 20% from bottom
                Pair(screenWidth * 0.8f, screenHeight * 0.8f)
            }
        }
    }
    
    fun isInThumbZone(x: Float, y: Float, context: Context, position: Position): Boolean {
        val (thumbX, thumbY) = getThumbZonePosition(context, position)
        val thumbRadius = 100f // 100dp radius for thumb zone
        
        val distance = sqrt((x - thumbX).toDouble().pow(2) + (y - thumbY).toDouble().pow(2))
        return distance <= thumbRadius
    }
    
    fun getOptimalButtonPositions(context: Context, position: Position): List<Pair<Float, Float>> {
        val (centerX, centerY) = getThumbZonePosition(context, position)
        val radius = 120f // 120dp radius for button positions
        
        return listOf(
            // Top
            Pair(centerX, centerY - radius),
            // Top-right
            Pair(centerX + radius * 0.7f, centerY - radius * 0.7f),
            // Right
            Pair(centerX + radius, centerY),
            // Bottom-right
            Pair(centerX + radius * 0.7f, centerY + radius * 0.7f),
            // Bottom
            Pair(centerX, centerY + radius),
            // Bottom-left
            Pair(centerX - radius * 0.7f, centerY + radius * 0.7f),
            // Left
            Pair(centerX - radius, centerY),
            // Top-left
            Pair(centerX - radius * 0.7f, centerY - radius * 0.7f)
        )
    }
}
