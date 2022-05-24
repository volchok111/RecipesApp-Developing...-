package com.example.recipes.ui.main

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.recipes.model_recipes.RecipeModel
import com.example.recipes.ui.home.HomeView

@StateStrategyType(SingleStateStrategy::class)
interface MainView: HomeView {
    fun onListSetup(recipes: List<RecipeModel>)
    fun addRecipe(recipe: RecipeModel)
}