package com.ciandt.book.seeker.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.util.getProgressDrawable
import com.ciandt.book.seeker.util.loadImage
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailsActivity : AppCompatActivity() {

    private val name : String by lazy { intent.getStringExtra("name")}
    private val author : String by lazy { intent.getStringExtra("author")}
    private val description : String by lazy { intent.getStringExtra("description")}
    private val image1100 : String by lazy { intent.getStringExtra("image1100")}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val progressDrawable = getProgressDrawable(this)
        detailsTitle.text = name
        detailsAuthor.text = author
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            detailsDesc.text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString()
        } else detailsDesc.text =  Html.fromHtml(description).toString()

        imageView.loadImage(image1100, progressDrawable)
    }
}
