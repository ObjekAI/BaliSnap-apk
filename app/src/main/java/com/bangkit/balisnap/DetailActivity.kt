package com.bangkit.balisnap

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bangkit.balisnap.adapter.MainAdapter
import com.bangkit.balisnap.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()

        val image = intent.extras?.getString(MainAdapter.IMAGE_STORY).toString()
        val title = intent.extras?.getString(MainAdapter.TITLE_STORY).toString()
        val desc = intent.extras?.getString(MainAdapter.DESC_STORY).toString()

        Glide.with(this)
            .load(image)
            .into(binding.tvDetailPhoto)

        binding.namaWisata.text = title
        binding.deskripsiWisata.text = desc
    }
}