package com.ciandt.book.seeker

import android.util.Log
import androidx.multidex.MultiDexApplication

class BooksApplication : MultiDexApplication() {
    private val TAG = "BooksApplication"
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object {
        // Singleton da classe Application
        private var appInstance: BooksApplication? = null
        fun getInstance(): BooksApplication {
            if(appInstance == null) {
                throw IllegalStateException("Must configure application in AndroidManifest")
            }
            return appInstance!!
        }
    }
    override fun onTerminate(){
        super.onTerminate()
        Log.d(TAG, "BooksApplication.onTerminate()")
    }
}