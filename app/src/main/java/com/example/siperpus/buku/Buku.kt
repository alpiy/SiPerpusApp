package com.example.siperpus.buku

import android.net.Uri
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type
import java.util.Date


data class Buku(
    @SerializedName("id_book")
    val id_book : Int,
    @SerializedName("book_title")
    val book_title  : String?,
    @SerializedName("author")
    val author  : String,
    @SerializedName("release_date")
    val release_date  : String?
//    @SerializedName("book_pict")
//    val imageUri    : Uri
)


