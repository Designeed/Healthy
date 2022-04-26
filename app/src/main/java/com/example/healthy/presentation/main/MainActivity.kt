package com.example.healthy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.healthy.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import com.example.healthy.data.room.food.FoodsDao
import com.example.healthy.data.room.food.FoodsDao_Impl
import com.example.healthy.domain.use_case.FoodService
import com.example.healthy.presentation.util.adapters.FoodAdapter

class MainActivity : AppCompatActivity(){
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationContainer: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationContainer = findViewById(R.id.bottomNavigationContainer)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.menu.getItem(1).isEnabled = false

        appBarConfiguration = AppBarConfiguration(setOf(R.id.fragment_journal, R.id.fragment_food))
        navController = findNavController(R.id.fragmentContainer)
        bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        findViewById<FloatingActionButton>(R.id.fab).also {
            it.setOnClickListener{
                btnChangeFragment()
            }
        }
    }

    //Method for support back button on ActionBar
    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        if (bottomNavigationContainer.alpha == 0.0f)
            bottomNavigationContainer.animate().alpha(1.0f).duration = 150

        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (bottomNavigationContainer.alpha == 0.0f)
            bottomNavigationContainer.animate().alpha(1.0f).duration = 150
        super.onBackPressed()
    }

    private fun btnChangeFragment() {
        if (navController.currentDestination?.id == R.id.fragment_journal)
            navController.navigate(R.id.action_fragment_journal_to_fragment_add_journal, null,
                navOptions {
                    anim {
                        enter = R.animator.nav_default_enter_anim
                        exit = R.anim.nav_default_exit_anim
                        popEnter = R.anim.nav_default_pop_enter_anim
                        popExit = R.anim.nav_default_pop_exit_anim
                    }
                })

        else if (navController.currentDestination?.id == R.id.fragment_food)
            navController.navigate(R.id.action_fragment_food_to_fragment_add_food, null,
                navOptions {
                    anim {
                        enter = R.anim.nav_default_enter_anim
                        exit = R.anim.nav_default_exit_anim
                        popEnter = R.anim.nav_default_pop_enter_anim
                        popExit = R.anim.nav_default_pop_exit_anim
                    }
                })
    }

    fun btnOpenSettings(item: MenuItem) {
        navController.navigate(
            R.id.fragment_settings,
            null,
            navOptions {
                anim {
                    enter = R.animator.nav_default_enter_anim
                    exit = R.anim.nav_default_exit_anim
                    popEnter = R.anim.nav_default_pop_enter_anim
                    popExit = R.anim.nav_default_pop_exit_anim
                }
            })
        bottomNavigationContainer.animate().alpha(0.0f).duration = 150
    }
}