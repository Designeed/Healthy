package com.example.healthy.presentation.fragments.journal

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthy.R
import com.example.healthy.data.repository.JournalRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import com.example.healthy.databinding.FragmentJournalBinding
import com.example.healthy.domain.model.Journal
import com.example.healthy.domain.use_cases.journal.DeleteJournalNoteUseCase
import com.example.healthy.domain.use_cases.journal.GetJournalNoteIdUseCase
import com.example.healthy.domain.use_cases.shared.NotificationService
import com.example.healthy.domain.use_cases.shared.SetImageButton
import com.example.healthy.presentation.util.adapters.JournalRecyclerViewAdapter
import com.example.healthy.presentation.util.section.ItemSectionDecoration
import kotlinx.coroutines.launch
import java.lang.Exception

class JournalFragment : Fragment() {
    private lateinit var binding: FragmentJournalBinding
    private lateinit var viewModel: JournalViewModel
    private lateinit var recyclerViewAdapter: JournalRecyclerViewAdapter
    private lateinit var itemSectionDecoration: ItemSectionDecoration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentJournalBinding.inflate(layoutInflater)

        setUpRecyclerDecoration()
        setUpRecyclerView()
        initializeLifeData()
        SetImageButton.execute(R.drawable.ic_add_note)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpRecyclerView() {
        val journalDao = JournalRepositoryImpl(AppDataBase.getDatabase(requireContext()).getJournalDao())
        recyclerViewAdapter = JournalRecyclerViewAdapter(
            onDelete = {
                title, date ->
                lifecycleScope.launch {
                    NotificationService.notifyWithContext(requireContext(), "$title \n $date")
                    try {
                        val journalId = GetJournalNoteIdUseCase().execute(title, date, journalDao)
                        DeleteJournalNoteUseCase().execute(journalId, journalDao)
                    } catch (ex: Exception) {
                    }
                }
            }
        )
        binding.journalRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.journalRecycleView.adapter = recyclerViewAdapter
    }

    private fun setUpRecyclerDecoration() {
        itemSectionDecoration = ItemSectionDecoration(requireContext()) {
            recyclerViewAdapter.data
        }
        binding.journalRecycleView.addItemDecoration(itemSectionDecoration)
    }

    private fun initializeLifeData() {
        viewModel = JournalViewModel(JournalRepositoryImpl(AppDataBase.getDatabase(requireContext()).getJournalDao()))
        viewModel.foodListLifeData.observe(viewLifecycleOwner) { list ->
            recyclerViewAdapter.data = list
        }
    }

}