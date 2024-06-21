package com.example.tour_firma

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

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
            append("'id' INTEGER PRIMARY KEY AUTOINCREMENT,")
            append("'tour' TEXT)")
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
        val query = "SELECT * FROM users WHERE email = ? AND pass = ?"
        val result = db.rawQuery(query, arrayOf(email, pass))
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
        db.execSQL("DELETE FROM tours WHERE id = $id")
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
            Log.e("DBHelper", "Error reading tours", e)
            db.close()
            return tours
        }

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val image = cursor.getString(cursor.getColumnIndex("image"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val desc = cursor.getString(cursor.getColumnIndex("desc"))
                val info = cursor.getString(cursor.getColumnIndex("info"))
                val price = cursor.getInt(cursor.getColumnIndex("price"))
                val destination = cursor.getString(cursor.getColumnIndex("destination"))
                val departure = cursor.getString(cursor.getColumnIndex("departure"))

                val tour = Tour(id, image, title, desc, info, price, destination, departure)
                tours.add(tour)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return tours
    }

    fun addBooking(booking: Booking) {
        val values = ContentValues().apply {
            put("tour", booking.tour)
        }

        val db = this.writableDatabase
        db.insert("bookings", null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllBookings(): List<Booking> {
        val bookings = mutableListOf<Booking>()
        val query = "SELECT * FROM bookings"
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            Log.e("DBHelper", "Error reading bookings", e)
            db.close()
            return bookings
        }

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val tour = cursor.getString(cursor.getColumnIndex("tour"))

                val booking = Booking(id, tour)
                bookings.add(booking)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return bookings
    }
}
