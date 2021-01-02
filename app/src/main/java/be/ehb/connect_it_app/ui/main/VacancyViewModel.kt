package be.ehb.connect_it_app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.ehb.connect_it_app.data.model.Vacancy
import be.ehb.connect_it_app.data.model.VacancyRepository
import kotlinx.coroutines.launch

class VacancyViewModel(private val repository: VacancyRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Vacancy>> = repository.allVacancies

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(vacancy: Vacancy) = viewModelScope.launch {
        repository.insert(vacancy)
    }
}
