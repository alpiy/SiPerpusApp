package com.example.siperpus.buku

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siperpus.R
import com.example.siperpus.adapter.BukuAdapter
import com.example.siperpus.config.ApiService
import com.example.siperpus.config.NetworkConfig
import com.example.siperpus.databinding.ActivityListBukuBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class ListBukuActivity : AppCompatActivity(), OnClickListener{
    private lateinit var binding : ActivityListBukuBinding

    lateinit var apiService: ApiService
    private lateinit var recyclerView: RecyclerView
//    private val ADD_BUKU_REQUEST = 1
//    private  val bukuList = mutableListOf<Buku>()
//    private lateinit var bukuAdapter: BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        loadBukuData()

        binding.btnTambahB.setOnClickListener(this)
        recyclerView = findViewById(R.id.listBook)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchBooks()
//        binding.listBook.setOnClickListener(this)
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
    private fun fetchBooks() {
        val call = apiService.getBuku()
        call.enqueue(object : Callback<List<Buku>> {
            override fun onResponse(call: Call<List<Buku>>, response: Response<List<Buku>>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@ListBukuActivity, "Code: ${response.code()}", Toast.LENGTH_SHORT).show()
                    return
                }

                val books = response.body()!!
                recyclerView.adapter = BukuAdapter(books)
            }

            override fun onFailure(call: Call<List<Buku>>, t: Throwable) {
                Toast.makeText(this@ListBukuActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == ADD_BUKU_REQUEST && resultCode == Activity.RESULT_OK) {
//            if (data != null) {
//                val judulBuku = data.getStringExtra("judulBuku")
//                val namaAuthor = data.getStringExtra("namaAuthor")
//                val tahunRilis = data.getStringExtra("tahunRilis")
//                val imageUriString = data.getStringExtra("imageUri")
//                val imageUri = Uri.parse(imageUriString)
//
//                val buku = Buku(judulBuku!!, namaAuthor!!, tahunRilis!!, imageUri)
//                bukuList.add(buku)
//                bukuAdapter.notifyItemInserted(bukuList.size - 1)
//            }
//        }
//    }

//    private fun displayBukuData(bukuList: List<Buku>) {
//        val listBukuLayout = findViewById<LinearLayout>(R.id.listBook)
//
//        for (buku in bukuList) {
//            val bukuView = layoutInflater.inflate(R.layout.list_book, null)
//
//            val textViewJudul = bukuView.findViewById<TextView>(R.id.tittle)
//            val textViewAuthor = bukuView.findViewById<TextView>(R.id.author)
//            val textViewTahun = bukuView.findViewById<TextView>(R.id.date)
//            val imageViewCover = bukuView.findViewById<ImageView>(R.id.cover_book)
//
//            textViewJudul.text = buku.judul
//            textViewAuthor.text = buku.author
//            textViewTahun.text = buku.tahunRilis
//            Picasso.get().load(buku.imageUri).into(imageViewCover)
//
//            listBukuLayout.addView(bukuView)
//        }
//    }

