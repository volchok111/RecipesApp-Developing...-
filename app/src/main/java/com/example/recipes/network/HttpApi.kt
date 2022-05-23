package com.example.recipes.network

import com.example.recipes.model.Recipe
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header


interface HttpApi {

    @GET("recipes/list")
    fun getRecipe(@Header("X-RapidAPI-Host")host: String, @Header("X-RapidAPI-Key")key: String): Observable<Recipe>


}