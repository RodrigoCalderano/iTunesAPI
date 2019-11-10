package com.ciandt.book.seeker.dao

import com.ciandt.book.seeker.model.Book

object BookServiceDAO {
    fun getBooks(): List<Book> {
        val dao = DatabaseManager.getBookDAO()
        val books = dao.findAll()
        return books
    }

    fun getBook(name: String): Book? {
        val dao = DatabaseManager.getBookDAO()
        val book = dao.getByName(name)
        return book
    }

    fun isSaved(book: Book): Boolean {
        val dao = DatabaseManager.getBookDAO()
        val exists = dao.getByName(book.name) != null
        return exists
    }

    fun save(book: Book): String {
        val saved = isSaved(book)
        val dao = DatabaseManager.getBookDAO()
        if (saved) {
            // dao.delete(book)
            return "Already saved"
        } else {
            dao.insert(book)
            return "Saved"
        }
    }
}
