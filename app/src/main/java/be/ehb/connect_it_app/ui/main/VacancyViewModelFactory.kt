package be.ehb.connect_it_app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.ehb.connect_it_app.data.model.VacancyRepository

class VacancyViewModelFactory(private val repository: VacancyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VacancyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VacancyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

