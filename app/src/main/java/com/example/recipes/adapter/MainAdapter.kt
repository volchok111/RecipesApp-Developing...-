package com.example.recipes.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.model_recipes.RecipeModel
import com.squareup.picasso.Picasso

class MainAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var recipeList = arrayListOf<RecipeModel>()


    fun newList(list: ArrayList<RecipeModel>){
        recipeList = list
        notifyDataSetChanged()
    }

    fun addList(recipe: RecipeModel){
        recipeList.add(recipe)
        notifyDataSetChanged()
    }

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val image = view.findViewById<ImageView>(R.id.item_iv)
        private val title = view.findViewById<TextView>(R.id.item_title_tv)

        fun bind(recipe: RecipeModel){
            Picasso.get().load(recipe.thumbnail_url).into(image)
            title.text = recipe.name
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("recipe_model",recipe)
                itemView.findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,bundle
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecipeViewHolder)
            holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.count()
    }

}