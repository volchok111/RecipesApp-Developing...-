package com.example.recipes.model

data class ComponentX(
    val extra_comment: String,
    val id: Int,
    val ingredient: IngredientX,
    val measurements: List<MeasurementX>,
    val position: Int,
    val raw_text: String
)