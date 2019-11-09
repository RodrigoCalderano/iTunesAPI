package com.ciandt.book.seeker.model

import com.ciandt.book.seeker.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class BooksService {

    @Inject
    lateinit var api: BooksApi
    init{
        DaggerApiComponent.create().inject(this)
    }

    fun getApiResponse(query: String): Single<ApiResponse> {
        return api.getApiData(query)
    }
}