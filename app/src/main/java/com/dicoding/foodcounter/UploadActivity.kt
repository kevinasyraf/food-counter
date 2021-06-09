package com.dicoding.foodcounter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ProgressBar
import android.widget.Button
import android.widget.LinearLayout
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class UploadActivity : AppCompatActivity(), View.OnClickListener {
    private var selectedImageUri: Uri? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var button: Button
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.image_view->{
                openImageChooser()
            }

            R.id.button_upload->{
                uploadImage()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    imageView = findViewById(R.id.image_view)
                    imageView.setImageURI(selectedImageUri)
                }
            }
        }
    }

    private fun uploadImage() {
        if (selectedImageUri == null) {
            linearLayout = findViewById(R.id.layout_root)
            linearLayout.snackbar("Select an Image First")
            return
        }

        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        progressBar = findViewById(R.id.progress_bar)
        progressBar.progress = 0
        val body = UploadRequestBody(file, "image", this)
        MyAPI().uploadImage(
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                body
            ),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json")
        )
//            .enqueue(object : Callback<UploadResponse> {
//            fun onFailure(call: Call<UploadResponse>, t: Throwable) {
//                linearLayout = findViewById(R.id.layout_root)
//                linearLayout.snackbar(t.message!!)
//                progressBar = findViewById(R.id.progress_bar)
//                progressBar.progress = 0
//            }
//
//            fun onResponse(
//                call: Call<UploadResponse>,
//                response: Response<UploadResponse>
//            ) {
//                response.body()?.let {
//                    linearLayout = findViewById(R.id.layout_root)
//                    linearLayout.snackbar(it.message)
//                    progressBar = findViewById(R.id.progress_bar)
//                    progressBar.progress = 100
//                }
//            }
//        })

    }

}