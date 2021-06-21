package com.example.domain.entity

class Motorcycle(plate: String, private var cylinderCapacity: Int) : Vehicle(plate) {
    companion object {
        const val HOUR_PRICE_MOTORCYCLE = 500.0
        const val DAY_PRICE_MOTORCYCLE = 4000.0
        const val MAX_CYLINDER_CAPACITY = 500
        const val CYLINDER_PAYMENT_ADDED = 2000.0
    }

    fun calculatePaymentMotorcycle(hours: Int): Double {
        var amount = calculatePaymentVehicle(HOUR_PRICE_MOTORCYCLE, DAY_PRICE_MOTORCYCLE, hours)
        if (cylinderCapacity > MAX_CYLINDER_CAPACITY)
            amount += CYLINDER_PAYMENT_ADDED
        return amount
    }
}