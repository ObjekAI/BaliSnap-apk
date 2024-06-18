package com.bangkit.balisnap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.balisnap.databinding.ActivityLikeBinding
import com.bangkit.balisnap.like.LikeAdapter
import com.bangkit.balisnap.viewmodel.LikeViewModel
import com.bangkit.balisnap.viewmodel.ViewModelFactory


class LikeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLikeBinding
    private lateinit var adapter: LikeAdapter

    private val viewModel by viewModels<LikeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setupRecyclerView()

        viewModel.getFavoriteUser().observe(this) { favorites ->
            adapter.submitList(favorites)
        }
    }

    private fun setupRecyclerView() {
        adapter = LikeAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }
}