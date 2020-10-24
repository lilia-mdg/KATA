package tn.thinkit.challenge.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tn.thinkit.challenge.data.repositories.HomeRepository
import tn.thinkit.challenge.ui.viewmodels.HomeViewModel

class HomeViewModelFactory(private val homeRepository: HomeRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeRepository) as T
    }
}