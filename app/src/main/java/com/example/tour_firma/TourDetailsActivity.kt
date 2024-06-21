package com.example.tour_firma

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TourDetailsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tour_details)

        val title:TextView = findViewById(R.id.tour_title)
        val desc:TextView = findViewById(R.id.tour_desc)
        val info:TextView = findViewById(R.id.tour_info)
        val dest_dep:TextView = findViewById(R.id.dest_dep)
        val price:TextView = findViewById(R.id.tour_price)
        val booking_btn:Button = findViewById(R.id.tour_button)


        title.text = intent.getStringExtra("title")
        desc.text = intent.getStringExtra("desc")
        info.text = intent.getStringExtra("info")
        val dep = intent.getStringExtra("dep")
        val des = intent.getStringExtra("des")
        dest_dep.text = des.toString() + " - " + dep.toString()
        price.text = intent.getStringExtra("price")

        val dbHelper = DBHelper(this, null)
        booking_btn.setOnClickListener{
            val booking = Booking(0, title.text.toString())
            dbHelper.addBooking(booking)
            Toast.makeText(this, "Booking added successfully", Toast.LENGTH_LONG).show()
        }
    }
}