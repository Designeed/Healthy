package com.example.healthy.presentation.fragments.food

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthy.R
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import com.example.healthy.databinding.FragmentFoodBinding
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.use_cases.DeleteFoodUseCase
import com.example.healthy.domain.use_cases.EditFoodUseCase
import com.example.healthy.domain.use_cases.SetImageButtonUserCase
import com.example.healthy.presentation.util.adapters.FoodRecyclerViewAdapter
import kotlinx.coroutines.runBlocking

class FoodFragment : Fragment(){
    private lateinit var binding: FragmentFoodBinding
    private lateinit var recyclerViewAdapter: FoodRecyclerViewAdapter
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_food, container, false)
        binding = FragmentFoodBinding.inflate(layoutInflater)

        SetImageButtonUserCase.execute(R.drawable.ic_add_note)
        setUpRecyclerViewAdapter(view)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpRecyclerViewAdapter(view: View){
        recyclerViewAdapter = FoodRecyclerViewAdapter(
            onEdit = { title  ->
                //val action = FoodFragmentDirections.actionFragmentFoodToEditFoodFragment(title)
                EditFoodUseCase.selectedFoodTitle = title
                findNavController().navigate(R.id.action_fragment_food_to_editFoodFragment)
            },
            onDelete = { title ->
                runBlocking {
                    DeleteFoodUseCase().execute(title, FoodRepositoryImpl(AppDataBase.getDatabase(view.context).getFoodsDao()))
                }
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.adapter = recyclerViewAdapter

        foodViewModel = FoodViewModel((FoodRepositoryImpl(AppDataBase.getDatabase(view.context).getFoodsDao())))
        foodViewModel.foodListLifeData.observe(viewLifecycleOwner) { foodList ->
            recyclerViewAdapter.data = foodList as ArrayList<Food>
        }
    }
}