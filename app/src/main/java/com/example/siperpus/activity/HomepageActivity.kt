package com.example.siperpus.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.example.siperpus.R
import com.example.siperpus.buku.ListBukuActivity
import com.example.siperpus.buku.RentBookActivity
import com.example.siperpus.buku.ReturnBookActivity
import com.example.siperpus.config.SharedPrafManager
import com.example.siperpus.databinding.ActivityHomepageBinding
import com.example.siperpus.member.ListMemberActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


class HomepageActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityHomepageBinding
    companion object{
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.manageBuku.setOnClickListener(this)
        binding.returnBuku.setOnClickListener(this)
        binding.rentBuku.setOnClickListener(this)
        binding.manageMember.setOnClickListener(this)

    }

    override fun onClick(hm: View?) {
        when (hm?.getId()) {
            R.id.manage_buku -> {
                val intentBook = Intent(this@HomepageActivity, ListBukuActivity::class.java)
                startActivity(intentBook)
            }

            R.id.return_buku -> {
                val intentReturn = Intent(this@HomepageActivity, ReturnBookActivity::class.java)
                startActivity(intentReturn)
            }

            R.id.rent_buku -> {
                val intentRent = Intent(this@HomepageActivity, RentBookActivity::class.java)
                startActivity(intentRent)
            }

            R.id.manage_member -> {
                val intentMember = Intent(this@HomepageActivity, ListMemberActivity::class.java)
                startActivity(intentMember)
            }

        }

    }
}