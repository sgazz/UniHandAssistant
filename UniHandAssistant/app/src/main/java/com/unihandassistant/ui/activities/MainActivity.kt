package com.unihandassistant.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.unihandassistant.databinding.ActivityMainBinding
import com.unihandassistant.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    
    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.getStringExtra("selected_image_uri")
            imageUri?.let { uri ->
                viewModel.loadImage(uri)
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupObservers() {
        viewModel.imageState.observe(this) { state ->
            binding.imageView.setImageState(state)
        }
        
        viewModel.isRadialMenuVisible.observe(this) { isVisible ->
            binding.radialMenu.visibility = if (isVisible) android.view.View.VISIBLE else android.view.View.GONE
        }
    }
    
    private fun setupClickListeners() {
        binding.imageView.setOnClickListener {
            viewModel.toggleRadialMenu()
        }
        
        binding.radialMenu.setOnMenuActionListener { action ->
            when (action) {
                RadialMenu.MenuAction.ZOOM_IN -> viewModel.zoomIn()
                RadialMenu.MenuAction.ZOOM_OUT -> viewModel.zoomOut()
                RadialMenu.MenuAction.ROTATE_LEFT -> viewModel.rotateLeft()
                RadialMenu.MenuAction.ROTATE_RIGHT -> viewModel.rotateRight()
                RadialMenu.MenuAction.RESET -> viewModel.resetImage()
                RadialMenu.MenuAction.SETTINGS -> viewModel.openSettings()
                RadialMenu.MenuAction.MOVE -> viewModel.toggleMoveMode()
            }
        }
    }
}
