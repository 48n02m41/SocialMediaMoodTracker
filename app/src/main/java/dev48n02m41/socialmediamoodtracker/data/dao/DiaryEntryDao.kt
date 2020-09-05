package dev48n02m41.socialmediamoodtracker.data.dao

import androidx.room.Dao
import androidx.room.Query
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface DiaryEntryDao {

    @Query("SELECT * FROM diary_entry_table")
    abstract fun getAll(): Flow<List<DiaryEntryEntity>>

    fun getAllFlow() = getAll().distinctUntilChanged() // Flow only notifies on change.

    @Query("SELECT * FROM diary_entry_table WHERE social_network = :socialNetworkIn")
    abstract fun getBySocialNetwork(socialNetworkIn: String): Flow<List<DiaryEntryEntity>>

    fun getAllBySocialNetworkFlow(socialNetworkIn: String) = getBySocialNetwork(socialNetworkIn).distinctUntilChanged()


}