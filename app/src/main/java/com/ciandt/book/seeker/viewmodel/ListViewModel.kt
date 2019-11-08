package com.ciandt.book.seeker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciandt.book.seeker.model.Book

class ListViewModel: ViewModel() {

    val books = MutableLiveData<List<Book>>()
    val bookLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchBooks()
    }

    private fun fetchBooks(){
        val mockData = listOf(
            Book(name = "BookA", author = "Author A", description = "desc",
                image160 = "Im160", image1100 = "Im1100"),
            Book(name = "BookB", author = "Author B", description = "descb",
                image160 = "Im160b", image1100 = "Im1100b"),
            Book(name = "BookC", author = "Author C", description = "descc",
                image160 = "Im160c", image1100 = "Im1100c"),
            Book(name = "BookD", author = "Author D", description = "descd",
                image160 = "Im160d", image1100 = "Im1100d")
        )

        bookLoadError.value = false
        loading.value = false
        books.value = mockData
    }
}