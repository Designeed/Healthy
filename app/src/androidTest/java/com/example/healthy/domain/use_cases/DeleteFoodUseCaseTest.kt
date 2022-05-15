package com.example.healthy.domain.use_cases

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
class DeleteFoodUseCaseTest {

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
    fun deleteFood() = runBlockingTest {
        val food = Food("1", 1, 1, 1, 1)

        AddFoodUseCase().execute(food, repository)
        DeleteFoodUseCase().execute(food.title, repository)

        launch {
            repository.getAllFood().collect{
                assertThat(it).isEmpty()
            }
        }.cancel()
    }
}
