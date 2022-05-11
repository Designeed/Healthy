package com.example.healthy.presentation.fragments.journal.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthy.domain.repository.FoodRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class AddJournalViewModel(private val repository: FoodRepository): ViewModel() {

    var foodListLifeData = MutableLiveData<List<String>>()

    init{
        try {
            viewModelScope.launch {
                repository.getAllFood().collect { foodList ->
                    foodListLifeData.postValue (foodList.map {
                        it.title
                    })
                }
            }
        } catch (e: Exception){ }
    }
}