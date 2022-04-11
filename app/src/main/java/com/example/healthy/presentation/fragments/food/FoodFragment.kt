package com.example.healthy.presentation.fragments.food

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.healthy.R

class FoodFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}