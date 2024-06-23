package com.example.siperpus.model

import dagger.hilt.android.AndroidEntryPoint


class ResultStatus(
    val message: String,
    val status: Int,
    val error: Boolean
)