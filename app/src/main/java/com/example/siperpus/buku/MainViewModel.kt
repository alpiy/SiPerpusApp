package com.example.siperpus.buku

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import com.example.siperpus.config.ApiClient
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _dataBook = MutableLiveData<List<DataItem>?>()
    val dataBook: LiveData<List<DataItem>?> = _dataBook

    private val _addBookResponse = MutableLiveData<ResponseStore?>()
    val addBookResponse: LiveData<ResponseStore?>get()  = _addBookResponse

    fun getListBook() {
        viewModelScope.launch {
            val client = ApiClient.getApiService().getBuku()
            client.enqueue(object : Callback<com.example.siperpus.buku.Response> {
                override fun onResponse(
                    call: Call<com.example.siperpus.buku.Response>,
                    response: Response<com.example.siperpus.buku.Response>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _dataBook.value = responseBody.data as List<DataItem>?
                        }
                    } else {
                        Log.e("TAG", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(
                    call: Call<com.example.siperpus.buku.Response>,
                    t: Throwable
                ) {

                    Log.e("TAG", "onFailure: ${t.message}")
                }
            })
        }
    }

    fun addBook(judul: String,
                author: String,
                tahun: String,
                imageUri: Uri,
                contentResolver: ContentResolver) {
        viewModelScope.launch {
            try {
                // Lakukan proses untuk mengambil konten dari URI ke dalam bentuk file
                val inputStream = contentResolver.openInputStream(imageUri)
                // Buat bagian multipart dari file yang akan diunggah
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("book_title", judul)
                    .addFormDataPart("author", author)
                    .addFormDataPart("release_date", tahun)
                    .addFormDataPart(
                        "book_pict",
                        "",
                        inputStream!!.readBytes().toRequestBody("image/*".toMediaType())
                    )
                    .build()
                val response = repository.addBook(requestBody)
                _addBookResponse.value = response
            } catch (e: Exception) {
                _addBookResponse.value = null
                Log.e("MainViewModel", "Error adding book", e)
            }
        }
    }
}


