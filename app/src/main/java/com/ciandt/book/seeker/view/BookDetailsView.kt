package com.ciandt.book.seeker.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.util.getProgressDrawable
import com.ciandt.book.seeker.util.loadImage
import com.ciandt.book.seeker.viewmodel.BookDetailsViewModel
import kotlinx.android.synthetic.main.activity_book_details.*
import org.jetbrains.anko.toast

class BookDetailsView : AppCompatActivity() {

    private val bookName: String by lazy { intent.getStringExtra("name") }

    private lateinit var viewModel: BookDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        viewModel = ViewModelProviders.of(this).get(BookDetailsViewModel::class.java)
        viewModel.fetchBookFromDb(bookName)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.book.observe(this, Observer { book ->
            book?.let {
                detailsTitle.text = book.name
                detailsAuthor.text = book.author
                val progressDrawable = getProgressDrawable(applicationContext)
                @Suppress("DEPRECATION")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    detailsDesc.text =
                        Html.fromHtml(book.description, Html.FROM_HTML_MODE_LEGACY).toString()
                } else detailsDesc.text = Html.fromHtml(book.description).toString()
                imageView.loadImage(book.image1100, progressDrawable)
            }
        })

        viewModel.errorMessage.observe(this, Observer { toast(it) })
    }
}
