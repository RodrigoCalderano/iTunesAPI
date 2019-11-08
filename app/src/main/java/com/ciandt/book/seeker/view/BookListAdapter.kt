package com.ciandt.book.seeker.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.model.Book
import com.ciandt.book.seeker.util.getProgressDrawable
import com.ciandt.book.seeker.util.loadImage
import kotlinx.android.synthetic.main.item_book.view.*

class BookListAdapter(var books: ArrayList<Book>): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    fun updateBooks(newBooks: List<Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
    )

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    class BookViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView = view.imageView
        private val bookName = view.book_name
        private val author = view.author
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(book: Book){
            bookName.text = book.name
            author.text = book.author
            imageView.loadImage(book.image1100, progressDrawable)
        }
    }
}