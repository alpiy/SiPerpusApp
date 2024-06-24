package com.example.siperpus.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import com.example.siperpus.R
import com.example.siperpus.buku.ListBukuActivity
import com.example.siperpus.buku.RentBookActivity
import com.example.siperpus.buku.ReturnBookActivity
import com.example.siperpus.config.Constant
import com.example.siperpus.config.SharedPrefManager

import com.example.siperpus.databinding.ActivityHomepageBinding
import com.example.siperpus.member.ListMemberActivity
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val isLoggedIn = SharedPrefManager.getBoolean(Constant.PREF_IS_LOGIN)

        binding.manageBuku.setOnClickListener(this)
        binding.returnBuku.setOnClickListener(this)
        binding.rentBuku.setOnClickListener(this)
        binding.manageMember.setOnClickListener(this)
        binding.logout.setOnClickListener(this)

        if (!isLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // User is logged in, proceed with homepage functionality
        }


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

            R.id.logout -> { // Statement program untuk logout/keluar
                AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(object  : OnCompleteListener<Void> {
                        override fun onComplete(p0: Task<Void>){
                            Toast.makeText(this@HomepageActivity, "Logout Berhasil",
                                Toast.LENGTH_SHORT).show()
                            intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
            }

        }

    }
}