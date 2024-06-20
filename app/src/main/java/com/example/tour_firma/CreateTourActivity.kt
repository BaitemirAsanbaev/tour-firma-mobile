package com.example.tour_firma


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateTourActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var inputTitle: EditText
    private lateinit var inputDesc: EditText
    private lateinit var inputInfo: EditText
    private lateinit var inputPrice: EditText
    private lateinit var inputDestination: EditText
    private lateinit var inputDeparture: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonAllTours: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tour)


        inputTitle = findViewById(R.id.inputTitle)
        inputDesc = findViewById(R.id.inputDesc)
        inputInfo = findViewById(R.id.inputInfo)
        inputPrice = findViewById(R.id.inputPrice)
        inputDestination = findViewById(R.id.inputDestination)
        inputDeparture = findViewById(R.id.inputDeparture)
        buttonSave = findViewById(R.id.buttonSave)
        buttonAllTours = findViewById(R.id.go_all_tours)
        spinner= findViewById(R.id.spinner)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        buttonAllTours.setOnClickListener{
            val intent = Intent(this, AdminToursActivity::class.java)
            startActivity(intent)
        }
        buttonSave.setOnClickListener {
            saveTour()
        }
    }


    private fun saveTour() {
        val image = spinner.selectedItem.toString().trim()
        val title = inputTitle.text.toString().trim()
        val desc = inputDesc.text.toString().trim()
        val info = inputInfo.text.toString().trim()
        val price = inputPrice.text.toString().toIntOrNull()
        val destination = inputDestination.text.toString().trim()
        val departure = inputDeparture.text.toString().trim()

        if (title.isEmpty() || desc.isEmpty() || info.isEmpty() || price == null || destination.isEmpty() || departure.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }


        val dbHelper = DBHelper(this, null)

        val newTour = Tour(
            id = 0,
            image = image,
            title = title,
            desc = desc,
            info = info,
            price = price,
            destination = destination,
            departure = departure
        )

        dbHelper.addTour(newTour)
        Toast.makeText(this, "Tour added successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, AdminToursActivity::class.java)
        startActivity(intent)
    }
}
