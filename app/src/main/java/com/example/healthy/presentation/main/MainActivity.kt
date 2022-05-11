package com.example.healthy.presentation.main

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.lifecycleScope
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
import com.example.healthy.domain.use_cases.EditFoodUseCase
import com.example.healthy.domain.use_cases.NotificationService
import com.example.healthy.domain.use_cases.ValidateOnBlankUseCase
import com.example.healthy.presentation.fragments.food.EditFoodFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.healthy.data.room.AppDataBase as AppDataBase

class MainActivity : AppCompatActivity(){
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationContainer: CoordinatorLayout
    private lateinit var dbRepository: FoodRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationContainer = findViewById(R.id.bottomNavigationContainer)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            floatActionButtonClick()
        }

        dbRepository = FoodRepositoryImpl(AppDataBase.getDatabase(this.applicationContext).getFoodsDao())
        setUpBottomNavigationView()
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
            R.id.fragment_journal ->
                navController.navigate(R.id.action_fragment_journal_to_fragment_add_journal)

            R.id.fragment_food ->
                navController.navigate(R.id.action_fragment_food_to_fragment_add_food)

                    R.id.fragment_add_food -> {
                if (!ValidateOnBlankUseCase().execute(getListStringFromEditText())) {
                    return NotificationService.notify(applicationContext, "Заполните все поля")
                }

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        AddFoodUseCase().execute(
                            getFoodFromEditText(),
                            dbRepository)

                        clearEditText()

                        NotificationService.notifyWithContext(applicationContext, "Блюдо успешно добавлено")
                    } catch (e: Exception) {
                        NotificationService.notifyWithContext(
                            applicationContext,
                            "Данное блюдо уже существует"
                        )
                    }
                }
            }

            R.id.fragment_edit_food -> {
                if (!ValidateOnBlankUseCase().execute(getListStringFromEditText())) {
                    return NotificationService.notify(applicationContext, "Заполните все поля")
                }

                lifecycleScope.launch(Dispatchers.Main) {
                    try {
                        val editingFood = getFoodFromEditText()

                        EditFoodUseCase().execute(
                            EditFoodFragment.savedTitle,
                            editingFood,
                            dbRepository
                        )

                        EditFoodFragment.savedTitle = editingFood.title
                        navController.navigate(R.id.action_fragment_edit_food_to_fragment_food)

                        NotificationService.notifyWithContext(applicationContext, "Блюдо успешно обновлено")
                    } catch (ex: SQLiteConstraintException) {
                        NotificationService.notifyWithContext(applicationContext, "Данное блюдо уже существует")
                    }
                }
            }
        }
    }

    private fun setUpBottomNavigationView(){
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.menu.getItem(1).isEnabled = false

        appBarConfiguration = AppBarConfiguration(setOf(R.id.fragment_journal, R.id.fragment_food))

        navController = findNavController(R.id.fragmentContainer)
        bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun getListStringFromEditText(): List<String> {
        val title = findViewById<EditText>(R.id.txtBox_foodTitle).text.toString()
        val protein = findViewById<EditText>(R.id.txtBox_protein).text.toString()
        val fats = findViewById<EditText>(R.id.txtBox_fat).text.toString()
        val carbs = findViewById<EditText>(R.id.txtBox_сarbs).text.toString()
        val calories = findViewById<EditText>(R.id.txtBox_calories).text.toString()

        return mutableListOf(
            title,
            protein,
            fats,
            carbs,
            calories
        )
    }

    private fun getFoodFromEditText(): Food{
        val title = findViewById<EditText>(R.id.txtBox_foodTitle).text.toString()
        val protein = findViewById<EditText>(R.id.txtBox_protein).text.toString().toInt()
        val fats = findViewById<EditText>(R.id.txtBox_fat).text.toString().toInt()
        val carbs = findViewById<EditText>(R.id.txtBox_сarbs).text.toString().toInt()
        val calories = findViewById<EditText>(R.id.txtBox_calories).text.toString().toInt()

        return Food(
            title,
            protein,
            fats,
            carbs,
            calories
        )
    }

    private fun clearEditText(){
        findViewById<EditText>(R.id.txtBox_foodTitle).text.clear()
        findViewById<EditText>(R.id.txtBox_protein).text.clear()
        findViewById<EditText>(R.id.txtBox_fat).text.clear()
        findViewById<EditText>(R.id.txtBox_сarbs).text.clear()
        findViewById<EditText>(R.id.txtBox_calories).text.clear()
    }

    fun btnOpenSettings(item: MenuItem) {
        navController.navigate(
            R.id.fragment_settings,
            null,
            navOptions {
                anim {
                    enter = R.animator.nav_default_pop_enter_anim
                    exit = R.anim.nav_default_pop_exit_anim
                    popEnter = R.anim.nav_default_pop_enter_anim
                    popExit = R.anim.nav_default_pop_exit_anim
                }
            })
        bottomNavigationContainer.animate().alpha(0.0f).duration = 150
    }
}