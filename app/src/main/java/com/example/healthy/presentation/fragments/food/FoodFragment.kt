package com.example.healthy.presentation.fragments.food

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthy.R
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import com.example.healthy.databinding.FragmentFoodBinding
import com.example.healthy.domain.use_cases.DeleteFoodUseCase
import com.example.healthy.domain.use_cases.EditFoodUseCase
import com.example.healthy.domain.use_cases.SetImageButton
import com.example.healthy.presentation.util.adapters.FoodRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodFragment : Fragment(){
    private lateinit var binding: FragmentFoodBinding
    private lateinit var recyclerViewAdapter: FoodRecyclerViewAdapter
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentFoodBinding.inflate(layoutInflater)

        SetImageButton.execute(R.drawable.ic_add_note)
        setUpRecyclerViewAdapter()
        initializeLifeData()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpRecyclerViewAdapter(){
        recyclerViewAdapter = FoodRecyclerViewAdapter(
            onEdit = { title  ->
                EditFoodUseCase.selectedFoodTitle = title
                findNavController().navigate(R.id.action_fragment_food_to_editFoodFragment)
            },
            onDelete = { title ->
                lifecycleScope.launch(Dispatchers.IO) {
                    DeleteFoodUseCase().execute(title, FoodRepositoryImpl(AppDataBase.getDatabase(requireContext()).getFoodsDao()))
                }
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    private fun initializeLifeData() {
        foodViewModel = FoodViewModel((FoodRepositoryImpl(AppDataBase.getDatabase(requireContext()).getFoodsDao())))
        foodViewModel.foodListLifeData.observe(viewLifecycleOwner) { foodList ->
            recyclerViewAdapter.data = foodList
        }
    }
}