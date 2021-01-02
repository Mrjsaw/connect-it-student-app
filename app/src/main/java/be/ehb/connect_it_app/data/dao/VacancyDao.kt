package be.ehb.connect_it_app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import be.ehb.connect_it_app.data.model.Vacancy

@Dao
interface VacancyDao {
     @Insert
     fun insertAll(vararg vacancies : Vacancy)

     @Query("SELECT * FROM vacancy_table")
     fun getAll() : LiveData<List<Vacancy>>

     @Delete
     fun deleteVacancies(vararg vacancies: Vacancy)


     @Update
     fun updateVacancies(vararg vacancies: Vacancy)

}