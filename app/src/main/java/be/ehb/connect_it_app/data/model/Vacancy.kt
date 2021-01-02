package be.ehb.connect_it_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy_table")
data class Vacancy(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val companyDescription: String?,
        val companyName: String?,
        val duration: String?,
        val jobDescription: String?,
        val location: String?,
        val title: String?,
        val yourProfile: String?
)
