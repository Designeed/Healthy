package com.example.healthy.presentation.fragments.journal

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import com.example.healthy.R
import com.example.healthy.databinding.FragmentAddJournalBinding
import android.widget.Spinner
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import com.example.healthy.domain.model.Food
import com.example.healthy.presentation.fragments.food.FoodViewModel
import com.example.healthy.presentation.util.adapters.JournalSpinnerAdapter

class AddJournalFragment : Fragment() {

    private lateinit var binding: FragmentAddJournalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_add_journal, container, false)
        setupSpinnerAdapter(view)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupSpinnerAdapter(view: View){

        val spinner = view.findViewById<Spinner>(R.id.foodSpinner)
        val adapter = JournalSpinnerAdapter()

        val viewModel = AddJournalViewModel(FoodRepositoryImpl(AppDataBase.getDatabase(view.context).getFoodsDao()))
        viewModel.foodListLifeData.observe(viewLifecycleOwner) { foodList ->
            adapter.data = foodList
        }

        spinner?.adapter = adapter
    }
}