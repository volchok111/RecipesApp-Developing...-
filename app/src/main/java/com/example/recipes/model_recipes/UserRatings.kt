package com.example.recipes.model_recipes

data class UserRatings(
    val count_negative: Int,
    val count_positive: Int,
    val score: Double
)