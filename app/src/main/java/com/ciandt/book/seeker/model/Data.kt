package com.ciandt.book.seeker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "book")
data class Book(
    @PrimaryKey
    @SerializedName("trackName")
    val name: String,
    @SerializedName("artistName")
    val author: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("artworkUrl60")
    val image160: String,
    @SerializedName("artworkUrl100")
    val image1100: String
)

data class ApiResponse(
    @SerializedName("results")
    val results: List<Book>,
    @SerializedName("resultCount")
    val resultCount: Int
)
