package com.ciandt.book.seeker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ciandt.book.seeker.R
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailsActivity : AppCompatActivity() {

    val name : String by lazy { intent.getStringExtra("name")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        textView.text = name
    }
}
