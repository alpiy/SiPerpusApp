package com.example.siperpus.config

import retrofit2.Call
import com.example.siperpus.buku.Buku
import retrofit2.http.GET

interface ApiService {
    @GET("api/buku")
    fun getBuku(): Call<List<Buku>>
}