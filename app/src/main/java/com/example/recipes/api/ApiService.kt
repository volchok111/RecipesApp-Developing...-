package com.example.recipes.api

import android.content.Context
import com.example.recipes.model.Recipe
import io.reactivex.Observable

import java.util.*

interface ApiService {
    fun hasPing(context: Context): Boolean
    fun getRecipe(): Observable<Recipe>
}