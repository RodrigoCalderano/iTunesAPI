package com.ciandt.book.seeker.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.ciandt.book.seeker.R
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_book_search.*

class BookSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)
        setupListeners()
    }

    private fun setupListeners() {
        button.setOnClickListener {
            if (!isQueryValid(searchTxt.text!!)) {
                textInputLayout.error = "Your query must be bigger"
            } else {
                textInputLayout.error = null
                val query = searchTxt.text
                startActivity<BookListView>("query" to query.toString())
            }
        }

        val tw = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // Detekt EmptyFunctionBlock suppress
                }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Detekt EmptyFunctionBlock suppress
                }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (isQueryValid(searchTxt.text!!)) {
                    textInputLayout.error = null
                }
            }
        }
        searchTxt.addTextChangedListener(tw)
    }

    private fun isQueryValid(text: Editable?): Boolean {
        return text != null && text.length >= 2
    }
}
