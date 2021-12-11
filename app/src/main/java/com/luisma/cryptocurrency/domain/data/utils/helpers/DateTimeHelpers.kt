package com.luisma.cryptocurrency.domain.data.utils.helpers

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
fun parseDateTime(date: LocalDateTime): String {
    return "${date.dayOfMonth}/${date.monthValue}/${date.year}" +
            " - ${date.hour}:${date.minute}:${date.second}"
}