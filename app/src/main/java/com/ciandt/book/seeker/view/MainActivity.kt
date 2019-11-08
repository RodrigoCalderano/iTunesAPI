package com.ciandt.book.seeker.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val booksAdapter = BookListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        booksList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = booksAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.books.observe(this, Observer {books ->
            books?.let{ booksAdapter.updateBooks(it)}
            booksList.visibility = View.VISIBLE
        })

        viewModel.bookLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE}
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    booksList.visibility = View.GONE
                }
            }
        })
    }
}
