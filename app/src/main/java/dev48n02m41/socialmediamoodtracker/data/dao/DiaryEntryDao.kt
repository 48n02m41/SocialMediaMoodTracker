package dev48n02m41.socialmediamoodtracker.data.dao

import androidx.room.*
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface DiaryEntryDao {

    @Query("SELECT * FROM diary_entry_table ORDER BY id DESC")
    abstract fun getAll(): Flow<List<DiaryEntryEntity>>

    fun getAllFlow() = getAll().distinctUntilChanged() // Flow only notifies on change.

    @Query("SELECT * FROM diary_entry_table WHERE social_network = :socialNetworkIn ORDER BY id DESC")
    abstract fun getBySocialNetwork(socialNetworkIn: String): Flow<List<DiaryEntryEntity>>

    fun getAllBySocialNetworkFlow(socialNetworkIn: String) = getBySocialNetwork(socialNetworkIn).distinctUntilChanged()

    // Insert / update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(vararg objectIn: DiaryEntryEntity)

    @Update
    suspend fun updateOne(vararg objectIn: DiaryEntryEntity)

    // Delete
    @Delete
    suspend fun deleteOne(vararg objectIn: DiaryEntryEntity)

    @Query("DELETE FROM diary_entry_table")
    suspend fun deleteAll()
}