package com.example.domain.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ConvertDate {
    fun Long.convertLongToTime(): String {
        val date = Date(this)
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return format.format(date)
    }

    fun String.getLongDate(): Long =
        try {
            val d: Date? = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).parse(this)
            d?.time ?: 0
        } catch (e: ParseException) {
            e.printStackTrace()
            0
        }

}