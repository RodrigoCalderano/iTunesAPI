package com.ciandt.book.seeker.di

import com.ciandt.book.seeker.model.BooksService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: BooksService)
}