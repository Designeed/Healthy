package com.example.healthy.presentation.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthy.databinding.FoodRecyclerViewItemBinding
import com.example.healthy.domain.model.Food

class FoodRecyclerViewAdapter: RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder>() {

    var foodList: List<Food> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodRecyclerViewItemBinding.inflate(inflater, parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentFood: Food = foodList[position]
        with(holder.binding){
            rvTitle.text = currentFood.title
            rvCountProtein.text = currentFood.protein.toString()
            rvCountFats.text = currentFood.fats.toString()
            rvCountCarbs.text = currentFood.carbs.toString()
            rvCountCalories.text = currentFood.calories.toString()
        }
    }

    override fun getItemCount(): Int = foodList.size

    class FoodViewHolder(
        val binding: FoodRecyclerViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}