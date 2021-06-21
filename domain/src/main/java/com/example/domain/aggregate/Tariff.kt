package com.example.domain.aggregate

import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import com.example.domain.entity.Vehicle
import com.example.domain.exception.CalculateAmountException
import com.example.domain.exception.VehicleEntryRestriction
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
    var departureDate: Long = 0
        set(value) {
            field = value
            calculateTariff()
        }

    init {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = entryDate
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        if (vehicle.plateInitWithA() && day != Calendar.SUNDAY && day != Calendar.MONDAY)
            throw VehicleEntryRestriction()
    }

    private fun calculateTariff() {
        val hours = ((departureDate - vehicleEntryDate) / MILLS_HOUR).toInt()
        amount = when (vehicle) {
            is Car -> (vehicle as Car).calculatePaymentCar(hours)
            is Motorcycle -> (vehicle as Motorcycle).calculatePaymentMotorcycle(hours)
            else -> throw CalculateAmountException()
        }
    }

    fun getEntryDateString() = vehicleEntryDate.convertLongToTime()

    fun getDepartureDateString() = departureDate.convertLongToTime()
}