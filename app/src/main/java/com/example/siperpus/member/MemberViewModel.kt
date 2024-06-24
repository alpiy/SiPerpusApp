package com.example.siperpus.member

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siperpus.config.ApiClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class MemberViewModel: ViewModel() {

    private val _dataMember = MutableLiveData<List<DataMember>?>()
    val dataMember : LiveData<List<DataMember>?> = _dataMember

    fun getListMember(){
        viewModelScope.launch {
            val client = ApiClient.getApiService().getMember()
            client.enqueue(object : Callback<ResponseMember> {
                override fun onResponse(
                    call: Call<ResponseMember>,
                    response: retrofit2.Response<ResponseMember>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _dataMember.value = responseBody.data as List<DataMember>?
                        }
                    } else {
                        Log.e("TAG", "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<ResponseMember>, t: Throwable) {

                    Log.e("TAG", "onFailure: ${t.message}")
                }
            })
        }
    }
}