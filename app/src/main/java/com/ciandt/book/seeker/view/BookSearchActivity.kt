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
        button.setOnClickListener{
            if (!isQueryValid(searchTxt.text!!)) {
                textInputLayout.error = "Your query must be bigger"
            } else {
                textInputLayout.error = null
                loginLoginBtnClicked()
            }
        }

        val tw = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
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

    private fun loginLoginBtnClicked() {
        print("asdasddsaasd")
        print(searchTxt.text)
        startActivity<BookListView>("myParam" to 'a')
    }
}
