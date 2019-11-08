package com.ciandt.book.seeker.model

import io.reactivex.Single
import retrofit2.http.GET

interface BooksApi {

    @GET("search?term=kotlin&entity=ibook")
    fun getApiData(): Single<ApiResponse>
}