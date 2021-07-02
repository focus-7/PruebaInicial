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
        hours = getHoursOfParking()
        amount = tariffPerVehicle.calculateTariffVehicle(hours)
    }

    private fun getHoursOfParking(): Int {
        var hours = ((vehicleDepartureDate - entryDate) / MILLS_HOUR).toInt()
        if (hours == 0) hours = 1
        return hours
    }

    fun getEntryDateString() = vehicleEntryDate.convertLongToTime()
    fun getDepartureDateString() = vehicleDepartureDate.convertLongToTime()
}
