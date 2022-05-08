package com.example.healthy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
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
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.use_cases.AddFoodUseCase
import com.example.healthy.domain.use_cases.NotificationUseCase
import com.example.healthy.domain.use_cases.ValidateOnBlankUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import com.example.healthy.data.room.AppDataBase as AppDataBase

class MainActivity : AppCompatActivity(){

    private lateinit var toastMessage: Toast
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationContainer: CoordinatorLayout
    private lateinit var dbRepository: FoodRepositoryImpl

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

        dbRepository = FoodRepositoryImpl(AppDataBase.getDatabase(this.applicationContext).getFoodsDao())
        findViewById<FloatingActionButton>(R.id.fab).also {
            it.setOnClickListener{
                floatActionButtonClick()
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

    private fun floatActionButtonClick() {
        when (navController.currentDestination?.id) {
            R.id.fragment_journal -> navController.navigate(R.id.action_fragment_journal_to_fragment_add_journal, null,
                navOptions {
                    anim {
                        enter = R.animator.nav_default_enter_anim
                        exit = R.anim.nav_default_exit_anim
                        popEnter = R.anim.nav_default_pop_enter_anim
                        popExit = R.anim.nav_default_pop_exit_anim
                    }
                })

            R.id.fragment_food ->
                navController.navigate(R.id.action_fragment_food_to_fragment_add_food, null,
                navOptions {
                    anim {
                        enter = R.anim.nav_default_enter_anim
                        exit = R.anim.nav_default_exit_anim
                        popEnter = R.anim.nav_default_pop_enter_anim
                        popExit = R.anim.nav_default_pop_exit_anim
                    }
                })

            R.id.fragment_add_food ->
            {
                val title = findViewById<EditText>(R.id.txtBox_foodName).text.toString()
                val protein = findViewById<EditText>(R.id.txtBox_protein).text.toString()
                val fats = findViewById<EditText>(R.id.txtBox_fat).text.toString()
                val carbs = findViewById<EditText>(R.id.txtBox_сarbs).text.toString()
                val calories = findViewById<EditText>(R.id.txtBox_calories).text.toString()

                if (!ValidateOnBlankUseCase().execute(title, protein, fats, carbs, calories)){
                    return NotificationUseCase.execute(applicationContext, "Заполните все поля")
                }

                try {
                    val addedFood = Food(title, protein.toInt(), fats.toInt(), carbs.toInt(), calories.toInt())
                    runBlocking(Dispatchers.Default) {
                        AddFoodUseCase().execute(addedFood, dbRepository)
                    }
                    NotificationUseCase.execute(applicationContext, "Блюдо успешно добавлено")
                } catch (e: Exception){
                    NotificationUseCase.execute(applicationContext, "Данное блюдо уже существует")
                }
            }
        }
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