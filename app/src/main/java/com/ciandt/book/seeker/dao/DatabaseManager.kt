package com.ciandt.book.seeker.dao

import androidx.room.Room
import com.ciandt.book.seeker.BooksApplication

object DatabaseManager {
    private var dbInstance: BookDatabase

    init {
        val appContext = BooksApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(appContext,
            BookDatabase::class.java, "carros.sqlite").build()
    }

    fun getBookDAO(): BookDAO {
        return dbInstance.BookDAO()
    }
}