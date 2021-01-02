package be.ehb.connect_it_app.data

import android.app.Application
import be.ehb.connect_it_app.data.model.VacancyRepository

class VacancyApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { VacancyRoomDb.getDatabase(this) }
    val repository by lazy { VacancyRepository(database.vacancyDao()) }

}