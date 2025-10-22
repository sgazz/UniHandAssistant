package com.unihandassistant.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.unihandassistant.data.models.Settings
import com.unihandassistant.utils.ThumbZoneUtils

class ThumbZoneOptimizedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    
    private var currentPosition: ThumbZoneUtils.Position = ThumbZoneUtils.Position.RIGHT
    
    fun setThumbZonePosition(position: ThumbZoneUtils.Position) {
        currentPosition = position
        updateLayout()
    }
    
    fun setThumbZonePosition(settings: Settings) {
        currentPosition = when (settings.position) {
            Settings.Position.LEFT -> ThumbZoneUtils.Position.LEFT
            Settings.Position.RIGHT -> ThumbZoneUtils.Position.RIGHT
        }
        updateLayout()
    }
    
    private fun updateLayout() {
        val (centerX, centerY) = ThumbZoneUtils.getThumbZonePosition(context, currentPosition)
        
        // Update layout parameters for all child views
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            updateChildPosition(child, centerX, centerY)
        }
    }
    
    private fun updateChildPosition(child: View, centerX: Float, centerY: Float) {
        val layoutParams = child.layoutParams as? LayoutParams ?: return
        
        // Calculate optimal position based on Thumb Zone
        val positions = ThumbZoneUtils.getOptimalButtonPositions(context, currentPosition)
        
        // Apply position to child view
        if (childCount > 0) {
            val index = indexOfChild(child)
            if (index < positions.size) {
                val (x, y) = positions[index]
                layoutParams.leftMargin = x.toInt()
                layoutParams.topMargin = y.toInt()
                child.layoutParams = layoutParams
            }
        }
    }
    
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            updateLayout()
        }
    }
}
