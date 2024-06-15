package com.bangkit.balisnap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.balisnap.databinding.ActivityDetailBinding
import com.bangkit.balisnap.databinding.ActivityLikeBinding
import com.bangkit.balisnap.viewmodel.ViewModelFactory
//import com.bangkit.like.LikeAdapter
import com.bangkit.like.LikeDestination
import com.bangkit.like.LikeViewModel

class LikeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLikeBinding
    private val LikeViewModel: LikeViewModel by viewModels {
        ViewModelFactory.getInstance(application)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        binding.back.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//
//            val layoutManager = LinearLayoutManager(this)
//            binding.recyclerview.layoutManager = layoutManager
//
//            LikeViewModel.getLikeDesti().observe(this) { destifav ->
//                setDataFavorite(destifav)
//
//            }
//        }
//        private fun setDataFavorite(destifav: List<LikeDestination>){
//            val adapter = LikeAdapter()
//            val items = arrayListOf<LikeDestination>()
//            destifav.map {
//                val item = LikeDestination(name = it.name, image = it.image, description = it.description)
//                items.add(item)
//            }
//            adapter.submitList(items)
//            binding.recyclerview.adapter = adapter
//        }
  }
}