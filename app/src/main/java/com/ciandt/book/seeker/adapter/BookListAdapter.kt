package com.ciandt.book.seeker.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.dao.BookServiceDAO
import com.ciandt.book.seeker.model.Book
import com.ciandt.book.seeker.util.getProgressDrawable
import com.ciandt.book.seeker.util.loadImage
import com.ciandt.book.seeker.view.BookDetailsView
import kotlinx.android.synthetic.main.item_book.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BookListAdapter(var books: ArrayList<Book>) :
    RecyclerView.Adapter<BookListAdapter.BookViewHolder> () {

    fun updateBooks(newBooks: List<Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        )

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val bookCardView = view.book_card_view
        private val imageView = view.imageView
        private val bookName = view.book_name
        private val author = view.author
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(book: Book) {
            bookName.text = book.name
            author.text = book.author
            imageView.loadImage(book.image1100, progressDrawable)
            bookCardView.setOnClickListener { onClick(it, book) }
        }

        private fun onClick(view: View, book: Book) {

            doAsync {
                val saving: String = BookServiceDAO.save(book)
                uiThread {
                    val context = view.context
                    val intent = Intent(context, BookDetailsView::class.java)
                    intent.putExtra("name", book.name)
                    context.startActivity(intent)
                }
            }
        }
    }
}
