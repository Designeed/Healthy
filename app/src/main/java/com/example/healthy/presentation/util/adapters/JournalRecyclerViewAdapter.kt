package com.example.healthy.presentation.util.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.healthy.databinding.RecyclerViewItemBinding
import com.example.healthy.domain.model.Journal


class JournalRecyclerViewAdapter(
//    val onEdit: (String) -> Unit,
//    val onDelete: (String) -> Unit
) : RecyclerView.Adapter<JournalRecyclerViewAdapter.JournalViewHolder>() {
    private lateinit var binding: RecyclerViewItemBinding
    private lateinit var context: Context
    private val viewBindHelper = ViewBinderHelper()

    var data = emptyList<Journal>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerViewItemBinding.inflate(inflater, parent, false)
        return JournalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        viewBindHelper.setOpenOnlyOne(true)
        viewBindHelper.bind(holder.binding.swipeRevealLayout, data[position].food.title)
        viewBindHelper.closeLayout(data[position].food.title)

        val currentJournal = data[position]
        with(holder.binding) {
            rvTitle.text = currentJournal.food.title
            rvCountProtein.text = currentJournal.food.protein.toString()
            rvCountFats.text = currentJournal.food.fats.toString()
            rvCountCarbs.text = currentJournal.food.carbs.toString()
            rvCountCalories.text = currentJournal.food.calories.toString()
        }
    }

    override fun getItemCount(): Int = data.size

    class JournalViewHolder(
        val binding: RecyclerViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}