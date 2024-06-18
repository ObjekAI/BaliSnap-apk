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
import com.bangkit.balisnap.response.FoodsItem
import com.bumptech.glide.Glide

class FoodAdapter : ListAdapter<FoodsItem, FoodAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((FoodsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
        Glide.with(holder.itemView.context)
            .load("https://storage.googleapis.com/balisnap-storage/${story.image}")
            .into(holder.imgstory)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(story)
        }
    }

    inner class ListViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding.rootView) {
        private val nama: TextView = binding.findViewById(R.id.tv_item_name)
        private val foto: ImageView = binding.findViewById(R.id.tv_detail_photo)
        private val deskripsi: TextView = binding.findViewById(R.id.tv_item_desc)

        fun bind(wisata: FoodsItem) {
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
        fun bind(story: FoodsItem) {
            binding.tvItemName.text = story.name
            binding.tvItemDesc.text = story.description
        }
    }

    fun setOnItemClickListener(listener: (FoodsItem) -> Unit) {
        onItemClickListener = listener
    }
    companion object {
        const val IMAGE_STORY = "IMAGE_STORY"
        const val TITLE_STORY = "TITLE_STORY"
        const val DESC_STORY = "DESC_STORY"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodsItem>() {
            override fun areItemsTheSame(oldItem: FoodsItem, newItem: FoodsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: FoodsItem, newItem: FoodsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}