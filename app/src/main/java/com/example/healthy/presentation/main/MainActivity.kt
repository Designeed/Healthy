package com.example.healthy.presentation.main

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Spinner
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
import com.example.healthy.data.repository.JournalRepositoryImpl
import com.example.healthy.data.repository.SharedPrefRepositoryImpl
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.use_cases.food.AddFoodUseCase
import com.example.healthy.domain.use_cases.food.EditFoodUseCase
import com.example.healthy.domain.use_cases.general.SwitchThemeUseCase
import com.example.healthy.domain.use_cases.journal.AddJournalNoteUseCase
import com.example.healthy.domain.use_cases.shared.NotificationService
import com.example.healthy.domain.use_cases.shared.SetImageButton
import com.example.healthy.domain.use_cases.shared.ValidateOnBlank
import com.example.healthy.domain.use_cases.sharedPref.GetThemeUseCase
import com.example.healthy.domain.util.Themes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.DateFormat
import java.util.*
import com.example.healthy.data.room.AppDataBase as AppDataBase

class MainActivity : AppCompatActivity(){
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationContainer: CoordinatorLayout
    private lateinit var dbFoodDao: FoodRepositoryImpl
    private lateinit var dbJournalDao: JournalRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationContainer = findViewById(R.id.bottomNavigationContainer)
        dbFoodDao = FoodRepositoryImpl(AppDataBase.getDatabase(applicationContext).getFoodsDao())
        dbJournalDao = JournalRepositoryImpl(AppDataBase.getDatabase(applicationContext).getJournalDao())
        SetImageButton(findViewById(R.id.fab))
        setAppTheme()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            floatActionButtonClick()
        }
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
                val stringList = getListStringFromEditText()

                if (!ValidateOnBlank().execute(stringList)) {
                    return NotificationService.notify(applicationContext, "Заполните все поля")
                }

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val editingFood = mapToFood(stringList)

                        AddFoodUseCase().execute(
                            editingFood,
                            dbFoodDao)

                        lifecycleScope.launch(Dispatchers.Main){
                            clearEditText()
                        }
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
                val listString = getListStringFromEditText()

                if (!ValidateOnBlank().execute(listString))
                    return NotificationService.notify(applicationContext, "Заполните все поля")

                lifecycleScope.launch(Dispatchers.Main) {
                    try {
                        val editingFood = mapToFood(listString)

                        EditFoodUseCase().execute(
                            editingFood,
                            dbFoodDao
                        )

                        navController.navigate(R.id.action_fragment_edit_food_to_fragment_food)
                        NotificationService.notifyWithContext(applicationContext, "Блюдо успешно обновлено")
                    } catch (ex: SQLiteConstraintException) {
                        NotificationService.notifyWithContext(applicationContext, "Данное блюдо уже существует")
                    }
                }
            }

            R.id.fragment_add_journal -> {
                val spinner = findViewById<Spinner>(R.id.foodSpinner)
                val weight = findViewById<EditText>(R.id.txtBox_weight).text.toString()

                if (spinner.selectedItemPosition == -1)
                    return NotificationService.notify(applicationContext, "Пополните список еды")

                if (weight.isBlank())
                    return NotificationService.notify(applicationContext, "Заполните поле")

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val foodTitle = spinner.selectedItem.toString()
                        val food = dbFoodDao.getFoodByTitle(foodTitle)
                        AddJournalNoteUseCase().execute(food, weight.toFloat(), dbJournalDao)

                        lifecycleScope.launch(Dispatchers.Main){
                            navController.navigate(R.id.action_fragment_add_journal_to_fragment_journal)
                        }
                        NotificationService.notifyWithContext(applicationContext, "Запись успешно добавлена")

                    } catch (ex: Exception) {
                        NotificationService.notifyWithContext(applicationContext, ex.message.toString())
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

        return mutableListOf(title, protein, fats, carbs, calories)
    }

    private fun mapToFood(list : List<String>) : Food {
        return Food(
            list[0],
            list[1].toFloat().toInt(),
            list[2].toFloat().toInt(),
            list[3].toFloat().toInt(),
            list[4].toFloat().toInt(),
        )
    }

    private fun clearEditText(){
        findViewById<EditText>(R.id.txtBox_foodTitle).text.clear()
        findViewById<EditText>(R.id.txtBox_protein).text.clear()
        findViewById<EditText>(R.id.txtBox_fat).text.clear()
        findViewById<EditText>(R.id.txtBox_сarbs).text.clear()
        findViewById<EditText>(R.id.txtBox_calories).text.clear()
    }

    private fun setAppTheme() {
        val repository = SharedPrefRepositoryImpl(applicationContext)
        lifecycleScope.launch {
            val savedTheme = GetThemeUseCase().execute(repository)
            SwitchThemeUseCase().execute(savedTheme)
        }
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