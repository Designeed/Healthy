package com.example.healthy.domain.use_cases

import com.google.android.material.floatingactionbutton.FloatingActionButton

class SetImageButtonUserCase(button: FloatingActionButton) {

     init {
        fabButton = button
    }

    companion object{
        private var fabButton: FloatingActionButton? = null

        fun execute(drawable: Int) = fabButton?.setImageResource(drawable)
    }
}