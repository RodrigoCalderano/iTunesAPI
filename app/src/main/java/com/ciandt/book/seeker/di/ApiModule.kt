package com.ciandt.book.seeker.di

import com.ciandt.book.seeker.model.BooksApi
import com.ciandt.book.seeker.model.BooksService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://itunes.apple.com/"

    @Provides
    fun provideBooksApi(): BooksApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }

    @Provides
    fun provideBooksService(): BooksService {
        return BooksService()
    }
}