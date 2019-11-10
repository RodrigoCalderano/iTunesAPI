package com.ciandt.book.seeker.viewmodel
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciandt.book.seeker.BooksApplication
import com.ciandt.book.seeker.dao.book.BookServiceDAO
import com.ciandt.book.seeker.dao.search.SearchServiceDAO
import com.ciandt.book.seeker.di.DaggerApiComponent
import com.ciandt.book.seeker.model.ApiResponse
import com.ciandt.book.seeker.model.Book
import com.ciandt.book.seeker.model.BooksService
import com.ciandt.book.seeker.model.Search
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class BookListViewModel : ViewModel() {

    @Inject
    lateinit var booksService: BooksService
    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val books = MutableLiveData<List<Book>>()
    val bookLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun refresh(query: String, swipe: Boolean = false) {
        loading.value = true
        if (swipe) { downloadData(query)
        } else { fetchBooks(query) }
    }

    private fun fetchBooks(query: String) {

        doAsync {
            val isSaved = SearchServiceDAO.isSaved(query)
            uiThread {
                if (isSaved) { getFromDb(query)
                } else { downloadData(query) }
            }
        }
    }

    fun downloadData(query: String) {
        Toast.makeText(BooksApplication.getInstance().applicationContext,
            "Downloading from API", Toast.LENGTH_SHORT).show()
        disposable.add(
            booksService.getApiResponse(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver <ApiResponse>() {
                    override fun onError(e: Throwable) {
                        bookLoadError.value = true
                        loading.value = false
                        errorMessage.value = "Request has failed, please try again latter"
                    }
                    override fun onSuccess(apiResponse: ApiResponse) {
                        if (apiResponse.resultCount > 0) {
                            books.value = apiResponse.results
                            bookLoadError.value = false
                            loading.value = false
                            saveDb(apiResponse.results, query)
                        } else {
                            bookLoadError.value = true
                            loading.value = false
                            errorMessage.value = "Please try another query"
                        }
                    }
                })
        )
    }

    private fun getFromDb(query: String) {
        Toast.makeText(BooksApplication.getInstance().applicationContext,
            "Getting from DB", Toast.LENGTH_SHORT).show()
        doAsync {
            val search = SearchServiceDAO.getSearch(query)
            var booksFromDb = listOf<Book>()

            search!!.booksNames.forEach {
                if (BookServiceDAO.getBook(it) != null) {
                    booksFromDb = booksFromDb + BookServiceDAO.getBook(it)!!
                }
            }
            uiThread {
                books.value = booksFromDb
                bookLoadError.value = false
                loading.value = false
            }
        }
    }

    private fun saveDb(books: List<Book>, query: String) {
        doAsync {
            BookServiceDAO.saveAll(books)

            var bookNameList = listOf<String>()
            books.forEach { bookNameList = bookNameList + it.name }
            val search = Search(query, bookNameList)
            SearchServiceDAO.save(search)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
