package com.example.domain.repository

import com.example.domain.aggregate.Tariff
import com.example.domain.entity.Vehicle

interface TariffRepository {
    fun getQuantityOfVehicles(typeVehicle: Int): Int
    fun takeOutVehicle(tariff: Tariff)
    fun enterVehicle(tariff: Tariff)
    fun getVehicles(): List<Tariff>
}