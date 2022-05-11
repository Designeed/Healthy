package com.example.healthy.presentation.fragments.food

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.healthy.R
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import kotlinx.coroutines.launch
import java.lang.Exception

class EditFoodFragment: Fragment() {
    private val args: EditFoodFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_edit_food, container, false)

        savedTitle = args.foodTitle
        lifecycleScope.launch {
            val editingFood = FoodRepositoryImpl(AppDataBase.getDatabase(view.context).getFoodsDao()).getFoodByTitle(savedTitle)
            view.findViewById<EditText>(R.id.txtBox_foodTitle).setText(editingFood.title)
            view.findViewById<EditText>(R.id.txtBox_protein).setText(editingFood.protein.toString())
            view.findViewById<EditText>(R.id.txtBox_fat).setText(editingFood.fats.toString())
            view.findViewById<EditText>(R.id.txtBox_сarbs).setText(editingFood.carbs.toString())
            view.findViewById<EditText>(R.id.txtBox_calories).setText(editingFood.calories.toString())
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        var savedTitle: String = ""
    }

}