package com.bangkit.balisnap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.balisnap.adapter.MainAdapter
import com.bangkit.balisnap.databinding.ActivityMainBinding
import com.bangkit.balisnap.viewmodel.MainViewModel
import com.bangkit.balisnap.utils.Result
import com.bangkit.balisnap.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getDestination(-8.724451, 115.176827, 1000).observe(this) {
            when (it) {
                is Result.Loading -> {

                }

                is Result.Success -> {

                    val adapter = MainAdapter()
                    adapter.submitList(it.data.data!!.destinations)
                    binding.recyclerview.layoutManager = LinearLayoutManager(this)
                    binding.recyclerview.adapter = adapter

//                    Log.e("bisa", "${it.data.data!!.destinations}")
                }

                is Result.Error -> {
//                    Log.e("gabisa", "${it.error}")
                }

            }
        }


//        binding.recyclerview.apply {
//            setHasFixedSize(true)
//            this.adapter = destinationAdapter
//        }

        with(binding) {


            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()

                    getSearch(searchBar.text.toString())

                    false
                }


        }

    }

//    fun onItemClick(wisata: DestinationsItem) {
//        val intent = Intent(this, DetailActivity::class.java).apply {
//            putExtra(MainAdapter.IMAGE_STORY, wisata.image)
//            putExtra(MainAdapter.TITLE_STORY, wisata.name)
//            putExtra(MainAdapter.DESC_STORY, wisata.description)
//        }
//        startActivity(intent)
//    }

    private fun getSearch(name: String) {
        viewModel.getSearchDestination(name).observe(this) {
            when (it) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    val adapter = MainAdapter()
                    adapter.submitList(it.data.data!!.destinations)
                    binding.recyclerview.layoutManager = LinearLayoutManager(this)
                    binding.recyclerview.adapter = adapter
                    adapter.notifyDataSetChanged()

                    Log.e("bisa", "${it.data.data!!.destinations}")
                }

                is Result.Error -> {
                    Log.e("gabisa", "${it.error}")
                }

            }
        }
    }
}
