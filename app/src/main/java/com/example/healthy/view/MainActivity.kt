package com.example.healthy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healthy.R
import com.example.healthy.view.fragments.FoodFragment
import com.example.healthy.view.fragments.JournalFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.containerView, JournalFragment()).commit()
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener(navListener)
    }

    val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.ic_journal -> {
                currentFragment = JournalFragment()
            }

            R.id.ic_food -> {
              currentFragment = FoodFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.containerView, currentFragment).commit()
        true
    }
}