package com.example.siperpus.config



import retrofit2.Call
import com.example.siperpus.buku.Response
import com.example.siperpus.buku.ResponseStore
import com.example.siperpus.member.ResponseMember
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("api/get-all-book")
    fun getBuku(): Call<Response>

    @GET("api/get-all-member")
    fun getMember(): Call<ResponseMember>
    @Multipart
    @POST("api/add-book")
    suspend fun addBook(
        @Part book_title: MultipartBody.Part,
        @Part author: MultipartBody.Part,
        @Part release_date: MultipartBody.Part,
        @Part book_pict: MultipartBody.Part
    ): retrofit2.Response<ResponseStore>
}