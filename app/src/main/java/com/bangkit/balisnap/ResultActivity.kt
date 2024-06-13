package com.bangkit.balisnap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import com.bangkit.balisnap.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val prediksi = intent.getStringExtra(MainActivity.PREDIKSI)
        val persen = intent.getStringExtra(MainActivity.PERSEN)

        // Display the prediction results
        binding.hasil.text = "Prediksi: $prediksi"
        Log.d(persen, "awwww")
//        binding.resultScore.text = "Nilai Prediksi: $persen"

    }
}