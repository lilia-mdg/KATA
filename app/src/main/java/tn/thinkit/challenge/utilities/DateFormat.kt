package tn.thinkit.challenge.utilities

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun toSimpleString(date: Date?) = with(date ?: Date()) {
    val dateFormat = SimpleDateFormat("MMMM dd, YYYY")
    dateFormat.format(this)
}
