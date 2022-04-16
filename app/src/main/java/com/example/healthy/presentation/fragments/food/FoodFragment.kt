package com.example.healthy.presentation.fragments.food

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthy.R
import com.example.healthy.databinding.FragmentFoodBinding
import com.example.healthy.domain.model.adapters.FoodAdapter
import com.example.healthy.domain.use_case.FoodListener
import com.example.healthy.domain.use_case.FoodService

class FoodFragment : Fragment(){

    private  lateinit var binding: FragmentFoodBinding
    private lateinit var adapter: FoodAdapter
    private val foodService = FoodService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_food, container, false)
        binding = FragmentFoodBinding.inflate(layoutInflater)

        adapter = FoodAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.adapter = adapter
        foodService.addListener(foodListener)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        foodService.deleteListener(foodListener)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private val foodListener: FoodListener = {
        adapter.foodList = it
    }
}