package com.example.siperpus.buku

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

class MainViewModel: ViewModel() {

    private val _dataBook = MutableLiveData<List<DataItem>?>()
    val dataBook : LiveData<List<DataItem>?> = _dataBook

    fun getListBook(){
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
                override fun onFailure(call: Call<com.example.siperpus.buku.Response>, t: Throwable) {

                    Log.e("TAG", "onFailure: ${t.message}")
                }
            })
        }
    }
}