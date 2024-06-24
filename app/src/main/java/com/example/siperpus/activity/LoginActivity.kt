package com.example.siperpus.activity

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.siperpus.R
import com.example.siperpus.config.Constant
import com.example.siperpus.config.NetworkConfig
import com.example.siperpus.config.SharedPrefManager
import com.example.siperpus.databinding.ActivityLoginBinding
import com.example.siperpus.model.ResultStatus
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Call
import okhttp3.Response
import javax.security.auth.callback.Callback
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    //    lateinit var SharedPrafManager: SharedPrafManager
//    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Login"
        HomepageActivity.auth = FirebaseAuth.getInstance()

        if (HomepageActivity.auth!!.currentUser == null){
        } else {
            intent = Intent(applicationContext, HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }

//        SharedPrafManager = SharedPrafManager(this)
        binding.loginButton.setOnClickListener {
            val email = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                HomepageActivity.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, HomepageActivity::class.java)
                            startActivity(intent)
                            saveSession(email, password)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Authentication failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, "TULUNGG LEBOKNE", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun saveSession(email: String, password: String) {
        SharedPrefManager.put(Constant.PREF_EMAIL, email)
        SharedPrefManager.put(Constant.PREF_PASSWORD, password)
        SharedPrefManager.put(Constant.PREF_IS_LOGIN, true)
    }
}
//