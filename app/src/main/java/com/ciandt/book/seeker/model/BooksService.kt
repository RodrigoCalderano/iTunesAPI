package com.ciandt.book.seeker.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BooksService {

    private val BASE_URL = "https://itunes.apple.com/"
    private val api: BooksApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }

    fun getApiResponse(): Single<ApiResponse> {
        return api.getApiData()
    }
}