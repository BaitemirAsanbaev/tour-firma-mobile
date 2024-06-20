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

class ToursAdapter(
    private var tours: List<Tour>,
    var context:Context
)
    :RecyclerView.Adapter<ToursAdapter.TourViewHolder>() {
    class TourViewHolder(view:View):RecyclerView.ViewHolder(view){
        val image:ImageView = view.findViewById(R.id.tour_card_image)
        val title:TextView = view.findViewById(R.id.tour_card_title)
        val desc:TextView = view.findViewById(R.id.tour_card_desc)
        val price:TextView = view.findViewById(R.id.tour_card_price)
        val btn:Button = view.findViewById(R.id.tour_card_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tour_card, parent, false)
        return TourViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tours.count()
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        holder.title.text = tours[position].title
        holder.desc.text = tours[position].desc
        holder.price.text = tours[position].price.toString()

        val imageId = context.resources.getIdentifier(
                tours[position].image,
                "drawable",
                context.packageName
                )

        holder.image.setImageResource(imageId)

        holder.btn.setOnClickListener{
            val intent = Intent(context, TourDetailsActivity::class.java)
            intent.putExtra("title", tours[position].title)
            intent.putExtra("desc", tours[position].desc)
            context.startActivity(intent)
        }
    }
}