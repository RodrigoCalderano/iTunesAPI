package com.ciandt.book.seeker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ciandt.book.seeker.model.ApiResponse
import com.ciandt.book.seeker.model.Book
import com.ciandt.book.seeker.model.BooksService
import com.ciandt.book.seeker.viewmodel.BookListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {

    @get: Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var booksService: BooksService

    @InjectMocks
    var listViewModel = BookListViewModel()

    private var testSingle: Single<ApiResponse>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getBooksSuccess() {
        val book = Book("BookA", "AuthorA", "", "", "")
        val booksList = arrayListOf(book)
        val apiResponse = ApiResponse(booksList,10)
        testSingle = Single.just(apiResponse)

        `when`(booksService.getApiResponse("kotlin")).thenReturn(testSingle)

        listViewModel.refresh("kotlin")

        Assert.assertEquals(1, listViewModel.books.value?.size)
        Assert.assertEquals(false,listViewModel.bookLoadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)
    }

    @Test
    fun getBooksFail() {
        testSingle = Single.error(Throwable())

        `when`(booksService.getApiResponse("kotlin")).thenReturn(testSingle)

        listViewModel.refresh("kotlin")

        Assert.assertEquals(true,listViewModel.bookLoadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { schedule -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { schedule -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { schedule -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { schedule -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedule -> immediate }
    }
}