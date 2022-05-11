package com.example.healthy.presentation.fragments.food

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.healthy.R
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import kotlinx.coroutines.runBlocking
import com.example.healthy.domain.model.Food as Food

class AddFoodFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}