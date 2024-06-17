package com.bangkit.balisnap.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.balisnap.R
import com.bangkit.balisnap.databinding.ItemWisataBinding
import com.bangkit.balisnap.response.DestinationsItem
import com.bumptech.glide.Glide

class DestinationAdapter : ListAdapter<DestinationsItem, DestinationAdapter.DestinationViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((DestinationsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = getItem(position)
        holder.bind(destination)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(destination)
        }
    }

    fun setOnItemClickListener(listener: (DestinationsItem) -> Unit) {
        onItemClickListener = listener
    }

    class DestinationViewHolder(private val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nama: TextView = binding.tvItemName
        private val foto: ImageView = binding.imgItemPhoto
        private val deskripsi: TextView = binding.tvItemDesc

        fun bind(destination: DestinationsItem) {
            nama.text = destination.name
            deskripsi.text = destination.description
            Log.d("DestinationAdapter", "Binding destination: ${destination.name}")
            Glide.with(binding.root.context)
                .load("https://storage.googleapis.com/balisnap-storage/${destination.image}")
                .into(foto)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DestinationsItem>() {
            override fun areItemsTheSame(oldItem: DestinationsItem, newItem: DestinationsItem): Boolean {
                return oldItem.id == newItem.id // Assuming DestinationsItem has an id field
            }

            override fun areContentsTheSame(oldItem: DestinationsItem, newItem: DestinationsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
