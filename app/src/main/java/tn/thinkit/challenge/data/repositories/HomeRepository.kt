package tn.thinkit.challenge.data.repositories

import tn.thinkit.challenge.data.dao.FakeHomeDao

class HomeRepository private constructor(private val homeDao: FakeHomeDao) {

    fun getRooms() = homeDao.getRooms()

    companion object {
        @Volatile
        private var instance: HomeRepository? = null

        fun getInstance(homeDao: FakeHomeDao) =
            instance ?: synchronized(this) {
                instance ?: HomeRepository(homeDao).also { instance = it }
            }
    }

}