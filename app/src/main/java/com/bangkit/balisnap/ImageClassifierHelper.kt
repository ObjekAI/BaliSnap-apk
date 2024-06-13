package com.bangkit.balisnap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.MediaStore
import android.util.Log
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.CastOp
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

@Suppress("DEPRECATION")
class ImageClassifierHelper(
    var threshold: Float = 0.1f,
    var maxResults: Int = 3,
    val modelName: String = "my_model.tflite",
    val context: Context,
    val classifierListener: ClassifierListener?) {

    interface ClassifierListener {
        fun onError(error: String)
        fun onResults(
            results: List<Classifications>?,
            inferenceTime: Long
        )
    }

    private var imageClassifier: ImageClassifier? = null

    init {
        setupImageClassifier()
    }

    private fun setupImageClassifier() {
        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(threshold)
            .setMaxResults(maxResults)

        val baseOptionBuilder = BaseOptions.builder()
            .setNumThreads(4)

        optionsBuilder.setBaseOptions(baseOptionBuilder.build())

        try {
            imageClassifier = ImageClassifier.createFromFileAndOptions(
                context,
                modelName,
                optionsBuilder.build()
            )
        } catch (e: IllegalStateException) {
            classifierListener?.onError("Error initializing ImageClassifier: ${e.message}")
            Log.e(TAG, "Error initializing ImageClassifier", e)
        }

    }

    fun classifyStaticImage(imageUri: Uri) {
        if (imageClassifier == null) {
            classifierListener?.onError("ImageClassifier is not initialized.")
            return
        }

        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        }.copy(Bitmap.Config.ARGB_8888, true)

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
            .add(CastOp(DataType.FLOAT32))
            .add(NormalizeOp(127.5f, 127.5f))  // Normalization step sesuai dengan metadata
            .build()

        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))

        try {
            var inferenceTime = SystemClock.uptimeMillis()
            val results = imageClassifier?.classify(tensorImage)
            inferenceTime = SystemClock.uptimeMillis() - inferenceTime
            classifierListener?.onResults(results, inferenceTime)
        } catch (e: Exception) {
            classifierListener?.onError("Error during classification: ${e.message}")
            Log.e(TAG, "Error during classification", e)
        }
    }

    companion object {
        private const val TAG = "ImageHelper"
    }
}