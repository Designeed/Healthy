package com.example.healthy.presentation.fragments.food.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.healthy.R
import com.example.healthy.domain.use_cases.SetImageButton

class AddFoodFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        SetImageButton.execute(R.drawable.ic_add_complete)
        return inflater.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}