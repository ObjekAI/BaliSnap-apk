package com.bangkit.balisnap

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.balisnap.adapter.FoodAdapter
import com.bangkit.balisnap.adapter.MainAdapter
import com.bangkit.balisnap.databinding.ActivityFoodBinding
import com.bangkit.balisnap.databinding.ActivityMainBinding
import com.bangkit.balisnap.utils.Result
import com.bangkit.balisnap.viewmodel.FoodViewModel
import com.bangkit.balisnap.viewmodel.MainViewModel
import com.bangkit.balisnap.viewmodel.ViewModelFactory

class FoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodBinding
    private lateinit var adapter: FoodAdapter
    private val viewModel by viewModels<FoodViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FoodAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        viewModel.getFoods().observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    adapter.submitList(result.data.data?.foods)
                    Log.e("bisa semoga bismillah", "Success: BERHASIL")
//                    Toast.makeText(this, "Success: ${result.data.data?.foods}", Toast.LENGTH_SHORT).show()
                }
                is Result.Error -> {
                    Log.e("errorrrrr", "Error: ${result.error}")
                    Toast.makeText(this, "Error: ${result.error}", Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    Log.e("bisa semoga bismillah", "LOADING")
                    // Tangani kasus loading di sini jika diperlukan
                }
                else -> {
                    // Tangani kasus lain yang mungkin terjadi
                    Log.e("gatau kenapa", "Unexpected result: $result")
                    Toast.makeText(this, "Unexpected result", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}