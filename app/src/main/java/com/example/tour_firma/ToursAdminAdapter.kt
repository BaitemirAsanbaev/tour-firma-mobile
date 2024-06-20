package com.example.tour_firma

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToursAdminAdapter(
    private var tours: List<Tour>,
    var context:Context
)
    :RecyclerView.Adapter<ToursAdminAdapter.TourViewHolder>() {
    class TourViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title:TextView = view.findViewById(R.id.admin_card_title)
        val btn:Button = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tour_admin_card, parent, false)
        return TourViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tours.count()
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        holder.title.text = tours[position].title
        val dbHelper = DBHelper(context, null)

        holder.btn.setOnClickListener{
            dbHelper.deleteTour(tours[position].id)
        }
    }
}