package com.example.tour_firma

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val goRegister: TextView = findViewById(R.id.go_login)
        goRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val userEmail: EditText = findViewById(R.id.email_inp)
        val userPassword: EditText = findViewById(R.id.password_inp)
        val button: Button = findViewById(R.id.register_button)

        button.setOnClickListener {
            val email = userEmail.text.toString().trim()
            val pass = userPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show()
            } else {
                val user = User(email, pass)

                val db = DBHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "You have registered successfully", Toast.LENGTH_LONG).show()

                userEmail.text.clear()
                userPassword.text.clear()

                val userIntent = Intent(this, ToursActivity::class.java)
                val adminIntent = Intent(this, CreateTourActivity::class.java)
                val atIndex = email.indexOf('@')
                val domain = email.substring(atIndex + 1)
                val dotIndex = domain.indexOf('.')
                val provider = domain.substring(0, dotIndex)
                if (provider == "admin") {
                    startActivity(adminIntent)
                } else {
                    startActivity(userIntent)
                }
            }
        }
    }
}
