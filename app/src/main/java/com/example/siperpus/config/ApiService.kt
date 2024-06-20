package com.example.siperpus.config

import com.example.siperpus.buku.Buku
import retrofit2.http.GET

interface ApiService {
    @GET("api/buku")
    suspend fun getBuku(): List<Buku>
}