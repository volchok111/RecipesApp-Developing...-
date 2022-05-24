package com.example.recipes.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipes.R
import com.example.recipes.databinding.FragmentDetailBinding
import com.example.recipes.model_recipes.RecipeModel
import com.example.recipes.ui.home.HomeFragment


class DetailFragment : HomeFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recipe: RecipeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = arguments?.getSerializable("recipe_model") as RecipeModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        binding.fragmentDetailTitleTv.text = recipe.name
        binding.fragmentDetailCookTimeTv.text = recipe.total_time_minutes.toString()
        binding.fragmentDetailReviewTv.text = recipe.description
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}