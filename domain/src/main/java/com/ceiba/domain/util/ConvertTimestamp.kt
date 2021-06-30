package com.ceiba.domain.util

import java.text.SimpleDateFormat
import java.util.*

object ConvertDate {
    fun Long.convertLongToTime(): String {
        val date = Date(this)
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.getDefault())
        return format.format(date)
    }
}