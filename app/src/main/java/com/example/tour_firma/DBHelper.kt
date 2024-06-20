package com.example.tour_firma

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "tour_firma", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val usersQ = buildString {
            append("CREATE TABLE users (")
            append("'id' INTEGER PRIMARY KEY AUTOINCREMENT,")
            append("'email' TEXT,")
            append("'pass' TEXT)")
        }

        val toursQ = buildString {
            append("CREATE TABLE tours (")
            append("'id' INTEGER PRIMARY KEY AUTOINCREMENT,")
            append("'image' TEXT,")
            append("'title' TEXT,")
            append("'desc' TEXT,")
            append("'info' TEXT,")
            append("'price' INTEGER,")
            append("'destination' TEXT,")
            append("'departure' TEXT)")
        }

        val bookingsQ = buildString {
            append("CREATE TABLE bookings (")
            append("'id' INTEGER PRIMARY KEY AUTOINCREMENT, ")
            append("'user' INTEGER,")
            append("'tour' INTEGER,")
            append("FOREIGN KEY(user) REFERENCES users(id),")
            append("FOREIGN KEY(tour) REFERENCES tours(id))")
        }

        db?.execSQL(usersQ)
        db?.execSQL(toursQ)
        db?.execSQL(bookingsQ)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS bookings")
        db?.execSQL("DROP TABLE IF EXISTS tours")
        db?.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("email", user.email)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    @SuppressLint("Recycle")
    fun getUser(email: String, pass: String): Boolean {
        val db = this.readableDatabase
        val result =
            db.rawQuery("SELECT * FROM users WHERE email = '$email' AND pass = '$pass'", null)
        return result.moveToFirst()
    }

    fun addTour(tour: Tour) {
        val values = ContentValues().apply {
            put("image", tour.image)
            put("title", tour.title)
            put("desc", tour.desc)
            put("info", tour.info)
            put("price", tour.price)
            put("destination", tour.destination)
            put("departure", tour.departure)
        }

        val db = this.writableDatabase
        db.insert("tours", null, values)
        db.close()
    }
    fun deleteTour(id: Int) {
        val db = this.writableDatabase
        db.execSQL("DELETE * FROM tours WHERE (id=$id)")
        db.close()
    }
    @SuppressLint("Range")
    fun getAllTours(): List<Tour> {
        val tours = mutableListOf<Tour>()
        val query = "SELECT * FROM tours"
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            // Exception handling if needed
            db.close()
            return tours
        }

        var id: Int
        var image: String
        var title: String
        var desc: String
        var info: String
        var price: Int
        var destination: String
        var departure: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                image = cursor.getString(cursor.getColumnIndex("image"))
                title = cursor.getString(cursor.getColumnIndex("title"))
                desc = cursor.getString(cursor.getColumnIndex("desc"))
                info = cursor.getString(cursor.getColumnIndex("info"))
                price = cursor.getInt(cursor.getColumnIndex("price"))
                destination = cursor.getString(cursor.getColumnIndex("destination"))
                departure = cursor.getString(cursor.getColumnIndex("departure"))

                val tour = Tour(id, image, title, desc, info, price, destination, departure)
                tours.add(tour)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return tours
    }



}
