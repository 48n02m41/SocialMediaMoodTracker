package dev48n02m41.socialmediamoodtracker.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dev48n02m41.socialmediamoodtracker.data.AppRepository
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import kotlinx.coroutines.launch

class DiaryEntryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    // Observed data
    val allDiaryEntries: LiveData<List<DiaryEntryEntity>>

    init {
        repository = AppRepository(application)
        allDiaryEntries = repository.allDiaryEntries
    }

    fun insertDiaryEntry(entry: DiaryEntryEntity) = viewModelScope.launch {
        repository.insertDiaryEntry(entry)
    }
}