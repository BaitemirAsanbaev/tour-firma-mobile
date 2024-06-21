package com.example.tour_firma

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        btn.setOnClickListener{
            val intent = Intent(this, BookinsActivity::class.java)
            startActivity(intent)
        }
        toursList.layoutManager = LinearLayoutManager(this)
        toursList.adapter = ToursAdapter(tours, this)
    }
}