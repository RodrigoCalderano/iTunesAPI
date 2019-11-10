package com.ciandt.book.seeker.dao.search

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ciandt.book.seeker.model.Search

@Database(entities = [Search::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class SearchDatabase : RoomDatabase() {
    abstract fun searchDAO(): SearchDAO
}
