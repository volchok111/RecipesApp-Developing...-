package com.example.recipes.ui.main

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.recipes.R
import com.example.recipes.api.ApiService
import com.example.recipes.api.ApiServiceImpl
import com.example.recipes.model.RecipeModel
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    private val apiService = ApiServiceImpl()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun startInitialization(context: Context) {
        if (dataList.isNotEmpty()) {
            viewState.onListSetup(dataList)
            return
        }
        context.let {
            if (!apiService.hasPing(context)) {
                viewState.onConnectionAbsence()
            }
        }
        compositeDisposable.add(
            apiService.getRecipe()
                .subscribe({ recipe ->
                    viewState.onListSetup(recipe.results)
                },
                    { viewState.onRequestError(R.string.error)})
        )
    }

    fun dispose(){
        compositeDisposable.dispose()
    }
    companion object {
        private val dataList = ArrayList<RecipeModel>()
    }
}