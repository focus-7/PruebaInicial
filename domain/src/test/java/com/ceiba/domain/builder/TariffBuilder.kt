package com.ceiba.domain.builder

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Vehicle

class TariffBuilder {
    private var entryDate = 1624266000000 //June 21, 2021, 9:00 a.m

    fun withEntryDate(entryDate: Long): TariffBuilder {
        this.entryDate = entryDate
        return this
    }

    fun build(vehicle: Vehicle): Tariff {
        return Tariff(entryDate, vehicle)
    }
}