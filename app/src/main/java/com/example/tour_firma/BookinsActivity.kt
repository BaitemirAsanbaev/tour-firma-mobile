package com.example.tour_firma

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookinsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bookins)

        val dbHelper = DBHelper(this, null)
        val bookingsList: RecyclerView = findViewById(R.id.bookings_list)
        val bookings = dbHelper.getAllBookings()

        bookingsList.layoutManager = LinearLayoutManager(this)
        bookingsList.adapter = BookingsAdapter(bookings, this)
    }
}