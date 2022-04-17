package com.example.healthy.domain.model.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthy.databinding.FoodItemBinding
import com.example.healthy.domain.model.Food

class FoodAdapter: RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    var foodList: List<Food> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodItemBinding.inflate(inflater, parent, false)
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
        val binding: FoodItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}