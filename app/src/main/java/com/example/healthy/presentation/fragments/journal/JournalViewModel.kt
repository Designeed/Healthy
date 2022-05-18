package com.example.healthy.presentation.fragments.journal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthy.domain.model.Journal
import com.example.healthy.domain.repository.JournalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class JournalViewModel(
    private val repository: JournalRepository
) : ViewModel() {
    val foodListLifeData = MutableLiveData<List<Journal>>()

    init{
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getAllJournal().collect { items ->
                    foodListLifeData.postValue(items)
                }
            }
        } catch (e: Exception){ }
    }
}