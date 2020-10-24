package tn.thinkit.challenge.utilities

import tn.thinkit.challenge.data.FakeDatabase
import tn.thinkit.challenge.data.repositories.HomeRepository
import tn.thinkit.challenge.ui.viewmodels.factories.HomeViewModelFactory

object InjectorUtils {
    fun provideHomeViewModelFactory(): HomeViewModelFactory {
        val homeRepository =
            HomeRepository.getInstance(FakeDatabase.getInstance().homeDao)
        return HomeViewModelFactory(homeRepository)
    }
}