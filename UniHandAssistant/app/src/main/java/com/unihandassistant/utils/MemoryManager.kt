package com.unihandassistant.utils

import android.content.ComponentCallbacks2
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import com.unihandassistant.data.models.Settings

object MemoryManager {
    
    private const val MAX_MEMORY = 1024 * 1024 * 8 // 8MB
    private const val CACHE_SIZE = 10
    
    private var imageCache: LruCache<String, Bitmap>? = null
    
    fun initializeCache(context: Context) {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8 // Use 1/8th of available memory
        
        imageCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return bitmap.byteCount / 1024
            }
        }
    }
    
    fun loadImage(context: Context, imageUri: String, settings: Settings): Bitmap? {
        // Check cache first
        imageCache?.get(imageUri)?.let { return it }
        
        // Load image with appropriate size based on settings
        val sampleSize = getSampleSize(settings)
        val options = BitmapFactory.Options().apply {
            inSampleSize = sampleSize
            inJustDecodeBounds = false
        }
        
        val bitmap = BitmapFactory.decodeFile(imageUri, options)
        bitmap?.let {
            imageCache?.put(imageUri, it)
        }
        
        return bitmap
    }
    
    private fun getSampleSize(settings: Settings): Int {
        return when (settings.sensitivity) {
            Settings.Sensitivity.LOW -> 4 // Lower quality, less memory
            Settings.Sensitivity.MEDIUM -> 2 // Balanced quality
            Settings.Sensitivity.HIGH -> 1 // High quality, more memory
        }
    }
    
    fun clearCache() {
        imageCache?.evictAll()
    }
    
    fun getCacheSize(): Int {
        return imageCache?.size() ?: 0
    }
    
    fun getMaxCacheSize(): Int {
        return imageCache?.maxSize() ?: 0
    }
    
    fun optimizeMemoryUsage() {
        // Clear unused resources
        System.gc()
        
        // Trim memory if needed
        if (getCacheSize() > getMaxCacheSize() * 0.8) {
            clearCache()
        }
    }
    
    fun onLowMemory() {
        // Clear cache when memory is low
        clearCache()
        System.gc()
    }
    
    fun onTrimMemory(level: Int) {
        when (level) {
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> {
                // Clear some cache
                imageCache?.trimToSize(imageCache?.maxSize() ?: 0 / 2)
            }
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
                // Clear all cache
                clearCache()
            }
        }
    }
}
