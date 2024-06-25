package com.example.siperpus.buku

import com.example.siperpus.config.ApiService
import okhttp3.MultipartBody

class Repository(private val apiService: ApiService) {

    suspend fun addBook(requestBody: MultipartBody): ResponseStore {
        // Ambil setiap bagian dari requestBody dan konversikan menjadi MultipartBody.Part
        val book_title = requestBody.part(0)
        val author = requestBody.part(1)
        val release_date = requestBody.part(2)
        val book_pict = requestBody.part(3)

        val response = apiService.addBook(book_title, author, release_date, book_pict)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Failed to add book")
        }
    }
}
