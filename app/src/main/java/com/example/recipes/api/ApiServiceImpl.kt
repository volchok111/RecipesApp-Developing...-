package com.example.recipes.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.recipes.model_recipes.Recipe
import com.example.recipes.network.HttpRetrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiServiceImpl : ApiService{
    private val httpRetrofit: HttpRetrofit = HttpRetrofit

    override fun hasPing(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = manager.activeNetwork ?: return false
        val actNw = manager.getNetworkCapabilities(nw)
        if (actNw != null){
            return actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR
            )
        }
        return true
    }

    override fun getRecipe(): Observable<Recipe> {
        return httpRetrofit.getHttpApi().getRecipe("tasty.p.rapidapi.com","2f95872471mshda35e3be08b37f5p18f5e5jsn69680e02a3f7")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}