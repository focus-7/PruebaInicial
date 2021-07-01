package com.ceiba.domain.service

interface TariffPerVehicle {
    fun getHourPrice(): Double
    fun getDayPrice(): Double
    fun getAdditionalValue(): Double
}