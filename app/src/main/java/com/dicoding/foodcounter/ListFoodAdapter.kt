package com.dicoding.foodcounter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListFoodAdapter(val listFood: ArrayList<Food>) : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_food, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val food = listFood[position]
        Glide.with(holder.itemView.context)
            .load(food.photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)
        holder.tvName.text = food.name
        holder.tvDetail.text = food.detail

        val holderContext = holder.itemView.context
        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(listFood[holder.adapterPosition])
            val foodDetailIntent = Intent(holderContext, DetailFood::class.java)
            foodDetailIntent.putExtra(DetailFood.EXTRA_NAME, food.name)
            foodDetailIntent.putExtra(DetailFood.EXTRA_DETAIL, food.detail)
            foodDetailIntent.putExtra(DetailFood.EXTRA_PHOTO, food.photo)
            holderContext.startActivity(foodDetailIntent)
        }
    }

    override fun getItemCount(): Int {
        return listFood.size
    }
}
