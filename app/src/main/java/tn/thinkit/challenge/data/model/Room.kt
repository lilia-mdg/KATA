package tn.thinkit.challenge.data.model

import androidx.annotation.DrawableRes

data class Room(val id: Int, val name: String, @DrawableRes val image: Int, val nbDevices: Int)