package com.example.siperpus.activity

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.siperpus.R
import com.example.siperpus.config.Constant
import com.example.siperpus.config.NetworkConfig
import com.example.siperpus.config.SharedPrafManager
import com.example.siperpus.databinding.ActivityLoginBinding
import com.example.siperpus.model.ResultStatus
import okhttp3.Call
import okhttp3.Response
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {
    lateinit var SharedPrafManager: SharedPrafManager
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SharedPrafManager = SharedPrafManager(this)
        binding.loginButton.setOnClickListener {
            MulaiLogin()
        }
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrafManager.getBoolean(Constant.PREF_IS_LOGIN)) {
            moveIntent()
        }
    }

    private fun MulaiLogin() {
        val username = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()
        if (username.isNotEmpty() && password.isNotEmpty()) {
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
            Toast.makeText(applicationContext, "Berhasil Login", Toast.LENGTH_SHORT).show()
            saveSession(username, password)
            moveIntent()
        } else {
            Toast.makeText(applicationContext, "Username dan Password Harus Diisi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveSession(username: String, password: String) {
        SharedPrafManager.put(Constant.PREF_USERNAME, username)
        SharedPrafManager.put(Constant.PREF_PASSWORD, password)
        SharedPrafManager.put(Constant.PREF_IS_LOGIN, true)
    }

    private fun moveIntent() {
        startActivity(Intent(this, HomepageActivity::class.java))
    }
}
