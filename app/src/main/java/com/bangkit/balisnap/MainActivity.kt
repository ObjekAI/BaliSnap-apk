package com.bangkit.balisnap

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.balisnap.ScanActivity.Companion.CAMERAX_RESULT
import com.bangkit.balisnap.adapter.MainAdapter
import com.bangkit.balisnap.databinding.ActivityMainBinding
import com.bangkit.balisnap.response.DestinationsItem
import com.bangkit.balisnap.viewmodel.MainViewModel
import com.bangkit.balisnap.utils.Result
import com.bangkit.balisnap.utils.rotateBitmap
import com.bangkit.balisnap.viewmodel.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.io.File
import java.util.Locale

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            getLocation()
        }

        binding.scanButton.setOnClickListener{startCameraX()}

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            } else {
                binding.namalokasi.text = getString(R.string.location_permission_denied)
                Toast.makeText(this, getString(R.string.location_need_permission), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            viewModel.getLastKnownLocation { location: Location? ->
                Log.e("lokasi", "${location}")
                location?.let {
                    getAddressFromLocation(it.latitude, it.longitude)
                } ?: run {
                    Toast.makeText(this@MainActivity, getString(R.string.location_not_found), Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun getAddressFromLocation(latitude: Double, longitude: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                if (!addresses.isNullOrEmpty()) {
                    val address = addresses[0]
                    val locationName = address.getAddressLine(0)
                    withContext(Dispatchers.Main) {
                        binding.namalokasi.text = locationName
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.namalokasi.text = getString(R.string.location_error_name, e.message)
                    Toast.makeText(this@MainActivity, getString(R.string.location_error_name, e.message), Toast.LENGTH_LONG).show()
                }
            }
        }
    }



    private fun startCameraX() {
        val intent = Intent(this, ScanActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERAX_RESULT) {
            it.data?.getStringExtra(ScanActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
                ?.let { it1 -> analyzeImage(it1) }
        }
        if (it.resultCode == CAMERAX_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File

            val uritoFile = Uri.fromFile(myFile)
            analyzeImage(uritoFile)

        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                showToast("Permission request granted")
            } else {
                showToast("Permission request denied")
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private fun analyzeImage(dataUri: Uri) {
        val imageClassifierHelper = ImageClassifierHelper(
            context = this,
            classifierListener = object : ImageClassifierHelper.ClassifierListener {
                override fun onError(error: String) {
                    Log.d(ContentValues.TAG, "ShowImage: $error")
                    Toast.makeText(this@MainActivity, "Error: $error", Toast.LENGTH_SHORT).show()
                }

                override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
                    results?.let {
                        val result = it[0]
                        val prediksi = result.categories[0].label
                        val persen = result.categories[0].score

                        fun Float.formatToString(): String {
                            return String.format("%.2f%%", this * 100)
                        }

                        val prediksi_img = prediksi
                        val persen_img = persen.formatToString()

                        // Start ResultActivity with prediction results
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        intent.putExtra(PREDIKSI, prediksi_img)
                        intent.putExtra(PERSEN, persen_img)
                        startActivity(intent)
                    }
                }
            }
        )
        imageClassifierHelper.classifyStaticImage(dataUri)
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
        const val IMG_CLASS = "IMAGE_CLASS"
        const val PREDIKSI = "LABEL"
        const val PERSEN = "SCORE"
    }


}
