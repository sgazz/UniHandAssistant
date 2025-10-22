package com.unihandassistant.ui.widgets

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.unihandassistant.databinding.WidgetSettingsRadialMenuBinding

class SettingsRadialMenu @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    
    private val binding: WidgetSettingsRadialMenuBinding
    private var isVisible = false
    private var onSettingsAction: ((SettingsAction) -> Unit)? = null
    
    enum class SettingsAction {
        POSITION_LEFT, POSITION_RIGHT, SENSITIVITY_LOW, SENSITIVITY_MEDIUM, SENSITIVITY_HIGH,
        SPEED_SLOW, SPEED_NORMAL, SPEED_FAST, COMBINATION_STANDARD, COMBINATION_OPPOSITE
    }
    
    init {
        binding = WidgetSettingsRadialMenuBinding.inflate(LayoutInflater.from(context), this, true)
        setupClickListeners()
        visibility = View.GONE
    }
    
    private fun setupClickListeners() {
        // Position buttons
        binding.btnPositionLeft.setOnClickListener { onSettingsAction?.invoke(SettingsAction.POSITION_LEFT) }
        binding.btnPositionRight.setOnClickListener { onSettingsAction?.invoke(SettingsAction.POSITION_RIGHT) }
        
        // Sensitivity buttons
        binding.btnSensitivityLow.setOnClickListener { onSettingsAction?.invoke(SettingsAction.SENSITIVITY_LOW) }
        binding.btnSensitivityMedium.setOnClickListener { onSettingsAction?.invoke(SettingsAction.SENSITIVITY_MEDIUM) }
        binding.btnSensitivityHigh.setOnClickListener { onSettingsAction?.invoke(SettingsAction.SENSITIVITY_HIGH) }
        
        // Speed buttons
        binding.btnSpeedSlow.setOnClickListener { onSettingsAction?.invoke(SettingsAction.SPEED_SLOW) }
        binding.btnSpeedNormal.setOnClickListener { onSettingsAction?.invoke(SettingsAction.SPEED_NORMAL) }
        binding.btnSpeedFast.setOnClickListener { onSettingsAction?.invoke(SettingsAction.SPEED_FAST) }
        
        // Combination buttons
        binding.btnCombinationStandard.setOnClickListener { onSettingsAction?.invoke(SettingsAction.COMBINATION_STANDARD) }
        binding.btnCombinationOpposite.setOnClickListener { onSettingsAction?.invoke(SettingsAction.COMBINATION_OPPOSITE) }
    }
    
    fun setOnSettingsActionListener(listener: (SettingsAction) -> Unit) {
        onSettingsAction = listener
    }
    
    fun show() {
        if (!isVisible) {
            isVisible = true
            visibility = View.VISIBLE
            animateIn()
        }
    }
    
    fun hide() {
        if (isVisible) {
            isVisible = false
            animateOut()
        }
    }
    
    fun toggle() {
        if (isVisible) {
            hide()
        } else {
            show()
        }
    }
    
    private fun animateIn() {
        val animatorSet = AnimatorSet()
        val buttons = listOf(
            binding.btnPositionLeft, binding.btnPositionRight,
            binding.btnSensitivityLow, binding.btnSensitivityMedium, binding.btnSensitivityHigh,
            binding.btnSpeedSlow, binding.btnSpeedNormal, binding.btnSpeedFast,
            binding.btnCombinationStandard, binding.btnCombinationOpposite
        )
        
        buttons.forEachIndexed { index, button ->
            button.alpha = 0f
            button.scaleX = 0f
            button.scaleY = 0f
            
            val animator = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f)
            val scaleX = ObjectAnimator.ofFloat(button, "scaleX", 0f, 1f)
            val scaleY = ObjectAnimator.ofFloat(button, "scaleY", 0f, 1f)
            
            animator.startDelay = (index * 30).toLong()
            scaleX.startDelay = (index * 30).toLong()
            scaleY.startDelay = (index * 30).toLong()
            
            animatorSet.playTogether(animator, scaleX, scaleY)
        }
        
        animatorSet.start()
    }
    
    private fun animateOut() {
        val animatorSet = AnimatorSet()
        val buttons = listOf(
            binding.btnPositionLeft, binding.btnPositionRight,
            binding.btnSensitivityLow, binding.btnSensitivityMedium, binding.btnSensitivityHigh,
            binding.btnSpeedSlow, binding.btnSpeedNormal, binding.btnSpeedFast,
            binding.btnCombinationStandard, binding.btnCombinationOpposite
        )
        
        buttons.forEach { button ->
            val animator = ObjectAnimator.ofFloat(button, "alpha", 1f, 0f)
            val scaleX = ObjectAnimator.ofFloat(button, "scaleX", 1f, 0f)
            val scaleY = ObjectAnimator.ofFloat(button, "scaleY", 1f, 0f)
            
            animatorSet.playTogether(animator, scaleX, scaleY)
        }
        
        animatorSet.start()
        animatorSet.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                visibility = View.GONE
            }
        })
    }
}
