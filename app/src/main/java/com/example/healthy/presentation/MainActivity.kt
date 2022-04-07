package com.example.healthy.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.healthy.R
import com.example.healthy.presentation.food.FoodFragment
import com.example.healthy.presentation.journal.JournalFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.actionBar_journal)
        supportFragmentManager.beginTransaction().replace(R.id.containerView, JournalFragment()).commit()
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener(navListener)
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.ic_journal -> {
                currentFragment = JournalFragment()
                setTitle(R.string.actionBar_journal)
            }

            R.id.ic_food -> {
              currentFragment = FoodFragment()
                setTitle(R.string.actionBar_food)
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.containerView, currentFragment).commit()
        true
    }
}