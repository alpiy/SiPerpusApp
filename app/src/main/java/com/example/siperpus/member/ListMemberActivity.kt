package com.example.siperpus.member
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.siperpus.R
import com.example.siperpus.buku.UpdateAddBukuActivity
import com.example.siperpus.databinding.ActivityListBukuBinding
import com.example.siperpus.databinding.ActivityListMemberBinding
import dagger.hilt.android.AndroidEntryPoint


class ListMemberActivity : AppCompatActivity(), OnClickListener{
    private lateinit var binding : ActivityListMemberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddM.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.getId()){
            R.id.btnAdd_m -> {
                val intentMem = Intent(this@ListMemberActivity, UpdateAddMemberActivity::class.java)
                startActivity(intentMem)
            }
        }
    }
}