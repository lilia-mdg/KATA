package tn.thinkit.challenge.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tn.thinkit.challenge.data.model.Room

class FakeHomeDao {
    private val roomList = mutableListOf<Room>()
    private val rooms = MutableLiveData<List<Room>>()

    init {
        rooms.value = roomList
    }

    fun getRooms() = rooms as LiveData<List<Room>>

    fun addRoom(room: Room){
        roomList.add(room)
        rooms.value = roomList
    }
}