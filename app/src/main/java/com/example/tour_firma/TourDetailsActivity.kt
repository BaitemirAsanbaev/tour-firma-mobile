package com.example.tour_firma

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TourDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tour_details)

        val title:TextView = findViewById(R.id.tour_title)
        val desc:TextView = findViewById(R.id.tour_desc)

        title.text = intent.getStringExtra("title")
        desc.text = intent.getStringExtra("desc")
    }
}