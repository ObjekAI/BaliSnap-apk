package com.bangkit.balisnap

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.balisnap.adapter.DestinationAdapter
import com.bangkit.balisnap.adapter.MainAdapter
import com.bangkit.balisnap.databinding.ActivityResultBinding
import com.bangkit.balisnap.response.DestinationsItem
import com.bangkit.balisnap.utils.Result
import com.bangkit.balisnap.viewmodel.ResultViewModel
import com.bangkit.balisnap.viewmodel.ViewModelFactory

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val viewModel: ResultViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var adapter: MainAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prediksi = intent.getStringExtra(MainActivity.PREDIKSI) ?: ""
        val persen = intent.getStringExtra(MainActivity.PERSEN) ?: ""

        // Display the prediction results
        binding.hasil.text = "Location based on the photo: $prediksi"
        Log.d("Prediction", prediksi)

        if (prediksi.isNotEmpty()) {
            viewModel.getSearchDestination(prediksi).observe(this, Observer { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val destinations = result.data.data?.destinations ?: emptyList()

                        if (destinations.isNotEmpty()) {
                            val adapter = DestinationAdapter(destinations)
                            binding.recyclerview.layoutManager = LinearLayoutManager(this)
                            binding.recyclerview.adapter = adapter

                            val destination = destinations[0]
                            val latitude = destination?.latitude?.toDoubleOrNull() ?: 0.0
                            val longitude = destination?.longitude?.toDoubleOrNull() ?: 0.0

                            viewModel.getNearbyDestinations(latitude, longitude, 10).observe(this, Observer { nearbyResult ->
                                when (nearbyResult) {
                                    is Result.Loading -> {
                                        binding.progressBar.visibility = View.VISIBLE
                                    }
                                    is Result.Success -> {
                                        binding.progressBar.visibility = View.GONE
                                        val nearbyDestinations = nearbyResult.data.data?.destinations ?: emptyList()

                                        if (nearbyDestinations.isNotEmpty()) {
                                            val nearbyAdapter = DestinationAdapter(nearbyDestinations)
                                            binding.bawah.layoutManager = LinearLayoutManager(this)
                                            binding.bawah.adapter = nearbyAdapter
                                        } else {
                                            binding.deket.text = "No nearby destinations found"
                                        }
                                    }
                                    is Result.Error -> {
                                        binding.progressBar.visibility = View.GONE
                                        binding.deket.text = "Error: ${nearbyResult.error}"
                                    }
                                }
                            })
                        } else {
                            binding.hasil.text = "No destinations found"
                        }
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            })
        }


        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun onItemClick(destination: DestinationsItem) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(MainAdapter.IMAGE_STORY, "https://storage.googleapis.com/balisnap-storage/${destination.image}")
            putExtra(MainAdapter.TITLE_STORY, destination.name)
            putExtra(MainAdapter.DESC_STORY, destination.description)
        }
        startActivity(intent)
    }
}
