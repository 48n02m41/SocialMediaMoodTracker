package dev48n02m41.socialmediamoodtracker.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "diary_entry_table")
data class DiaryEntryEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "social_network") val socialNetworkChoice: String?,
    @ColumnInfo(name = "before_mood_rating") val beforeMoodRating: Int?,
    @ColumnInfo(name = "after_mood_rating") val afterMoodRating: Int?,
    @ColumnInfo(name = "date_created") val dateCreated: Date?
)