package com.example.domain.entity

import java.io.Serializable

abstract class Vehicle(private var plate: String) : Serializable {
    companion object {
        const val MAX_HOUR = 9
        const val MAX_HOUR_DAY = 24
        const val PLATE_INITIAL_LETTER = 'A'
    }

    protected fun calculatePaymentVehicle(
        priceHour: Double,
        priceDay: Double,
        hours: Int
    ): Double {
        return when {
            hours < MAX_HOUR -> hours * priceHour
            hours in MAX_HOUR..MAX_HOUR_DAY -> {
                priceDay
            }
            else -> {
                val days = hours / MAX_HOUR_DAY
                val restOfHours = hours % MAX_HOUR_DAY
                priceDay * days + priceHour * restOfHours
            }
        }
    }

    fun plateInitWithA(): Boolean {
        return plate[0] == PLATE_INITIAL_LETTER
    }
}