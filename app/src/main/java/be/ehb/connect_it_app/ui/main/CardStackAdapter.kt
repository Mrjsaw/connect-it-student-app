package be.ehb.connect_it_app.ui.main

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import be.ehb.connect_it_app.R
import be.ehb.connect_it_app.data.model.Vacancy
import com.bumptech.glide.Glide

class CardStackAdapter (
    private var vacancies: List<Vacancy> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.name.text =vacancy.name
        holder.city.text = vacancy.city
        var draw = R.drawable.pngegg
        if (position == 1) {
            draw = R.drawable.temp_2
        }
        if (position == 2) {
            draw = R.drawable.temp_3
        }
        Glide.with(holder.image)
            .load(draw)
            .into(holder.image)
        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v.context, vacancy.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

    fun setVacancies(vacancies: List<Vacancy>) {
        this.vacancies = vacancies
    }

    fun getVacancies(): List<Vacancy> {
        return vacancies
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.company_name)
        var city: TextView = view.findViewById(R.id.vacancy_address)
        var image: ImageView = view.findViewById(R.id.company_logo)
    }
}