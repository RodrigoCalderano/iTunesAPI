package com.ciandt.book.seeker.dao

import androidx.room.Room
import com.ciandt.book.seeker.BooksApplication
import com.ciandt.book.seeker.dao.book.BookDAO
import com.ciandt.book.seeker.dao.book.BookDatabase
import com.ciandt.book.seeker.dao.search.SearchDAO
import com.ciandt.book.seeker.dao.search.SearchDatabase

object DatabaseManager {
    private var dbInstance: BookDatabase
    private var dbSearchInstance: SearchDatabase

    init {
        val appContext = BooksApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(appContext,
            BookDatabase::class.java, "books.sqlite").build()
        dbSearchInstance = Room.databaseBuilder(appContext,
            SearchDatabase::class.java, "search.sqlite").build()
    }

    fun getBookDAO(): BookDAO {
        return dbInstance.bookDAO()
    }
    fun getSearchDAO(): SearchDAO {
        return dbSearchInstance.searchDAO()
    }
}
