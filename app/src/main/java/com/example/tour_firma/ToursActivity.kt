package com.example.tour_firma

import android.annotation.SuppressLint
import android.os.Bundle
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
        val tours = dbHelper.getAllTours()

        toursList.layoutManager = LinearLayoutManager(this)
        toursList.adapter = ToursAdminAdapter(tours, this)
    }
}