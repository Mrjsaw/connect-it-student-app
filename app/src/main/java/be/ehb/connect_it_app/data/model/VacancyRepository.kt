package be.ehb.connect_it_app.data.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import be.ehb.connect_it_app.data.VacancyRoomDb
import be.ehb.connect_it_app.data.dao.VacancyDao
import java.util.concurrent.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class VacancyRepository(private val vacancyDao: VacancyDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allVacancies: LiveData<List<Vacancy>> = vacancyDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(vacancy: Vacancy) {
        vacancyDao.insertAll(vacancy)
    }
}
