package com.ciandt.book.seeker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciandt.book.seeker.dao.BookServiceDAO
import com.ciandt.book.seeker.model.Book
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BookDetailsViewModel : ViewModel() {

    val book = MutableLiveData<Book>()
    val errorMessage = MutableLiveData<String>()

    fun fetchBookFromDb(name: String) {

        doAsync {
            val bookRetrieved: Book? = BookServiceDAO.getBook(name)
            uiThread {
                    if (bookRetrieved != null) {
                        book.value = bookRetrieved
                    } else {
                        errorMessage.value = "Error has occurred while retrieving data"
                    }
            }
        }
    }
}
