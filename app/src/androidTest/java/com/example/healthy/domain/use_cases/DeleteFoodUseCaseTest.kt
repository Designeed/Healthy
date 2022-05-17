package com.example.healthy.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.healthy.data.repository.FoodRepositoryImpl
import com.example.healthy.data.room.AppDataBase
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.use_cases.food.AddFoodUseCase
import com.example.healthy.domain.use_cases.food.DeleteFoodUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DeleteFoodUseCaseTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val scope = TestScope(dispatcher)

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
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        database.close()
        Dispatchers.resetMain()
    }

    @Test
    fun deleteFood_success_foodShouldDeleted() = scope.runTest {
        val food = Food("1", 1, 1, 1, 1)

        AddFoodUseCase().execute(food, repository)
        DeleteFoodUseCase().execute(food.title, repository)

        launch {
            repository.getAllFood().collect {
                assertThat(it).also { list ->
                    list.doesNotContain(food)
                    list.hasSize(0)
                }
            }
        }.cancel()
    }

    @Test
    fun deleteFoodWithTwoNote_success_food1ShouldDeleted() = scope.runTest {
        val food1 = Food("1", 1, 1, 1, 1)
        val food2 = Food("2", 1, 1, 1, 1)

        AddFoodUseCase().execute(food1, repository)
        AddFoodUseCase().execute(food2, repository)
        DeleteFoodUseCase().execute(food1.title, repository)

        launch {
            repository.getAllFood().collect {
                assertThat(it).also { list ->
                    list.contains(food2)
                    list.hasSize(1)
                }
            }
        }.cancel()
    }
}
