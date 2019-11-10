package com.ciandt.book.seeker.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ciandt.book.seeker.model.Search

@Database(entities = [Search::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class SearchDatabase : RoomDatabase() {
    abstract fun searchDAO(): SearchDAO
}

class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}
