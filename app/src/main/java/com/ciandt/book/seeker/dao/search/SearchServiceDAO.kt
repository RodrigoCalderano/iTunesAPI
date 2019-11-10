package com.ciandt.book.seeker.dao.search

import com.ciandt.book.seeker.dao.DatabaseManager
import com.ciandt.book.seeker.model.Search

object SearchServiceDAO {
    fun getSearches(): List<Search> {
        val dao = DatabaseManager.getSearchDAO()
        return dao.findAll()
    }

    fun getSearch(queryName: String): Search? {
        val dao = DatabaseManager.getSearchDAO()
        return dao.getByName(queryName)
    }

    fun isSaved(queryName: String): Boolean {
        val dao = DatabaseManager.getSearchDAO()
        return dao.getByName(queryName) != null
    }

    fun save(search: Search): String {
        val saved = isSaved(search.queryName)
        val dao = DatabaseManager.getSearchDAO()
        return if (saved) {
            // dao.delete(book)
            "Already saved"
        } else {
            dao.insert(search)
            "Saved"
        }
    }
}
