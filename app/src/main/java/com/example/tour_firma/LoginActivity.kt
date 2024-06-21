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

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val goRegister: TextView = findViewById(R.id.go_register)
        goRegister.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val userEmail: EditText = findViewById(R.id.email_inp_login)
        val userPassword: EditText = findViewById(R.id.password_inp_login)
        val button: Button = findViewById(R.id.login_button)

        button.setOnClickListener{
            val email = userEmail.text.toString().trim()
            val pass = userPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show()
            } else {
                val db = DBHelper(this, null)
                val isAuth = db.getUser(email, pass)
                if (isAuth) {
                    Toast.makeText(this, "You have logged in successfully", Toast.LENGTH_LONG).show()
                    userEmail.text.clear()
                    userPassword.text.clear()

                    val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putBoolean("is_logged_in", true)
                        apply()
                    }

                    val userIntent = Intent(this, ToursActivity::class.java)
                    val adminIntent = Intent(this, CreateTourActivity::class.java)
                    val atIndex = email.indexOf('@')
                    val domain = email.substring(atIndex + 1)
                    val dotIndex = domain.indexOf('.')
                    val provider = domain.substring(0, dotIndex)
                    if(provider=="admin"){
                        startActivity(adminIntent)
                    } else {
                        startActivity(userIntent)
                    }
                } else {
                    Toast.makeText(this, "Error while login", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
