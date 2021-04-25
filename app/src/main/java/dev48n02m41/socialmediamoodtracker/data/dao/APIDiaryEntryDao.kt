package dev48n02m41.socialmediamoodtracker.data.dao

import androidx.room.*
import dev48n02m41.socialmediamoodtracker.data.entities.APIDiaryEntryEntity
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface APIDiaryEntryDao {

    @Query("SELECT * FROM api_diary_entry_table ORDER BY id DESC")
    abstract fun getAll(): Flow<List<APIDiaryEntryEntity>>

    fun getAllFlow() = getAll().distinctUntilChanged() // Flow only notifies on change.

    @Query("SELECT * FROM api_diary_entry_table WHERE social_network = :socialNetworkIn ORDER BY id DESC")
    abstract fun getBySocialNetwork(socialNetworkIn: String): Flow<List<APIDiaryEntryEntity>>

    fun getAllBySocialNetworkFlow(socialNetworkIn: String) = getBySocialNetwork(socialNetworkIn).distinctUntilChanged()

    // Insert / update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(objectsIn: List<APIDiaryEntryEntity>)

    @Update
    suspend fun updateOne(vararg objectIn: APIDiaryEntryEntity)

    // Delete
    @Delete
    suspend fun deleteOne(vararg objectIn: APIDiaryEntryEntity)

    @Query("DELETE FROM api_diary_entry_table")
    suspend fun deleteAll()
}