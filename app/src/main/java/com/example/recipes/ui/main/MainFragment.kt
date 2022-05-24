package com.example.recipes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.recipes.R
import com.example.recipes.adapter.MainAdapter
import com.example.recipes.adapter.SliderAdapter
import com.example.recipes.databinding.FragmentMainBinding
import com.example.recipes.model_recipes.Dishes
import com.example.recipes.model_recipes.RecipeModel
import com.example.recipes.ui.home.HomeFragment
import kotlin.math.abs



class MainFragment : HomeFragment(), MainView{

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { MainAdapter() }

    private val runner = Runnable {
        if (binding.viewPager2.currentItem >= 3)
            binding.viewPager2.currentItem = 0
        else
            binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
    }

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentMainBinding.inflate(layoutInflater)

        val slideList = arrayListOf(
            Dishes(dishName = "Borsch", dishImage = R.drawable.borsch),
            Dishes(dishName = "Sushi", dishImage = R.drawable.sushi),
            Dishes(dishName = "Pancakes", dishImage = R.drawable.pancakes),
            Dishes(dishName = "Rise", dishImage = R.drawable.plov)
        )

        binding.viewPager2.apply {
            adapter = SliderAdapter(binding.viewPager2,slideList)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer{page,position ->
                run{
                    val r: Float = abs(position)
                    page.scaleY = 0.85f + r * 0.15f
                }
            }
            setPageTransformer(compositePageTransformer)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(runner)
                    handler.postDelayed(runner,6000)
                }
            })
        }
        binding.dotsIndicator.setViewPager2(binding.viewPager2)

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