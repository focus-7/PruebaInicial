package com.example.domain.aggregate

import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import com.example.domain.entity.Vehicle
import com.example.domain.exception.InvalidDataException
import com.example.domain.util.ConvertDate.convertLongToTime
import java.io.Serializable
import java.util.*

class Tariff(var entryDate: Long, vehicle: Vehicle) : Serializable {
    companion object {
        const val MILLS_HOUR = 3600000
    }

    var vehicle: Vehicle = vehicle
        private set
    var vehicleEntryDate: Long = entryDate
        private set
    var amount: Double? = null
        private set
    var vehicleDepartureDate: Long = 0
        set(value) {
            field = value
            calculateTariff()
        }

    init {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = entryDate
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        if (vehicle.plateInitWithA() && day != Calendar.SUNDAY && day != Calendar.MONDAY)
            throw InvalidDataException("Vehiculo no autorizado a ingresar")
    }

    private fun calculateTariff() {
        var hours = ((vehicleDepartureDate - vehicleEntryDate) / MILLS_HOUR).toInt()
        if (hours == 0) hours = 1
        amount = when (vehicle) {
            is Car -> (vehicle as Car).calculatePaymentCar(hours)
            is Motorcycle -> (vehicle as Motorcycle).calculatePaymentMotorcycle(hours)
            else -> throw InvalidDataException("No se logro calcular el pago.")
        }
    }

    fun getEntryDateString() = vehicleEntryDate.convertLongToTime()

    fun getDepartureDateString() = vehicleDepartureDate.convertLongToTime()

}