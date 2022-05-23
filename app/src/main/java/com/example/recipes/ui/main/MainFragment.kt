package com.example.recipes.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.recipes.R
import com.example.recipes.adapter.Adapter
import com.example.recipes.databinding.FragmentMainBinding
import com.example.recipes.model.RecipeModel
import com.example.recipes.ui.home.HomeFragment


class MainFragment : HomeFragment(), MainView{

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { Adapter() }

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentMainBinding.inflate(layoutInflater)
        binding.bestRecyclerView.adapter = adapter
        binding.favouriteRecyclerView.adapter = adapter
        binding.recommendedRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { mainPresenter.startInitialization(it) }
    }

    override fun onListSetup(recipes: List<RecipeModel>) {
        adapter.newList(recipes as ArrayList<RecipeModel> /* = java.util.ArrayList<com.example.recipes.model.RecipeModel> */)
    }


    override fun addRecipe(recipe: RecipeModel) {
       adapter.addList(recipe)
    }

    override fun onDestroyView() {
        _binding = null
        mainPresenter.dispose()
        super.onDestroyView()
    }

}