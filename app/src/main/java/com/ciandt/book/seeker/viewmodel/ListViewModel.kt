package com.ciandt.book.seeker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciandt.book.seeker.model.ApiResponse
import com.ciandt.book.seeker.model.Book
import com.ciandt.book.seeker.model.BooksService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private val booksService = BooksService()
    private val disposable = CompositeDisposable()


    val books = MutableLiveData<List<Book>>()
    val bookLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchBooks()
    }

    private fun fetchBooks(){
        loading.value = true
        disposable.add(
            booksService.getApiResponse()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver <ApiResponse>(){
                    override fun onSuccess(value: ApiResponse) {
                        books.value = value.results
                        bookLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        bookLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}