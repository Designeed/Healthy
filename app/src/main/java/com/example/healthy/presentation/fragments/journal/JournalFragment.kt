package com.example.healthy.presentation.fragments.journal

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthy.R
import com.example.healthy.databinding.FragmentJournalBinding
import com.example.healthy.domain.use_cases.shared.SetImageButton
import com.example.healthy.presentation.util.adapters.JournalRecyclerViewAdapter

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