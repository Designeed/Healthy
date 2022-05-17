package com.example.healthy.domain.use_cases.shared

import com.google.android.material.floatingactionbutton.FloatingActionButton

class SetImageButton(button: FloatingActionButton) {
     init {
        fabButton = button
    }

    companion object{
        private var fabButton: FloatingActionButton? = null

        fun execute(drawable: Int) = fabButton?.setImageResource(drawable)
    }
}