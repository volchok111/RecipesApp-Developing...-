package com.example.recipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.recipes.R
import com.example.recipes.model.Dishes
import com.example.recipes.model.RecipeModel

class SliderAdapter(val viewPager2: ViewPager2,private var dataList: List<Dishes>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    fun newList(list: ArrayList<Dishes>){
        dataList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SliderViewPager(LayoutInflater.from(parent.context)
            .inflate(R.layout.slide_item_container,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SliderViewPager)
            holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
       return dataList.count()
    }

    private class SliderViewPager(view: View): RecyclerView.ViewHolder(view){

        fun bind(dish: Dishes){
            itemView.findViewById<ImageView>(R.id.image_slide)
                .setImageResource(dish.dishImage)
        }
    }
}