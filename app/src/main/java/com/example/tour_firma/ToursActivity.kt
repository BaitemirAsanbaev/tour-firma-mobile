package com.example.tour_firma

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ToursActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tours)

        val dbHelper = DBHelper(this, null)
        val toursList: RecyclerView = findViewById(R.id.tours_list)
        val btn: Button = findViewById(R.id.go_bookings)
        val tours = dbHelper.getAllTours()
        val insta:ImageView = findViewById(R.id.instagram_icon)
        val telega:ImageView = findViewById(R.id.telegram_icon)
        val facebook:ImageView = findViewById(R.id.facebook_icon)
        insta.setOnClickListener {
            openInstagram()
        }

        telega.setOnClickListener {
            openTelegram()
        }

        facebook.setOnClickListener {
            openFacebook()
        }
        btn.setOnClickListener{
            val intent = Intent(this, BookinsActivity::class.java)
            startActivity(intent)
        }
        toursList.layoutManager = LinearLayoutManager(this)
        toursList.adapter = ToursAdapter(tours, this)
    }
    private fun openInstagram() {
        val uri = Uri.parse("https://www.instagram.com/") // Replace with your Instagram URL
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openTelegram() {
        val uri = Uri.parse("https://telegram.me/") // Replace with your Telegram URL
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openFacebook() {
        val uri = Uri.parse("https://www.facebook.com/") // Replace with your Facebook URL
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}