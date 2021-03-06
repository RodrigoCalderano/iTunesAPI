package com.ciandt.book.seeker.dao.book

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ciandt.book.seeker.model.Book

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDAO(): BookDAO
}
