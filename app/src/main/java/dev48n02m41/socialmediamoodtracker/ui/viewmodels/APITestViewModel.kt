package dev48n02m41.socialmediamoodtracker.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev48n02m41.socialmediamoodtracker.data.AppRepository
import dev48n02m41.socialmediamoodtracker.data.entities.APIDiaryEntryEntity
import kotlinx.coroutines.launch

class APITestViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    init {
        repository = AppRepository(application)
    }

    fun insertAPIDiaryEntries(entries: List<APIDiaryEntryEntity>) = viewModelScope.launch {
        repository.insertAPIDiaryEntries(entries)
    }
}