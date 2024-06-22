package com.example.siperpus.buku

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.siperpus.R
import com.example.siperpus.databinding.ActivityListBukuBinding
import com.example.siperpus.databinding.ActivityRentBookBinding

class RentBookActivity: AppCompatActivity(), OnClickListener {
    private lateinit var binding : ActivityRentBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRentBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddRbn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.getId()){
            R.id.btnAdd_rbn -> {
                val intentUpdate = Intent(this@RentBookActivity, UpdateAddRentBook::class.java)
                startActivity(intentUpdate)
            }
        }
    }
}