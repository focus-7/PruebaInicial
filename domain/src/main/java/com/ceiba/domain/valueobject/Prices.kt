package com.ceiba.domain.valueobject

enum class Prices(val hour: Double, val day: Double, val additionalAmount: Double) {
    MOTORCYCLE(500.0, 4000.0, 2000.0),
    CAR(1000.0, 8000.0, 0.0),
}