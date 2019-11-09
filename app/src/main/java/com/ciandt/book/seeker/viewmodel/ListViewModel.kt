package com.ciandt.book.seeker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciandt.book.seeker.di.DaggerApiComponent
import com.ciandt.book.seeker.model.ApiResponse
import com.ciandt.book.seeker.model.Book
import com.ciandt.book.seeker.model.BooksService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {

    @Inject
    lateinit var booksService: BooksService
    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val books = MutableLiveData<List<Book>>()
    val bookLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage =  MutableLiveData<String>()

    fun refresh(query: String){
        fetchBooks(query)
    }

    private fun fetchBooks(query: String){
        loading.value = true
        disposable.add(
            booksService.getApiResponse(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver <ApiResponse>(){
                    override fun onError(e: Throwable) {
                        bookLoadError.value = true
                        loading.value = false
                        errorMessage.value = "Request has failed, please try again latter"
                    }
                    override fun onSuccess(value: ApiResponse) {
                        if (value.resultCount > 0){
                            books.value = value.results
                            bookLoadError.value = false
                            loading.value = false
                        } else{
                            bookLoadError.value = true
                            loading.value = false
                            errorMessage.value = "Please try another query"
                        }
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}