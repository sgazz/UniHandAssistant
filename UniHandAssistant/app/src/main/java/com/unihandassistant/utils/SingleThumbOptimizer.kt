package com.unihandassistant.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.unihandassistant.data.models.Settings
import kotlin.math.pow
import kotlin.math.sqrt

object SingleThumbOptimizer {
    
    fun optimizeForSingleThumb(view: ViewGroup, settings: Settings) {
        // Ensure all interactive elements are within Thumb Zone
        val position = when (settings.position) {
            Settings.Position.LEFT -> ThumbZoneUtils.Position.LEFT
            Settings.Position.RIGHT -> ThumbZoneUtils.Position.RIGHT
        }
        
        val (centerX, centerY) = ThumbZoneUtils.getThumbZonePosition(view.context, position)
        val thumbRadius = 150f // 150dp radius for comfortable thumb reach
        
        // Optimize all child views
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            optimizeChildForSingleThumb(child, centerX, centerY, thumbRadius)
        }
    }
    
    private fun optimizeChildForSingleThumb(child: View, centerX: Float, centerY: Float, thumbRadius: Float) {
        val layoutParams = child.layoutParams as? ViewGroup.MarginLayoutParams ?: return
        
        // Calculate distance from thumb center
        val currentX = layoutParams.leftMargin.toFloat()
        val currentY = layoutParams.topMargin.toFloat()
        val distance = sqrt(
            (currentX - centerX).toDouble().pow(2) + (currentY - centerY).toDouble().pow(2)
        )
        
        // If outside thumb radius, move closer
        if (distance > thumbRadius) {
            val angle = kotlin.math.atan2(
                (currentY - centerY).toDouble(),
                (currentX - centerX).toDouble()
            )
            
            val newX = centerX + (thumbRadius * kotlin.math.cos(angle)).toFloat()
            val newY = centerY + (thumbRadius * kotlin.math.sin(angle)).toFloat()
            
            layoutParams.leftMargin = newX.toInt()
            layoutParams.topMargin = newY.toInt()
            child.layoutParams = layoutParams
        }
    }
    
    fun ensureMinimumTouchTarget(view: View) {
        // Ensure minimum touch target size (48dp as per Material Design)
        val minSize = 48 * view.context.resources.displayMetrics.density
        
        if (view.width < minSize) {
            view.layoutParams.width = minSize.toInt()
        }
        
        if (view.height < minSize) {
            view.layoutParams.height = minSize.toInt()
        }
    }
    
    fun optimizeSpacing(views: List<View>, settings: Settings) {
        val position = when (settings.position) {
            Settings.Position.LEFT -> ThumbZoneUtils.Position.LEFT
            Settings.Position.RIGHT -> ThumbZoneUtils.Position.RIGHT
        }
        
        val positions = ThumbZoneUtils.getOptimalButtonPositions(views.first().context, position)
        
        views.forEachIndexed { index, view ->
            if (index < positions.size) {
                val (x, y) = positions[index]
                val layoutParams = view.layoutParams as? ViewGroup.MarginLayoutParams ?: return@forEachIndexed
                
                layoutParams.leftMargin = x.toInt()
                layoutParams.topMargin = y.toInt()
                view.layoutParams = layoutParams
            }
        }
    }
}
