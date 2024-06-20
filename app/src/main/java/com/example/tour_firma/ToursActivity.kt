package com.example.tour_firma

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ToursActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tours)

        val toursList: RecyclerView = findViewById(R.id.tours_list)
        val tours = arrayListOf<Tour>()
        tours.add(Tour(
            id = 1,
            image = "beach",
            title = "Beautiful Beach Getaway",
            desc = "Enjoy a relaxing time at a beautiful beach.",
            price = 299,
            destination = "Maldives",
            departure = "New York"
        ))

        tours.add(Tour(
            id = 2,
            image = "mountain",
            title = "Mountain Adventure",
            desc = "Experience the thrill of mountain climbing and hiking.",
            price = 499,
            destination = "Swiss Alps",
            departure = "Los Angeles"
        ))

        tours.add(Tour(
            id = 3,
            image = "europe",
            title = "Historical Europe",
            desc = "Explore the rich history of Europe's most famous cities.",
            price = 799,
            destination = "Europe",
            departure = "Chicago"
        ))

        tours.add(Tour(
            id = 4,
            image = "safari",
            title = "Safari Expedition",
            desc = "Go on a safari and see wildlife up close.",
            price = 999,
            destination = "Kenya",
            departure = "London"
        ))

        tours.add(Tour(
            id = 5,
            image = "lights",
            title = "City Lights",
            desc = "Experience the vibrant nightlife and cityscapes.",
            price = 399,
            destination = "Tokyo",
            departure = "San Francisco"
        ))
        toursList.layoutManager = LinearLayoutManager(this)
        toursList.adapter = ToursAdapter(tours, this)
    }
}