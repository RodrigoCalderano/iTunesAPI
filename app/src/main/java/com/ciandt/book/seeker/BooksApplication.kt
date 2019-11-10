package com.ciandt.book.seeker

import androidx.multidex.MultiDexApplication

class BooksApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object {
        private var appInstance: BooksApplication? = null
        fun getInstance(): BooksApplication {
            checkNotNull(appInstance) { "Must configure application in AndroidManifest" }
            return appInstance!!
        }
    }
}
