package com.example.siperpus.buku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siperpus.R
import com.example.siperpus.adapter.StoriesAdapter
import com.example.siperpus.databinding.ActivityListBukuBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class ListBukuActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityListBukuBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel.getListBook()
        viewModel.dataBook.observe(this){listBook->
            if (listBook != null) {
                setStoriesData(listBook)
            }
        }
        binding.btnTambahB.setOnClickListener(this)
    }
    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.listBook.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listBook.addItemDecoration(itemDecoration)
    }
    private fun setStoriesData(userData: List<DataItem>) {
        val adapter = StoriesAdapter()
        adapter.submitList(userData)
        binding.listBook.adapter = adapter
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
}

