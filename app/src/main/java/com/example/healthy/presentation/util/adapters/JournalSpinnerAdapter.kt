package com.example.healthy.presentation.util.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.example.healthy.databinding.FoodSpinnerItemBinding
import com.example.healthy.domain.model.Food

class JournalSpinnerAdapter: BaseAdapter() {

    var data: List<String> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): String {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parentView: ViewGroup): View {
        val binding = convertView?.tag as FoodSpinnerItemBinding? ?: createBinding(parentView.context)

        val item = getItem(position)

        binding.foodTitle.text = item
        return binding.root
    }

    private fun createBinding(context: Context?): FoodSpinnerItemBinding {
        val binding = FoodSpinnerItemBinding.inflate(LayoutInflater.from(context))
        binding.foodTitle.tag = binding
        return binding
    }
}
