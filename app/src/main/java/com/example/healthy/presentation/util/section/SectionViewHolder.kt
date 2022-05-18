package com.example.healthy.presentation.util.section

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView
import com.example.healthy.R

class SectionViewHolder(
    context: Context
) : FrameLayout(context) {
    private lateinit var sectionTitle: TextView

    init {
        inflate(context, R.layout.view_holder_section, this)
        getView()
    }

    private fun getView() {
        sectionTitle = findViewById(R.id.sectionTitle)
    }

    fun setDate(dateString: String) {
        sectionTitle.text = dateString
    }
}