package tn.thinkit.challenge.data

import tn.thinkit.challenge.data.dao.FakeHomeDao
import tn.thinkit.challenge.data.database.HomeMockup

class FakeDatabase private constructor() {
    companion object {
        @Volatile
        private var instance: FakeDatabase? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeDatabase().also { instance = it }
            }
    }
    val homeDao = FakeHomeDao()

    init {
        HomeMockup.rooms.forEach { room ->
            homeDao.addRoom(room)
        }
    }
}