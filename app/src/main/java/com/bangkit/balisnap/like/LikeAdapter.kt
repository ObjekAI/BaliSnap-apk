package com.bangkit.balisnap.like

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.balisnap.DetailActivity
import com.bangkit.balisnap.databinding.ItemWisataBinding
import com.bumptech.glide.Glide

class LikeAdapter : ListAdapter<LikeDestination, LikeAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val destifav = getItem(position)
        holder.bind(destifav)

        // Load image using Glide
        Glide.with(holder.photoProfile.context)
            .load(destifav.image)
            .into(holder.photoProfile)

        // Set click listener for item view to navigate to detail activity
        holder.itemView.setOnClickListener {
            val intentDetailDestination = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.IMAGE_STORY, destifav.image)
                putExtra(DetailActivity.TITLE_STORY, destifav.name)
                putExtra(DetailActivity.DESC_STORY, destifav.description)
            }
            holder.itemView.context.startActivity(intentDetailDestination)
        }
    }

    inner class MyViewHolder(val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root) {
        val photoProfile: ImageView = binding.imgItemPhoto
        fun bind(destifav: LikeDestination) {
            binding.tvItemName.text = destifav.name
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LikeDestination>() {
            override fun areItemsTheSame(oldItem: LikeDestination, newItem: LikeDestination): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: LikeDestination, newItem: LikeDestination): Boolean {
                return oldItem == newItem
            }
        }
    }
}
