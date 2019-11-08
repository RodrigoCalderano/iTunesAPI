package com.ciandt.book.seeker.model

import com.google.gson.annotations.SerializedName

data class Book(
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