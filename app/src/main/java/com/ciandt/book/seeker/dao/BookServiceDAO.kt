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
        return dao.getByName(book.name) != null
    }

    fun saveAll(books: List<Book>): Boolean {
        try {
            books.forEach {
                if (!isSaved(it)) {
                    save(it)
                }
            }
        } catch (e: Throwable) {
            return false
        }
        return true
    }

    fun save(book: Book): String {
        val saved = isSaved(book)
        val dao = DatabaseManager.getBookDAO()
        return if (saved) {
            // dao.delete(book)
            "Already saved"
        } else {
            dao.insert(book)
            "Saved"
        }
    }
}
