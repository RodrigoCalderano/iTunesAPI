package com.ciandt.book.seeker.dao.book

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ciandt.book.seeker.model.Book

@Dao
interface BookDAO {
    @Query("SELECT * FROM book where name = :name")
    fun getByName(name: String): Book?
    @Query("SELECT * FROM book")
    fun findAll(): List<Book>
    @Insert
    fun insert(book: Book)
    @Delete
    fun delete(book: Book)
}
