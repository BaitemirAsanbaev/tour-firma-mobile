package com.example.tour_firma

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (checkLoginStatus()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 3000) // 3 seconds delay
    }

    private fun checkLoginStatus(): Boolean {
        // Implement your login status check logic here
        // For example, check SharedPreferences or a local database
        // Example:
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        return sharedPref.getBoolean("is_logged_in", false)
    }
}
