package com.dicoding.foodcounter

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvFood: RecyclerView
    private var list: ArrayList<Food> = arrayListOf()
    private var title: String = "Food Counter"
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvFood = findViewById(R.id.rv_food)
        rvFood.setHasFixedSize(true)

        list.addAll(FoodData.listData)
        showRecyclerList()
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showRecyclerList() {
        rvFood.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list)
        rvFood.adapter = listFoodAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Food Counter"
                showRecyclerList()
            }

            R.id.action_upload -> {
                showUpload()
            }
        }
        setActionBarTitle(title)
    }

    private fun showUpload() {
        val uploadIntent = Intent(this@MainActivity, UploadActivity::class.java)
        startActivity(uploadIntent)
    }

    private fun showSelectedFood(food: Food) {
        val foodDetailIntent = Intent(this@MainActivity, DetailFood::class.java)
        foodDetailIntent.putExtra(DetailFood.EXTRA_NAME, food.name)
        foodDetailIntent.putExtra(DetailFood.EXTRA_DETAIL, food.detail)
        foodDetailIntent.putExtra(DetailFood.EXTRA_PHOTO, food.photo)
        startActivity(foodDetailIntent)
    }
}