package be.ehb.connect_it_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.ehb.connect_it_app.data.dao.VacancyDao
import be.ehb.connect_it_app.data.model.Vacancy


@Database(entities = [Vacancy::class], version = 1, exportSchema = false)
public abstract class VacancyRoomDb : RoomDatabase() {

    abstract fun vacancyDao(): VacancyDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: VacancyRoomDb? = null

        fun getDatabase(context: Context): VacancyRoomDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VacancyRoomDb::class.java,
                    "vacancy_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
