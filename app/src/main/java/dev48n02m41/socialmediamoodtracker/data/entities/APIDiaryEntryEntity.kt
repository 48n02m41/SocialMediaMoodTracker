package dev48n02m41.socialmediamoodtracker.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "api_diary_entry_table")
data class APIDiaryEntryEntity(
    @Expose @ColumnInfo(name = "social_network") var socialNetworkChoice: String?,
    @Expose @ColumnInfo(name = "before_mood_rating") var beforeMoodRating: Int?,
    @Expose @ColumnInfo(name = "after_mood_rating") var afterMoodRating: Int?,
    @Expose @ColumnInfo(name = "date_created") var dateCreated: String?,
    @Expose @PrimaryKey(autoGenerate = false) var id: Int?,
) {
    override fun toString(): String {
        return "DiaryEntryEntity(socialNetworkChoice=$socialNetworkChoice, " +
                "beforeMoodRating=$beforeMoodRating, afterMoodRating=$afterMoodRating, " +
                "id=$id, dateCreated=$dateCreated)"
    }

//    fun getFormattedDateShort(): String {
//        val dateTimeFormatter: DateTimeFormatter =
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//                .withZone(ZoneId.systemDefault())
//
//        return dateTimeFormatter.format(dateCreated);
//    }
//
//    fun getFormattedDateVeryShort(): String {
//        val dateTimeFormatter: DateTimeFormatter =
//            DateTimeFormatter.ofPattern("MM-dd HH:mm")
//                .withZone(ZoneId.systemDefault())
//
//        return dateTimeFormatter.format(dateCreated);
//    }
}