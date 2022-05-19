package com.example.healthy.presentation.util.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.healthy.databinding.JournalRecyclerViewItemBinding
import com.example.healthy.domain.model.Journal
import com.example.healthy.domain.use_cases.shared.NotificationService
import java.lang.Exception

class JournalRecyclerViewAdapter(
    val onDelete: (title: String, date: String) -> Unit
) : RecyclerView.Adapter<JournalRecyclerViewAdapter.JournalViewHolder>() {
    private lateinit var binding: JournalRecyclerViewItemBinding
    private lateinit var context: Context
    private val viewBindHelper = ViewBinderHelper()

    var data = listOf<Journal>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        binding = JournalRecyclerViewItemBinding.inflate(inflater, parent, false)
        return JournalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        viewBindHelper.setOpenOnlyOne(true)
        viewBindHelper.bind(holder.binding.swipeRevealLayout, data[position].date)
        viewBindHelper.closeLayout(data[position].date)

        val currentJournal = data[position]
        with(holder.binding) {
            rvTitle.text = currentJournal.food.title
            rvCountProtein.text = currentJournal.food.protein.toString()
            rvCountFats.text = currentJournal.food.fats.toString()
            rvCountCarbs.text = currentJournal.food.carbs.toString()
            rvCountCalories.text = currentJournal.food.calories.toString()
        }

        binding.btnDeleteFood.setOnClickListener {
            try {
                //data.remove(currentJournal)
                onDelete(currentJournal.food.title, currentJournal.date)
            } catch (ex: Exception) {
                NotificationService.notify(context, ex.message.toString())
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class JournalViewHolder(
        val binding: JournalRecyclerViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}