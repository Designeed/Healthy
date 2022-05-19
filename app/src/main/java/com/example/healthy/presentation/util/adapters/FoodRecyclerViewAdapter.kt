package com.example.healthy.presentation.util.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.healthy.databinding.FoodRecyclerViewItemBinding
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.use_cases.shared.NotificationService
import java.lang.Exception

class FoodRecyclerViewAdapter(
    val onEdit: (String) -> Unit,
    val onDelete: (String) -> Unit
) : RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder>() {
    private lateinit var binding: FoodRecyclerViewItemBinding
    private lateinit var context: Context
    private val viewBindHelper = ViewBinderHelper()

    var data = listOf<Food>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        binding = FoodRecyclerViewItemBinding.inflate(inflater, parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        viewBindHelper.setOpenOnlyOne(true)
        viewBindHelper.bind(holder.binding.swipeRevealLayout, data[position].title)
        viewBindHelper.closeLayout(data[position].title)

        val currentFood: Food = data[position]
        with(holder.binding) {
            rvTitle.text = currentFood.title
            rvCountProtein.text = currentFood.protein.toString()
            rvCountFats.text = currentFood.fats.toString()
            rvCountCarbs.text = currentFood.carbs.toString()
            rvCountCalories.text = currentFood.calories.toString()
        }

        binding.btnEditFood.setOnClickListener {
            onEdit(currentFood.title)
        }

        binding.btnDeleteFood.setOnClickListener {
            onDelete(currentFood.title)
        }
    }

    override fun getItemCount(): Int = data.size

    class FoodViewHolder(
        val binding: FoodRecyclerViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}