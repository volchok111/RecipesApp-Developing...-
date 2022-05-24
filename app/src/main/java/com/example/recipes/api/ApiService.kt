package com.example.recipes.api

import android.content.Context
import com.example.recipes.model_recipes.Recipe
import io.reactivex.Observable

interface ApiService {
    fun hasPing(context: Context): Boolean
    fun getRecipe(): Observable<Recipe>
}