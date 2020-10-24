package tn.thinkit.challenge.data.database

import tn.thinkit.challenge.R
import tn.thinkit.challenge.data.model.Room

class HomeMockup {
    companion object{
        val rooms = listOf(
            Room(
                id= 1,
                name="Living room",
                image = R.drawable.livingroom,
                nbDevices = 4
            ),
            Room(
                id= 2,
                name="Media room",
                image = R.drawable.mediaroom,
                nbDevices = 3
            ),
            Room(
                id= 3,
                name="Bathroom",
                image = R.drawable.bathroom,
                nbDevices = 1
            ),
            Room(
                id= 4,
                name="Bedroom",
                image = R.drawable.bedroom,
                nbDevices = 3
            )
        )
    }
}