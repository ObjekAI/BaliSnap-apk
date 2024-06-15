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


class LikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)

  }
}