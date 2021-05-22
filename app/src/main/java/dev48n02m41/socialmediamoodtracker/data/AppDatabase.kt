package dev48n02m41.socialmediamoodtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev48n02m41.socialmediamoodtracker.data.converters.DateConverter
import dev48n02m41.socialmediamoodtracker.data.dao.APIDiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.dao.DiaryEntryDao
import dev48n02m41.socialmediamoodtracker.data.entities.APIDiaryEntryEntity
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity

@Database(
    entities = [DiaryEntryEntity::class, APIDiaryEntryEntity::class],
    version = 3,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun diaryEntryDao(): DiaryEntryDao
    abstract fun apiDiaryEntryDao(): APIDiaryEntryDao

    companion object {
        // DB Singleton with thread safety.
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database")
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}