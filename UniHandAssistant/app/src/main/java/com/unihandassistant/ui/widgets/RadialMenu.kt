package com.unihandassistant.ui.widgets

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.unihandassistant.databinding.WidgetRadialMenuBinding

class RadialMenu @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    
    private val binding: WidgetRadialMenuBinding
    private var isVisible = false
    private var onMenuAction: ((MenuAction) -> Unit)? = null
    
    enum class MenuAction {
        ZOOM_IN, ZOOM_OUT, ROTATE_LEFT, ROTATE_RIGHT, RESET, SETTINGS, MOVE
    }
    
    init {
        binding = WidgetRadialMenuBinding.inflate(LayoutInflater.from(context), this, true)
        setupClickListeners()
        visibility = View.GONE
    }
    
    private fun setupClickListeners() {
        binding.btnZoomIn.setOnClickListener { onMenuAction?.invoke(MenuAction.ZOOM_IN) }
        binding.btnZoomOut.setOnClickListener { onMenuAction?.invoke(MenuAction.ZOOM_OUT) }
        binding.btnRotateLeft.setOnClickListener { onMenuAction?.invoke(MenuAction.ROTATE_LEFT) }
        binding.btnRotateRight.setOnClickListener { onMenuAction?.invoke(MenuAction.ROTATE_RIGHT) }
        binding.btnReset.setOnClickListener { onMenuAction?.invoke(MenuAction.RESET) }
        binding.btnSettings.setOnClickListener { onMenuAction?.invoke(MenuAction.SETTINGS) }
        binding.btnMove.setOnClickListener { onMenuAction?.invoke(MenuAction.MOVE) }
    }
    
    fun setOnMenuActionListener(listener: (MenuAction) -> Unit) {
        onMenuAction = listener
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
            binding.btnZoomIn, binding.btnZoomOut, binding.btnRotateLeft,
            binding.btnRotateRight, binding.btnReset, binding.btnSettings, binding.btnMove
        )
        
        buttons.forEachIndexed { index, button ->
            button.alpha = 0f
            button.scaleX = 0f
            button.scaleY = 0f
            
            val animator = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f)
            val scaleX = ObjectAnimator.ofFloat(button, "scaleX", 0f, 1f)
            val scaleY = ObjectAnimator.ofFloat(button, "scaleY", 0f, 1f)
            
            animator.startDelay = (index * 50).toLong()
            scaleX.startDelay = (index * 50).toLong()
            scaleY.startDelay = (index * 50).toLong()
            
            animatorSet.playTogether(animator, scaleX, scaleY)
        }
        
        animatorSet.start()
    }
    
    private fun animateOut() {
        val animatorSet = AnimatorSet()
        val buttons = listOf(
            binding.btnZoomIn, binding.btnZoomOut, binding.btnRotateLeft,
            binding.btnRotateRight, binding.btnReset, binding.btnSettings, binding.btnMove
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
