package com.example.tour_firma

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookingsAdapter(
    private var bookings: List<Booking>,
    var context:Context
)
    :RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>() {
    class BookingViewHolder(view:View):RecyclerView.ViewHolder(view){
        val booking:TextView = view.findViewById(R.id.booking_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.booking_item, parent, false)
        return BookingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookings.count()
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.booking.text = bookings[position].tour


    }
}