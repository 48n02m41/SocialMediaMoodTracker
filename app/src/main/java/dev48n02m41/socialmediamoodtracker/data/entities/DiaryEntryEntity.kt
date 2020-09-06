package dev48n02m41.socialmediamoodtracker.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "diary_entry_table")
data class DiaryEntryEntity(
    @ColumnInfo(name = "social_network") var socialNetworkChoice: String?,
    @ColumnInfo(name = "before_mood_rating") var beforeMoodRating: Int?,
    @ColumnInfo(name = "after_mood_rating") var afterMoodRating: Int?,
) {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
    @ColumnInfo(name = "date_created") var dateCreated: Instant = Instant.now()
}