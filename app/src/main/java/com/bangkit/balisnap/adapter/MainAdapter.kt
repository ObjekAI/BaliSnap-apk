package com.bangkit.balisnap.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bangkit.balisnap.DetailActivity
import com.bangkit.balisnap.R
import com.bangkit.balisnap.databinding.ItemWisataBinding
import com.bangkit.balisnap.response.DestinationsItem

class MainAdapter : ListAdapter<DestinationsItem, MainAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
        Glide.with(holder.imgstory.getContext())
            .load("https://storage.googleapis.com/balisnap-storage/${story.image}")
            .into(holder.imgstory);
        holder.itemView.setOnClickListener {
            val intentDetailStory =
                Intent(holder.itemView.context, DetailActivity::class.java)
            holder.itemView.context.startActivity(intentDetailStory)
        }
    }

    inner class ListViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding.rootView) {
        private val nama: TextView = binding.findViewById(R.id.tv_item_name)
        private val foto: ImageView = binding.findViewById(R.id.tv_detail_photo)
        private val deskripsi: TextView = binding.findViewById(R.id.tv_item_desc)

        fun bind(wisata: DestinationsItem) {
            nama.text = wisata.name
            deskripsi.text = wisata.description
            Log.d("MainAdapter", "Binding story: $wisata.name")
            Glide.with(binding.context)
                .load(wisata.image)
                .into(foto)
        }
    }

    class MyViewHolder(val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgstory: ImageView = itemView.findViewById(R.id.img_item_photo)
        fun bind(story: DestinationsItem) {
            binding.tvItemName.text = "${story.name}"
            binding.tvItemDesc.text = "${story.description}"
        }
    }

    companion object {
        const val IMAGE_STORY = "IMAGE_STORY"
        const val TITLE_STORY = "TITLE_STORY"
        const val DESC_STORY = "DESC_STORY"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DestinationsItem>() {
            override fun areItemsTheSame(oldItem: DestinationsItem, newItem: DestinationsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DestinationsItem, newItem: DestinationsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}