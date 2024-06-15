package com.bangkit.like

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.balisnap.DetailActivity
import com.bangkit.balisnap.R
import com.bangkit.balisnap.adapter.MainAdapter
import com.bangkit.balisnap.adapter.MainAdapter.Companion.DIFF_CALLBACK
import com.bangkit.balisnap.databinding.ItemWisataBinding
import com.bumptech.glide.Glide

//class LikeAdapter : ListAdapter<LikeDestination, LikeAdapter.MyViewHolder>(DIFF_CALLBACK) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
//        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MainAdapter.MyViewHolder(binding)
//    }
//
////    override fun onBindViewHolder(holder: MainAdapter.MyViewHolder, position: Int) {
////        val destifav = getItem(position)
////        holder.bind(destifav)
////        Glide.with(holder.fotoProfile.getContext())
////            .load(destifav.image)
////            .into(holder.fotoProfile);
////
//////        holder.itemView.setOnClickListener {
//////            val intentDetailUserGithub =
//////                Intent(holder.itemView.context, DetailActivity::class.java)
//////            intentDetailUserGithub.putExtra(DetailActivity.EXTRA_USER, destifav.name)
//////            intentDetailUserGithub.putExtra(DetailActivity.EXTRA_AVATAR, destifav.image)
//////            holder.itemView.context.startActivity(intentDetailUserGithub)
//////        }
//
//    }
//    class MyViewHolder(val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root) {
//        val fotoProfile: ImageView = itemView.findViewById(R.id.img_item_photo)
//        fun bind(destifav: LikeDestination) {
//            binding.tv_item_name.text = destifav.name
//        }
//    }
//    companion object {
//        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LikeDestination>() {
//            override fun areItemsTheSame(oldItem: LikeDestination, newItem: LikeDestination): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(oldItem: LikeDestination, newItem: LikeDestination): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//}
