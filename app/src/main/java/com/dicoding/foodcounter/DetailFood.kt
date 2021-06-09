package com.dicoding.foodcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailFood : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME ="extra_name"
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_PHOTO = "extra_photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)

        setActionBarTitle("Detail Food")

        val foodName: TextView = findViewById(R.id.tv_item_name)
        val foodDetail: TextView = findViewById(R.id.tv_item_detail)
        val foodPhoto: ImageView = findViewById(R.id.img_item_photo)

        val name = intent.getStringExtra(EXTRA_NAME)
        val detail = intent.getStringExtra(EXTRA_DETAIL)
        val photo = intent.getIntExtra(EXTRA_PHOTO, 0)

        foodName.text = name
        foodDetail.text = detail
        Glide.with(this)
            .load(photo)
            .apply(RequestOptions())
            .into(foodPhoto)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}