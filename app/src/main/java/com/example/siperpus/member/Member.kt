package com.example.siperpus.member

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class Member(
    val id_member : Int,
    val fullname : String?,
    val address : String?,
    val gender : String?,
    @SerializedName("profile_pict")
    val profile : Uri



)
