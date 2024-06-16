package com.bangkit.balisnap

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.bangkit.balisnap.activity.SignInActivity
import com.bumptech.glide.Glide
import com.bangkit.balisnap.adapter.MainAdapter
import com.bangkit.balisnap.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupView()

        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupView() {
        val image = intent.getStringExtra(MainAdapter.IMAGE_STORY)
        val title = intent.getStringExtra(MainAdapter.TITLE_STORY)
        val desc = intent.getStringExtra(MainAdapter.DESC_STORY)

        Glide.with(this)
            .load(image)
            .into(binding.tvDetailPhoto)

        binding.namaWisata.text = title
        binding.deskripsiWisata.text = desc
    }

    companion object {
        const val IMAGE_STORY = "IMAGE_STORY"
        const val TITLE_STORY = "TITLE_STORY"
        const val DESC_STORY = "DESC_STORY"
    }
}
