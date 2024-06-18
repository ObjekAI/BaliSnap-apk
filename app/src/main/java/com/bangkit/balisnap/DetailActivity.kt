package com.bangkit.balisnap

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.bangkit.balisnap.activity.SignInActivity
import com.bumptech.glide.Glide
import com.bangkit.balisnap.adapter.MainAdapter
import com.bangkit.balisnap.databinding.ActivityDetailBinding
import com.bangkit.balisnap.like.LikeDestination
import com.bangkit.balisnap.viewmodel.DetailViewModel
import com.bangkit.balisnap.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private var isFavorite = false
    private lateinit var currentDestination: LikeDestination

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

        if (title != null && image != null) {
            currentDestination = LikeDestination(name = title, image = image, description = desc)

            Glide.with(this)
                .load(image)
                .into(binding.tvDetailPhoto)

            binding.namaWisata.text = title
            binding.deskripsiWisata.text = desc

            viewModel.getFavoriteByName(currentDestination.name).observe(this) { favorite ->
                isFavorite = favorite != null
                updateFavoriteIcon()
            }
        } else {
            Toast.makeText(this, "Failed to load destination details", Toast.LENGTH_SHORT).show()
            finish()
        }

        val likeDestination = LikeDestination(0 , title.toString(), image.toString(), desc.toString())
        viewModel.getFavoriteByName(likeDestination.name).observe(this) { destination ->
            if (destination != null) {
                updateFavoriteIcon()
                binding.fabFavorite.setOnClickListener {
                    viewModel.delete(destination)
                    Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
                }
            } else {
                updateFavoriteIcon()
                binding.fabFavorite.setOnClickListener {
                    viewModel.insert(likeDestination)
                    Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
    private fun updateFavoriteIcon() {
        binding.fabFavorite.setImageResource(
            if (isFavorite) R.drawable.like else R.drawable.unlike
        )
    }

    companion object {
        const val IMAGE_STORY = "IMAGE_STORY"
        const val TITLE_STORY = "TITLE_STORY"
        const val DESC_STORY = "DESC_STORY"
    }
}