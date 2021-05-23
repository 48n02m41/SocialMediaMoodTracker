package dev48n02m41.socialmediamoodtracker.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dev48n02m41.socialmediamoodtracker.data.api.APIInterface
import dev48n02m41.socialmediamoodtracker.data.dao.APIDiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.dao.DiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.entities.APIDiaryEntryEntity
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

class AppRepository(application: Application) {
    private val scope = CoroutineScope(Dispatchers.Main)
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

    suspend fun insertAPIDiaryEntries(objectsIn: List<APIDiaryEntryEntity>) {
        apiDiaryEntryDao.insertAll(objectsIn)
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

    suspend fun fullSync(token: String) {
        apiGetData(token)
        apiPostData(token)
    }

    private suspend fun apiGetData(token: String) {
        Log.d("API", "Attempting GET...")
        val apiGETAll = APIInterface.create().getAll("Bearer $token")

        if (apiGETAll.isSuccessful) {
            Log.d("API", "Response is null.")

            scope.launch {
                apiDiaryEntryDao.insertAllSuspended(apiGETAll.body()!!)
            }
        } else {
            Log.d("API", "API GET onFailure...")
            Log.d("API", apiGETAll.errorBody().toString())
        }
    }

    private suspend fun apiPostData(token: String) {
        Log.d(AppRepository.TAG, "Attempting POST...")
        var listOfDataToSend = diaryEntryDao.getAllSuspended()

        val apiPOSTAll = APIInterface.create().postALL("Bearer $token", listOfDataToSend)

        if (apiPOSTAll.isSuccessful) {
            // Convert raw JSON to pretty JSON using GSON library
            val gson = GsonBuilder().setPrettyPrinting().create()
            val prettyJson = gson.toJson(
                JsonParser.parseString(
                    apiPOSTAll.body().toString()
                )
            )
            Log.d(AppRepository.TAG, prettyJson)

            // Mark diaryEntries as isUploaded:True and update in SQLite.
            for (data in listOfDataToSend) {
                data.isUploadedToAPI = true
            }
            scope.launch {
                diaryEntryDao.updateAllSuspended(listOfDataToSend)
            }

        } else {
            Log.d(AppRepository.TAG, apiPOSTAll.body().toString())
        }
    }

    companion object {
        private const val TAG = "AppRepository"
    }
}