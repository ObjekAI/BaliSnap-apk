package com.bangkit.balisnap.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.balisnap.databinding.ItemWisataBinding
import com.bangkit.balisnap.response.DestinationsItem
import com.bumptech.glide.Glide

class DestinationAdapter(private val destinations: List<DestinationsItem?>) :
    RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = destinations[position]
        destination?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = destinations.size

    class DestinationViewHolder(private val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: DestinationsItem) {
            with(binding) {
                tvItemName.text = destination.name
                tvItemDesc.text = destination.description
                Log.d("knlnnn", destination.image.toString())
                Glide.with(itemView.context)
                    .load("https://storage.googleapis.com/balisnap-storage/${destination.image}")
                    .into(imgItemPhoto)
            }
        }
    }
}
