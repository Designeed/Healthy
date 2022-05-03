package com.example.healthy.presentation.fragments.journal

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.healthy.R
import com.example.healthy.databinding.FragmentAddJournalBinding
import com.example.healthy.domain.model.Food
import java.text.FieldPosition

class AddJournalFragment : Fragment(){
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var binding: FragmentAddJournalBinding

    val data = arrayOf(
        Food("1asdfasdf", 2 ,2, 2, 2).title,
        Food("1sadfsadf", 223 ,2232, 232, 232).title,
        Food("1asdf", 12 ,32, 32, 233).title,
        Food("1asdf", 12 ,24, 52, 12).title,
        Food("1asdf", 232 ,12, 322, 23).title,
        Food("1fsdf", 212 ,1232, 32, 23).title
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_add_journal, container, false)
        binding = FragmentAddJournalBinding.inflate(layoutInflater)
        adapter = ArrayAdapter(view.context, R.layout.food_spinner_item, data)
        binding.foodSpinner.adapter = adapter
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}