package com.ciandt.book.seeker.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.dao.BookServiceDAO
import com.ciandt.book.seeker.model.Book
import com.ciandt.book.seeker.util.getProgressDrawable
import com.ciandt.book.seeker.util.loadImage
import kotlinx.android.synthetic.main.activity_book_details.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BookDetailsActivity : AppCompatActivity() {

    private val name: String by lazy { intent.getStringExtra("name") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)


        doAsync {
            val book: Book? = BookServiceDAO.getBook(name)
            uiThread {
                if(book!=null){
                    detailsTitle.text = book.name
                    detailsAuthor.text = book.author
                    val progressDrawable = getProgressDrawable(applicationContext)
                    @Suppress("DEPRECATION")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        detailsDesc.text = Html.fromHtml(book.description, Html.FROM_HTML_MODE_LEGACY).toString()
                    } else detailsDesc.text = Html.fromHtml(book.description).toString()

                    imageView.loadImage(book.image1100, progressDrawable)
                }
            }


//            val progressDrawable = getProgressDrawable(this)
//            detailsTitle.text = name
//            detailsAuthor.text = saved //TODO
//            @Suppress("DEPRECATION")
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                detailsDesc.text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString()
//            } else detailsDesc.text = Html.fromHtml(description).toString()
//
//            imageView.loadImage(image1100, progressDrawable)

        }
    }
}
