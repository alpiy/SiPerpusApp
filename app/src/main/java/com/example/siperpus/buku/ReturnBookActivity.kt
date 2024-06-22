package com.example.siperpus.buku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.siperpus.R
import com.example.siperpus.databinding.ActivityRentBookBinding
import com.example.siperpus.databinding.ActivityReturnBookBinding

class ReturnBookActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding : ActivityReturnBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReturnBookBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onClick(v: View?) {
        when(v?.getId()){

        }
    }
}