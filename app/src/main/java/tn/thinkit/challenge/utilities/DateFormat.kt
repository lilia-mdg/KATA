package tn.thinkit.challenge.utilities

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SimpleDateFormat")
fun toSimpleString(date: LocalDateTime?) = with(date ?: LocalDateTime.now()) {
    val formatter = DateTimeFormatter.ofPattern("MMMM dd, YYYY")
    this.format(formatter)
}
