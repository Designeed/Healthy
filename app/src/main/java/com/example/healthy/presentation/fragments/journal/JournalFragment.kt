package com.example.healthy.presentation.fragments.journal

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthy.R
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import com.example.healthy.databinding.FragmentJournalBinding
import com.example.healthy.domain.use_cases.DeleteFoodUseCase
import com.example.healthy.domain.use_cases.EditFoodUseCase
import com.example.healthy.domain.use_cases.SetImageButton
import com.example.healthy.presentation.fragments.food.FoodViewModel
import com.example.healthy.presentation.util.adapters.FoodRecyclerViewAdapter
import com.example.healthy.presentation.util.adapters.JournalRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JournalFragment : Fragment() {
    private lateinit var binding : FragmentJournalBinding
    private lateinit var recyclerViewAdapter: JournalRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentJournalBinding.inflate(layoutInflater)

        setUpRecyclerView()
        SetImageButton.execute(R.drawable.ic_add_note)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpRecyclerView(){

//        recyclerViewAdapter = JournalRecyclerViewAdapter(
//            onDelete = {
//                return@JournalRecyclerViewAdapter
//            }
//        )
        binding.journalRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.journalRecycleView.adapter = JournalRecyclerViewAdapter()
    }
}