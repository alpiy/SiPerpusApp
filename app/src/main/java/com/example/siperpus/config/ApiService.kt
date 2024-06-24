package com.example.siperpus.config

import retrofit2.Call
import com.example.siperpus.buku.Response
import com.example.siperpus.member.ResponseMember
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("api/get-all-book")
    fun getBuku(): Call<Response>

    @GET("api/get-all-member")
    fun getMember(): Call<ResponseMember>
}