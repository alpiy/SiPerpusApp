package com.example.siperpus.buku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.siperpus.R
import com.example.siperpus.adapter.BukuAdapter
import com.example.siperpus.config.ApiClient
import com.example.siperpus.config.createRetrofitService
import com.example.siperpus.databinding.ActivityListBukuBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class ListBukuActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityListBukuBinding
    private lateinit var adapter: BukuAdapter
    private val apiService by lazy { createRetrofitService() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = BukuAdapter(this@ListBukuActivity, arrayListOf())

        binding.listBook.adapter = adapter
        binding.listBook.setHasFixedSize(true)
        remoteGetBook()
//            fetchBuku()


        binding.btnTambahB.setOnClickListener(this)
//        recyclerView = findViewById(R.id.listBook)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        fetchBooks()
////        binding.listBook.setOnClickListener(this)
//        val recyclerViewBuku: RecyclerView = findViewById(R.id.listBook)
//        recyclerViewBuku.layoutManager = LinearLayoutManager(this)
//        bukuAdapter = BukuAdapter(bukuList)
//        recyclerViewBuku.adapter = bukuAdapter

    }

    override fun onClick(v: View?) {
        when (v?.getId()) {
            R.id.btnTambah_b -> {
                val intentUpdate =
                    Intent(this@ListBukuActivity, UpdateAddBukuActivity::class.java)
                startActivity(intentUpdate)
            }
        }
    }

    fun remoteGetBook() {
        ApiClient.apiService.getBuku().enqueue(object : Callback<ArrayList<Buku>> {
            override fun onResponse(
                call: Call<ArrayList<Buku>>,
                response: Response<ArrayList<Buku>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    setDatatoAdapter(data!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<Buku>>, t: Throwable) {
                Log.d("Errorr", "" + t.stackTraceToString())
            }

        })
    }

    fun setDatatoAdapter(data: List<Buku>) {
        adapter.setData(data)
    }

    //
    private fun fetchBuku() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val buku = apiService.getBuku()
                withContext(Dispatchers.Main) {
                    // Update UI dengan data yang diterima
                    println("Items: $buku")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


//    private fun loadBukuData() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = NetworkConfig().instance.getBuku()
//                withContext(Dispatchers.Main) {
//                    displayBukuData(response)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
