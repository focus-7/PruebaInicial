package com.example.domain.entity

class Car(plate: String) : Vehicle(plate) {
    companion object {
        const val HOUR_PRICE_CAR = 1000.0
        const val DAY_PRICE_CAR = 8000.0
    }

    fun calculatePaymentCar(hours: Int): Double {
        return calculatePaymentVehicle(HOUR_PRICE_CAR, DAY_PRICE_CAR, hours)
    }
}