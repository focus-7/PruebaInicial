package com.ceiba.domain.aggregate

import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.model.Vehicle
import com.ceiba.domain.util.ConvertDate.convertLongToTime
import java.io.Serializable


class Tariff(var entryDate: Long, vehicle: Vehicle) : Serializable {
    companion object {
        const val MILLS_HOUR = 3600000
        const val MAX_HOUR = 9
        const val MAX_HOUR_DAY = 24
    }

    var vehicle: Vehicle = vehicle
        private set
    var vehicleType: Int = vehicle.vehicleType
        private set
    var vehicleEntryDate: Long = entryDate
        private set
    var amount: Double? = null
        private set
    var vehicleDepartureDate: Long = 0
    var hours: Int = 0


    fun setTariffVehicle(tariffPerVehicle: TariffPerVehicle, departureDate: Long) {
        vehicleDepartureDate = departureDate
        hours = ((vehicleDepartureDate - entryDate) / MILLS_HOUR).toInt()
        if (hours == 0) hours = 1

        val priceHour = tariffPerVehicle.priceHour
        val priceDay = tariffPerVehicle.priceDay

        amount = when {
            hours < MAX_HOUR -> hours * priceHour
            hours in MAX_HOUR..MAX_HOUR_DAY -> priceDay
            else -> {
                val days = hours / MAX_HOUR_DAY
                val restOfHours = hours % MAX_HOUR_DAY
                priceDay * days + priceHour * restOfHours
            }
        } + tariffPerVehicle.additionalValue()
    }

    fun getEntryDateString() = vehicleEntryDate.convertLongToTime()
    fun getDepartureDateString() = vehicleDepartureDate.convertLongToTime()
}
