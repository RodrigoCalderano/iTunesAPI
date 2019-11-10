package com.ciandt.book.seeker.di

import com.ciandt.book.seeker.model.BooksService
import com.ciandt.book.seeker.viewmodel.BookListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: BooksService)

    fun inject(viewModel: BookListViewModel)
}
