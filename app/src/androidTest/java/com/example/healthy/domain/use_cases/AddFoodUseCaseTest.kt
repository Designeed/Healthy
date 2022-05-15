package com.example.healthy.domain.use_cases

import android.database.sqlite.SQLiteConstraintException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import com.example.healthy.domain.model.Food
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AddFoodUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDataBase
    private lateinit var repository: FoodRepositoryImpl

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
        repository = FoodRepositoryImpl(database.getFoodsDao())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertOneFood() = runBlockingTest {
        val food = Food("1", 1, 1, 1, 1)

        AddFoodUseCase().execute(food, repository)
        launch {
            repository.getAllFood().collect {
                assertThat(it).contains(food)
            }
        }.cancel()
    }

    @Test
    fun insertTwoFood() = runBlockingTest {
        val food1 = Food("1", 1, 1, 1, 1)
        val food2 = Food("2", 1, 1, 1, 1)

        AddFoodUseCase().execute(food1, repository)
        AddFoodUseCase().execute(food2, repository)

        launch {
            repository.getAllFood().collect {
                assertThat(it).contains(food1)
                assertThat(it).contains(food2)
            }
        }.cancel()
    }

    @Test
    fun insertAlreadyExistFood() = runBlockingTest {
        val food = Food("1", 1, 1, 1, 1)
        AddFoodUseCase().execute(food, repository)

        try {
            AddFoodUseCase().execute(food, repository)
            assert(false)
        } catch (ex: SQLiteConstraintException) {
            assert(true)
        }
    }
}
