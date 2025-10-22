package com.unihandassistant.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.unihandassistant.databinding.ActivityGalleryBinding
import com.unihandassistant.ui.adapters.GalleryAdapter

class GalleryActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var galleryAdapter: GalleryAdapter
    
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            loadImages()
        } else {
            finish()
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        checkPermissions()
    }
    
    private fun setupRecyclerView() {
        galleryAdapter = GalleryAdapter { imageUri ->
            val resultIntent = Intent().apply {
                putExtra("selected_image_uri", imageUri)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@GalleryActivity, 3)
            adapter = galleryAdapter
        }
    }
    
    private fun checkPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImages()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
        }
    }
    
    private fun loadImages() {
        // TODO: Load images from device gallery
        galleryAdapter.submitList(emptyList())
    }
}
