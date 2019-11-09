package com.ciandt.book.seeker.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {

    @GET("search")
    fun getApiData(@Query("term") query: String,
                   @Query("entity") entity: String = "ibook"): Single<ApiResponse>
}
