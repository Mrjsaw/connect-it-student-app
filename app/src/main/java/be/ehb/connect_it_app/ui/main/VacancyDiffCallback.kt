package be.ehb.connect_it_app.ui.main

import androidx.recyclerview.widget.DiffUtil
import be.ehb.connect_it_app.data.model.Vacancy

class VacancyDiffCallback (
    private val old: List<Vacancy>,
    private val new: List<Vacancy>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}