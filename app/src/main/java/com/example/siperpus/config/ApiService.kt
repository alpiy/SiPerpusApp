package com.example.siperpus.config

import retrofit2.Call
import com.example.siperpus.buku.Buku
import com.example.siperpus.buku.Response
import com.example.siperpus.member.Member
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("api/get-all-book")
    fun getBuku(): Call<Response>

    @GET("users")
    fun getMember(): Call<ArrayList<Member>>
}
fun createRetrofitService(): ApiService {
    val gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<Response>() {}.type, MyModelDeserializer())
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://siperpus-production.up.railway.app/")  // Ganti dengan URL endpoint API Anda
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    return retrofit.create(ApiService::class.java)
}