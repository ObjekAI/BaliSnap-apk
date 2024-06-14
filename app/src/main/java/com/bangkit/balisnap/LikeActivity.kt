package com.bangkit.balisnap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.balisnap.databinding.ActivityDetailBinding
import com.bangkit.balisnap.databinding.ActivityLikeBinding

class LikeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLikeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}