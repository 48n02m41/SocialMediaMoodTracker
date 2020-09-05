package dev48n02m41.socialmediamoodtracker.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import dev48n02m41.socialmediamoodtracker.data.dao.DiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity

class AppRepository(application: Application) {
    private var diaryEntryDao: DiaryEntryDao

    // Observed data
    var allDiaryEntries: LiveData<List<DiaryEntryEntity>>

    init {
        val database: AppDatabase = AppDatabase.getDatabase(application.applicationContext)

        diaryEntryDao = database.diaryEntryDao()
        allDiaryEntries = diaryEntryDao.getAllFlow().asLiveData()
    }

    fun getAllDiaryEntriesBySocialNetwork(socialNetwork: String) {
        diaryEntryDao.getAllBySocialNetworkFlow(socialNetwork)
    }

    suspend fun insertDiaryEntry(objectIn: DiaryEntryEntity) {
        diaryEntryDao.insertOne(objectIn)
    }

    suspend fun updateOneDiaryEntry(objectIn: DiaryEntryEntity) {
        diaryEntryDao.updateOne(objectIn)
    }

    suspend fun deleteOneDiaryEntry(objectIn: DiaryEntryEntity) {
        diaryEntryDao.deleteOne(objectIn)
    }

    suspend fun deleteAllDiaryEntries() {
        diaryEntryDao.deleteAll()
    }


    companion object {
        private const val TAG = "AppRepository"
    }
}