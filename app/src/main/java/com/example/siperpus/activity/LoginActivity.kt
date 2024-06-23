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

//        SharedPrafManager = SharedPrafManager(this)
        binding.loginButton.setOnClickListener {
            val email = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "TULUNGG LEBOKNE", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        HomepageActivity.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    saveSession(email, password)
                    val intent = Intent(this, HomepageActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveSession(email: String, password: String) {
        SharedPrefManager.put(Constant.PREF_EMAIL, email)
        SharedPrefManager.put(Constant.PREF_PASSWORD, password)
        SharedPrefManager.put(Constant.PREF_IS_LOGIN, true)
    }
}
//                        val user = HomepageActivity.auth.currentUser
//                        if (user != null) {
//                            val sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE)
//                            val editor = sharedPreferences.edit()
//                            editor.putString("user_id", user.uid)
//                            editor.apply()
//                            val Intent =Intent(this,HomepageActivity::class.java)
//                        startActivity(Intent)
//                        saveSession(email = )
//                        finish()
//                    }
//                } else {
//                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                    }
//                    }.addOnFailureListener {
//                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
//                }
//            }
//        }



//    override fun onStart() {
//        super.onStart()
//        if (SharedPrafManager.getBoolean(Constant.PREF_IS_LOGIN)) {
//            moveIntent()
//        }
//    }

//    private fun MulaiLogin() {
//        val username = binding.usernameInput.text.toString()
//        val password = binding.passwordInput.text.toString()
//        if (username.isNotEmpty() && password.isNotEmpty()) {
//            NetworkConfig().getServiceLogin().login(username, password)
//                .enqueue(object : retrofit2.Callback<ResultStatus> {
//                    override fun onResponse(call: retrofit2.Call<ResultStatus>, response: retrofit2.Response<ResultStatus>) {
//                        val response = response.body()
//                        if (response != null) {
//                            if (response.error == false) {
//                                Toast.makeText(applicationContext, response.message, Toast.LENGTH_SHORT).show()
//                                saveSession(username, password)
//                                moveIntent()
//                            }
//                        }
//                    }
//                    override fun onFailure(call: retrofit2.Call<ResultStatus>, t: Throwable) {
//                        Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
//                    }
//                })
//            Toast.makeText(applicationContext, "Berhasil Login", Toast.LENGTH_SHORT).show()
//            saveSession(username, password)
//            moveIntent()
//        } else {
//            Toast.makeText(applicationContext, "Username dan Password Harus Diisi", Toast.LENGTH_SHORT).show()
//        }
//    }



//    private fun moveIntent() {
//        startActivity(Intent(this, HomepageActivity::class.java))
//    }

