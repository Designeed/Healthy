package com.example.healthy.presentation.fragments.journal

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.healthy.R
import com.example.healthy.domain.use_cases.SetImageButton

class JournalFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_journal, container, false)

        SetImageButton.execute(R.drawable.ic_add_note)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}