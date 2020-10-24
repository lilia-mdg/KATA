package tn.thinkit.challenge.ui.viewmodels

import androidx.lifecycle.ViewModel
import tn.thinkit.challenge.data.repositories.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getRooms() = homeRepository.getRooms()

}