package com.example.healthy.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healthy.R
import com.example.healthy.R.id.bottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(bottomNavigationView).menu.getItem(1).isEnabled = false

        val bottomNavigationView: BottomNavigationView = findViewById(bottomNavigationView)
        val navController = findNavController(R.id.containerView)

        bottomNavigationView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.fragment_journal ,R.id.fragment_food))

        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
