package com.unihandassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unihandassistant.databinding.ItemGalleryImageBinding

class GalleryAdapter(
    private val onImageClick: (String) -> Unit
) : ListAdapter<String, GalleryAdapter.ImageViewHolder>(ImageDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemGalleryImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class ImageViewHolder(
        private val binding: ItemGalleryImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(imageUri: String) {
            Glide.with(binding.root.context)
                .load(imageUri)
                .centerCrop()
                .into(binding.imageView)
            
            binding.root.setOnClickListener {
                onImageClick(imageUri)
            }
        }
    }
    
    class ImageDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
