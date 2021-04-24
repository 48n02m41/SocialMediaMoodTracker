package dev48n02m41.socialmediamoodtracker.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import dev48n02m41.socialmediamoodtracker.data.dao.APIDiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.dao.DiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.entities.APIDiaryEntryEntity
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity

class AppRepository(application: Application) {
    private var diaryEntryDao: DiaryEntryDao
    private var apiDiaryEntryDao: APIDiaryEntryDao

    // Observed data
    var allDiaryEntries: LiveData<List<DiaryEntryEntity>>
    var allAPIDiaryEntries: LiveData<List<APIDiaryEntryEntity>>

    init {
        val database: AppDatabase = AppDatabase.getDatabase(application.applicationContext)

        diaryEntryDao = database.diaryEntryDao()
        apiDiaryEntryDao = database.apiDiaryEntryDao()

        allDiaryEntries = diaryEntryDao.getAllFlow().asLiveData()
        allAPIDiaryEntries = apiDiaryEntryDao.getAllFlow().asLiveData()
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

    fun getAllAPIDiaryEntriesBySocialNetwork(socialNetwork: String) {
        apiDiaryEntryDao.getAllBySocialNetworkFlow(socialNetwork)
    }

    companion object {
        private const val TAG = "AppRepository"
    }
}