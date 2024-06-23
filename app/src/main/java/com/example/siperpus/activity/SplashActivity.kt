package com.example.siperpus.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.siperpus.R
import dagger.hilt.android.AndroidEntryPoint


class SplashActivity : AppCompatActivity() {
    private val splashScreentimeout : Long = 2500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashScreentimeout)
    }
}