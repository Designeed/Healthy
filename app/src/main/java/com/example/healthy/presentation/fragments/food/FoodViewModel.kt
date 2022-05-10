package com.example.healthy.presentation.fragments.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.repository.FoodRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class FoodViewModel(private val repository: FoodRepository): ViewModel() {

    val foodListLifeData = MutableLiveData<List<Food>>()

    init{
        try {
            viewModelScope.launch{
                repository.getAllFood().collect{ items ->
                    foodListLifeData.postValue(items)
                }
            }
        } catch (e: Exception){ }
    }
}