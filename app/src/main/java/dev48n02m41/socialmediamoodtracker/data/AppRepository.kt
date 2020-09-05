package dev48n02m41.socialmediamoodtracker.data

import android.app.Application
import dev48n02m41.socialmediamoodtracker.data.dao.DiaryEntryDao

class AppRepository(application: Application) {
    private var diaryEntryDao: DiaryEntryDao

    init {
        val database: AppDatabase = AppDatabase.getDatabase(application.applicationContext)

        diaryEntryDao = database.diaryEntryDao()
    }


    companion object {
        private const val TAG = "AppRepository"
    }
}