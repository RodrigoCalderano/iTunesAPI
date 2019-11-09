package com.ciandt.book.seeker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ciandt.book.seeker.R
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_book_search.*

class BookSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)
        button.setOnClickListener { startActivity<BookListView>("myParam" to 'a') }
    }
}
