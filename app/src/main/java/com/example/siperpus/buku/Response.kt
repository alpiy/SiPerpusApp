		package com.example.siperpus.buku

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("book_title")
	val bookTitle: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("isbn")
	val isbn: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("book_pict")
	val bookPict: String? = null
)
		data class ResponseStore(

			@field:SerializedName("data")
			val data: List<DataItem?>? = null,

			@field:SerializedName("message")
			val message: String? = null,

			@field:SerializedName("status")
			val status: Boolean? = null
		)
		data class DataItemStore(

			@field:SerializedName("updated_at")
			val updatedAt: String? = null,

			@field:SerializedName("book_title")
			val bookTitle: String? = null,

			@field:SerializedName("release_date")
			val releaseDate: String? = null,

			@field:SerializedName("author")
			val author: String? = null,

			@field:SerializedName("isbn")
			val isbn: String? = null,

			@field:SerializedName("created_at")
			val createdAt: String? = null,

			@field:SerializedName("id")
			val id: Int? = null,

			@field:SerializedName("book_pict")
			val bookPict: String? = null
		)
