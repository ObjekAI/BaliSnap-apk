package com.bangkit.balisnap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import com.bangkit.balisnap.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val image = intent.extras?.getString(MainActivity.IMG_CLASS).toString()
        val prediksi = intent.extras?.getString(MainActivity.PREDIKSI).toString()
//        val persen = intent.extras?.getString(MainActivity.PERSEN).toString()

//        binding.resultImage.setImageURI(image.toUri())
        binding.hasil.text = "Prediksi: ${prediksi}"
//        binding.resultScore.text = "Nilai Prediksi: ${persen}"

    }
}