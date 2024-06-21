package com.example.tour_firma

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdminToursActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_tours)

        val dbHelper = DBHelper(this, null)
        val toursList: RecyclerView = findViewById(R.id.tours_list_admin)
        val tours = dbHelper.getAllTours()
        val buttonGoCreate:Button = findViewById(R.id.go_create)

        buttonGoCreate.setOnClickListener{
            val intent = Intent(this, CreateTourActivity::class.java)
            startActivity(intent)
        }
        toursList.layoutManager = LinearLayoutManager(this)
        toursList.adapter = ToursAdminAdapter(tours, this)
    }
}