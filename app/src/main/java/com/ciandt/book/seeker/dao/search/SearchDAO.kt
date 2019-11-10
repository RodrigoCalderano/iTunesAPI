package com.ciandt.book.seeker.dao.search

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ciandt.book.seeker.model.Search

@Dao
interface SearchDAO {
    @Query("SELECT * FROM search where queryName = :queryName")
    fun getByName(queryName: String): Search?
    @Query("SELECT * FROM search")
    fun findAll(): List<Search>
    @Insert
    fun insert(search: Search)
    @Delete
    fun delete(search: Search)
}

