package com.example.siperpus.buku

import android.net.Uri

data class Buku(
    val id : Int,
    val judul   : String,
    val author  : String,
    val tahunRilis  : String,
    val imageUri    : Uri
)
