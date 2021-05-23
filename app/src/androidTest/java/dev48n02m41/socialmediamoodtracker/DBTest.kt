package dev48n02m41.socialmediamoodtracker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev48n02m41.socialmediamoodtracker.data.AppDatabase
import dev48n02m41.socialmediamoodtracker.data.dao.APIDiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.dao.DiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var tempAppDatabase: AppDatabase
    private lateinit var diaryEntryDao: DiaryEntryDao
    private lateinit var apiDiaryEntryDao: APIDiaryEntryDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        tempAppDatabase = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()

        diaryEntryDao = tempAppDatabase.diaryEntryDao()
        apiDiaryEntryDao = tempAppDatabase.apiDiaryEntryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        tempAppDatabase.close()
    }

    @Test
    fun writeAndReadNewDBData() {
        val newEntry = DiaryEntryEntity ("Twitter", 2, 4)

        diaryEntryDao.insertTest(newEntry)

        val findByNetwork = diaryEntryDao.getBySocialNetworkTest("Twitter")
        assertThat(findByNetwork[0], equalTo(newEntry))
        assertThat(findByNetwork[0].afterMoodRating, equalTo(4))
        assertThat(findByNetwork[0].beforeMoodRating, equalTo(2))
        assertThat(findByNetwork[0].id, notNullValue())
    }

}