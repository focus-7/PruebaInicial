package com.ceiba.domain.aggregate

import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.model.Vehicle
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.util.ConvertDate.convertLongToTime
import com.ceiba.domain.util.VehicleGeneric
import com.ceiba.domain.valueobject.Prices
import com.ceiba.domain.valueobject.VehicleType
import java.io.Serializable
import java.util.*


class Tariff(var entryDate: Long, vehicle: Vehicle) : Serializable {
    companion object {
        const val MILLS_HOUR = 3600000
        const val MAX_HOUR = 9
        const val MAX_HOUR_DAY = 24
    }

    var vehicle: Vehicle = vehicle
        private set
    var vehicleType: Int = 0
        private set
    var vehicleEntryDate: Long = entryDate
        private set
    var amount: Double? = null
        private set
    var vehicleDepartureDate: Long = 0
        set(value) {
            field = value
            calculateVehicleTariff()
        }

    init {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = entryDate
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        if (vehicle.plateInitWithA() && day != Calendar.SUNDAY && day != Calendar.MONDAY)
            throw InvalidDataException("Vehiculo no autorizado a ingresar")
        else
            vehicleType = vehicle.vehicleType
    }

    private fun calculateVehicleTariff() {
        var hours = ((vehicleDepartureDate - vehicleEntryDate) / MILLS_HOUR).toInt()
        if (hours == 0) hours = 1

        amount = when (vehicleType) {
            VehicleType.CAR.type -> calculateVehiclePayment(
                Prices.CAR.hour,
                Prices.CAR.day,
                hours
            )
            VehicleType.MOTORCYCLE.type -> {
                val vehicleGeneric = VehicleGeneric(Motorcycle::class.java)
                calculateVehiclePayment(
                    Prices.MOTORCYCLE.hour,
                    Prices.MOTORCYCLE.day,
                    hours
                ) + vehicleGeneric.getTypeOfVehicle(vehicle)!!.getAdditionalAmountMotorcycle()
            }
            else -> throw InvalidDataException("No se logro calcular el pago.")
        }
    }

    private fun calculateVehiclePayment(
        priceHour: Double,
        priceDay: Double,
        hours: Int
    ): Double {
        return when {
            hours < MAX_HOUR -> hours * priceHour
            hours in MAX_HOUR..MAX_HOUR_DAY -> priceDay
            else -> {
                val days = hours / MAX_HOUR_DAY
                val restOfHours = hours % MAX_HOUR_DAY
                priceDay * days + priceHour * restOfHours
            }
        }
    }

    fun getEntryDateString() = vehicleEntryDate.convertLongToTime()

    fun getDepartureDateString() = vehicleDepartureDate.convertLongToTime()
}
