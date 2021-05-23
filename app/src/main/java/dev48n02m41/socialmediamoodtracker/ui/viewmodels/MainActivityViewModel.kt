package dev48n02m41.socialmediamoodtracker.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev48n02m41.socialmediamoodtracker.data.AppRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    init {
        repository = AppRepository(application)
    }

    suspend fun fullSync(token: String) {
        repository.fullSync(token)
    }

}