package com.example.recipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.model_recipes.Component
import com.example.recipes.model_recipes.Ingredient

class IngredientAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var ingredientList = arrayListOf<Component>()

    fun newList(list: ArrayList<Component>){
        ingredientList = list
        notifyDataSetChanged()
    }

    fun addList(ingr: Component){
        ingredientList.add(ingr)
    }

    class IngredientViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.name_item_tv)

        fun bind(comp: Component){
            name.text = comp.ingredient.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item,parent,false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is IngredientViewHolder)
            holder.bind(ingredientList[position])

    }

    override fun getItemCount(): Int {
        return ingredientList.count()
    }

}