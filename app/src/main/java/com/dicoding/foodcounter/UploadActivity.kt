//package com.dicoding.foodcounter
//
//import android.app.Activity
//import android.content.Intent
//import android.net.Uri
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import okhttp3.*
//import java.io.File
//import java.io.FileInputStream
//import java.io.FileOutputStream
//
//class UploadActivity : AppCompatActivity() {
//    private var selectedImageUri: Uri? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_upload)
//        R.id.image_view.setOnClickListener {
//            openImageChooser()
//        }
//
//        R.id.button_upload.setOnClickListener {
//            uploadImage()
//        }
//    }
//
//    companion object {
//        const val REQUEST_CODE_PICK_IMAGE = 101
//    }
//
//    private fun openImageChooser() {
//        Intent(Intent.ACTION_PICK).also {
//            it.type = "image/*"
//            val mimeTypes = arrayOf("image/jpeg", "image/png")
//            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
//            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            when (requestCode) {
//                REQUEST_CODE_PICK_IMAGE -> {
//                    selectedImageUri = data?.data
//                    R.id.image_view.setImageURI(selectedImageUri)
//                }
//            }
//        }
//    }
//
//    private fun uploadImage() {
//        if (selectedImageUri == null) {
//            R.id.layout_root.snackbar("Select an Image First")
//            return
//        }
//
//        val parcelFileDescriptor =
//            contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return
//
//        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
//        val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
//        val outputStream = FileOutputStream(file)
//        inputStream.copyTo(outputStream)
//
//        R.id.progress_bar.progress = 0
//        val body = UploadRequestBody(file, "image", this)
//        MyAPI().uploadImage(
//            MultipartBody.Part.createFormData(
//                "image",
//                file.name,
//                body
//            ),
//            RequestBody.create(MediaType.parse("multipart/form-data"), "json")
//        ).enqueue(object : Callback<UploadResponse> {
//            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
//                R.id.layout_root.snackbar(t.message!!)
//                R.id.progress_bar.progress = 0
//            }
//
//            override fun onResponse(
//                call: Call<UploadResponse>,
//                response: Response<UploadResponse>
//            ) {
//                response.body()?.let {
//                    R.id.layout_root.snackbar(it.message)
//                    R.id.progress_bar.progress = 100
//                }
//            }
//        })
//
//    }
//
//}